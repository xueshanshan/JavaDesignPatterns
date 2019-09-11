## 适配器模式

适配器就是一种适配中间件，它在于不匹配的二者之间，用于连接二者，将不匹配变得匹配，简单理解就是平常所见的转接头或转换器之类的存在。

适配模式有三种：类适配器、对象适配器、接口适配器

后面还有一种其实是项目中经常使用的，我自己命名为全局适配器模式

**UML角色**

**Source**：需要被适配的类、接口、对象。
**Destination**：需要得到的类，Source通过适配得到的类对象，也就是我们期待得到的接口。
**Adapter**：适配器类，协调Source和Destination，使两者能够协同工作。

### 1、类适配器模式

原理：通过继承来实现适配器功能。

简单的一个场景：手机充电需要将220V的交流电转化为手机锂电池需要的5V直流电

Source类：

```java
public class AC220{
	public int output220V(){
		int output = 220;
		return output;
	}
}
```

目标类Destination,只需要定义接口，由适配器来转化：

```java
public interface DC5{
	int output5V();
}
```

Adapter类：

```java
public class PowerAdapter extends AC220 implements DC5{
	@Override
	public int output5V(){
		int output = output220V();
      	return (output / 44);
	}
}
```

使用：

```java
PowerAdapter adapter = new PowerAdapter();
adapter.output5V();
```

上面PowerAdapter就类似于手机充电器，将220V的交流电转换成5V的直流电进行输出。

因为java单继承，所以Destination需要是接口，以便Adapter去继承Source并实现Destination,完成适配的功能。


### 2、对象适配器模式

对象适配器模式和类适配器模式逻辑是相同的，只不过是Adapter不继承Source,而是持有Source的对象，来实现适配功能

```java
public class PowerAdapter implements DC5{
	private AC220 mAC220;

	public PowerAdapter(AC220 ac220){
        this.mAC220 = ac220;
	}

   @Override
	public int output5V() {
		int output = 0;
		if (mAC220 != null) {
		 	output = mAC220.output220V() / 44;
		}
      	return output;
	}
}
```

使用：

```java
PowerAdapter adapter = new PowerAdapter(new AC220());
adapter.output5V();
```

这种情况下Destination就不是必须是接口了，也可以是父类，然后adapter重写父类的方法。

### 3、接口适配器模式

原理：通过抽象类来进行适配。

当存在这样一个接口，其中定义了N多方法，而我们现在却只想使用其中的一个或几个方法，如果我们直接实现接口，那么我们要实现所有的方法，哪怕仅仅是对不需要的方法进行置空，也会导致这个类变得臃肿，调用也不方便，这是我们可以使用一个抽象类作为中间件，即适配器，用这个抽象类实现接口，而在抽象类中把所有的方法都置空，那么我们再创建抽象类的继承类，此时只需要重写需要使用的方法即可。


```java
public interface DCoutput{
	int output5V();
	int output9V();
	int output12V();
	int output220V();
}
```


```java
public abstract class PowerAdapter implements DCoutput{
	@override
	public int output5V(){}

	@override
	public int output9V(){}

	@override
	public int output12V(){}

	@override
	public int output220V(){}
}
```

```java
public class Power5VAdapter extends PowerAdapter{
	private AC220 mAC220;

	public PowerAdapter(AC220 ac220){
        this.mAC220 = ac220;
	}

   //只需要重写我们关心的方法
   @Override
	public int output5V() {
		int output = 0;
		if (mAC220 != null) {
		 	output = mAC220.output220V() / 44;
		}
      	return output;
	}

}
```

### 4、全局适配器模式

1，2列出的适配器模式必须通过继承和实现，但是适配器作为一个中间件，应该只是适配A和B,应该是一个独立的类，抽出一些固定的方法来进行转换。

下面是一个简单的例子

```java
//抽象的适配器  抽出来要适配的方法  这里是将string的数据转换为实体
public abstract class CommonBaseAdapter<T> {
    public abstract T translate(String jsonData);
}
```

```java
//具体的适配器  实现适配方法进行转换
public class MyAdapter1 extends CommonBaseAdapter<Person> {

    @Override
    public Person translate(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return new Person(jsonObject.getString("name"), jsonObject.getInt("age"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class Person{

    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

```java
//这里是适配器使用的工具类
public class AdapterUtils {
    public static Set<CommonBaseAdapter> sAdapterList = new HashSet<>();

	//注册适配器
    public static void registerAdapter(CommonBaseAdapter commonBaseAdapter) {
        sAdapterList.add(commonBaseAdapter);
    }

	//解注册适配器
    public static void unRegisterAdapter(CommonBaseAdapter commonBaseAdapter) {
        sAdapterList.remove(commonBaseAdapter);
    }

	//根据一定的规则获取合适能够进行转换的适配器
    public static <T> CommonBaseAdapter getAdapter(T targetClass) {
        for (CommonBaseAdapter adapter : sAdapterList) {
            Type type = adapter.getClass().getGenericSuperclass();
            if (!(type instanceof Class)) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class actualTypeArgument = (Class) parameterizedType.getActualTypeArguments()[0];
                if (actualTypeArgument == targetClass) {
                    return adapter;
                }
            }
        }
        return null;
    }
}
```

```java
//测试
AdapterUtils.registerAdapter(new MyAdapter1());
JSONObject jsonObject = new JSONObject();
try {
      jsonObject.put("name", "xss");
      jsonObject.put("age", 18);
    } catch (JSONException e) {
      e.printStackTrace();
    }
CommonBaseAdapter<Person> adapter = (CommonBaseAdapter<Person>) AdapterUtils.getAdapter(Person.class);
if (adapter != null) {
   Person person = adapter.translate(jsonObject.toString());
   Log.d("xxx", person.toString());
}
```

>针对于Android中ListView或RecyclerView的适配器也是这种模式，定义了一个抽象类Adapter，抽象出了几个方法，跟上面的例子区别在于，这个适配器可能不是用于整个项目的，它只是服务于单个ListView或者ReclcerView，你需要继承BaseAdapter，重写抽象方法，将数据适配到view上，然后将该BaseAdapter的子类直接设置给ListView或者RecyclerView.

