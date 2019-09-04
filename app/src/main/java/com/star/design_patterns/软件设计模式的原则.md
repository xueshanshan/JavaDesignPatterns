## 软件设计模式的原则
#### 1、单一职责原则

定义：不要存在多于一个导致类变更的原因。通俗的说，即一个类只负责一项职责。 

问题由来：类T负责两个不同的职责：职责P1，职责P2。当由于职责P1需求发生改变而需要修改类T时，有可能会导致原本运行正常的职责P2功能发生故障。

解决方案：遵循单一职责原则。分别建立两个类T1、T2，使T1完成职责P1功能，T2完成职责P2功能。这样，当修改类T1时，不会使职责P2发生故障风险；同理，当修改T2时，也不会使职责P1发生故障风险。

#### 2、开放封闭原则

定义：一个软件实体如类、模块和函数应该对扩展开放，对修改关闭。

问题由来：在软件的生命周期内，因为变化、升级和维护等原因需要对软件原有代码进行修改时，可能会给旧代码中引入错误，也可能会使我们不得不对整个功能进行重构，并且需要原有代码经过重新测试。

解决方案：当软件需要变化时，尽量通过扩展软件实体的行为来实现变化，而不是通过修改已有的代码来实现变化。

但是现实开发中，绝对的修改关闭是不可能的，这就要求开发人员必须对其设计的模块应该对哪种变化封闭作出选择，他必须先预测或者预留出最优可能发生变化的地方，然后构造出抽象来隔离那些变化，来尽量做到开放封闭原则。

开放封闭原则是面向对象设计的核心所在，遵循这个原则设计出来的软件可维护、可扩展、可复用、灵活性好，开发人员应该仅对程序中呈现出频繁变化的那些部分作出抽象，然而对于应用中每个部分都刻意进行抽象并不是一个好主意，拒绝不成熟的抽象和抽象一样重要。

#### 3、依赖倒置原则

定义：高层模块不应该依赖低层模块，二者都应该依赖其抽象；抽象不应该依赖细节；细节应该依赖抽象。

问题由来：类A直接依赖类B，假如要将类A改为依赖类C，则必须通过修改类A的代码来达成。这种场景下，类A一般是高层模块，负责复杂的业务逻辑；类B和类C是低层模块，负责基本的原子操作；假如修改类A，会给程序带来不必要的风险。

解决方案：将类A修改为依赖接口I，类B和类C各自实现接口I，类A通过接口I间接与类B或者类C发生联系，则会大大降低修改类A的几率。

依赖倒置原则基于这样一个事实：相对于细节的多变性，抽象的东西要稳定的多。以抽象为基础搭建起来的架构比以细节为基础搭建起来的架构要稳定的多。在java中，抽象指的是接口或者抽象类，细节就是具体的实现类，使用接口或者抽象类的目的是制定好规范和契约，而不去涉及任何具体的操作，把展现细节的任务交给他们的实现类去完成。

依赖倒置原则的核心就是要我们面向接口编程，理解了面向接口编程，也就理解了依赖倒置。

比如电脑里硬件有主板，其依赖于cpu、内存、硬盘等部件，但是不管是主板还是内存坏掉，都不会使其他部件需要换新，所以他们之间并没有具体依赖，而是依赖于接口，统一的设计规范，在软件开发中也是一样的，模块之间应该依赖接口和抽象，只要接口是稳定的，任何一个的更改都不用担心其他受到影响。

举例：母亲给孩子讲故事，只要给她一本书，她就可以照着书给孩子讲故事了

```java
class Book{
	public String getContent(){
		return "很久很久以前有一个阿拉伯的故事……";
	}
}

class Mother{
	public void narrate(Book book){
		System.out.println("妈妈开始讲故事");
		System.out.println(book.getContent());
	}
}

public class Client{
	public static void main(String[] args){
		Mother mother = new Mother();
		mother.narrate(new Book());
	}
} 
```
运行良好，假如有一天，需求变成这样：不是给书而是给一份报纸，让这位母亲讲一下报纸上的故事，报纸的代码如下:

```java

class Newspaper{
	public String getContent(){
		return "林书豪38+7领导尼克斯击败湖人……";
	}
} 
```

这位母亲却办不到，因为她居然不会读报纸上的故事，这太荒唐了，只是将书换成报纸，居然必须要修改Mother才能读。假如以后需求换成杂志呢？换成网页呢？还要不断地修改Mother，这显然不是好的设计。原因就是Mother与Book之间的耦合性太高了，必须降低他们之间的耦合度才行。

我们引入一个抽象的接口IReader。读物，只要是带字的都属于读物：

```java
interface IReader{
	public String getContent();
} 
```

Mother类与接口IReader发生依赖关系，而Book和Newspaper都属于读物的范畴，他们各自都去实现IReader接口，这样就符合依赖倒置原则了，代码修改为：

```java
class Newspaper implements IReader {
	public String getContent(){
		return "林书豪17+9助尼克斯击败老鹰……";
	}
}
class Book implements IReader{
	public String getContent(){
		return "很久很久以前有一个阿拉伯的故事……";
	}
}

class Mother{
	public void narrate(IReader reader){
		System.out.println("妈妈开始讲故事");
		System.out.println(reader.getContent());
	}
}

public class Client{
	public static void main(String[] args){
		Mother mother = new Mother();
		mother.narrate(new Book());
		mother.narrate(new Newspaper());
	}
}
```

那么为什么依赖了接口或者抽象类，就不怕更改呢？这就涉及了下面一个原则，里式替换原则。

#### 4、里式替换原则

定义：所有引用基类的地方必须能透明地使用其子类的对象，通俗来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能

只有当子类可以替换掉父类，软件单位的功能不受到影响时，父类才能真正的被复用，而子类也能够在父类的基础上增加新的行为。

问题由来：有一功能P1，由类A完成。现需要将功能P1进行扩展，扩展后的功能为P，其中P由原有功能P1与新功能P2组成。新功能P由类A的子类B来完成，则子类B在完成新功能P2的同时，有可能会导致原有功能P1发生故障。

解决方案：当使用继承时，遵循里氏替换原则。类B继承类A时，除添加新的方法完成新增功能P2外，尽量不要重写父类A的方法，也尽量不要重载父类A的方法。

继承包含这样一层含义：父类中凡是已经实现好的方法（相对于抽象方法而言），实际上是在设定一系列的规范和契约，虽然它不强制要求所有的子类必须遵从这些契约，但是如果子类对这些非抽象方法任意修改，就会对整个继承体系造成破坏。而里氏替换原则就是表达了这一层含义。

里氏替换原则通俗的来讲就是：子类可以扩展父类的功能，但不能改变父类原有的功能。

举例：

```java
class A{
	public int func1(int a, int b){
		return a - b;
	}
}

public class Client{
	public static void main(String[] args){
		A obj = new A();
		System.out.println("100-50=" + obj.func1(100,50)); //50
	}
}
```
后来我们需要增加一个新的功能，完成两个数相加，然后再与100求和，由B类来负责，B继承自A

```java
class B extends A{
	
	public int func1(int a, int b){
		ruturn a + b;
	}
	
	public int func2(int a, int b){
		return func1(a,b)+100;
	}
}

public static void main(String[] args){
	A obj = new B();
	System.out.println("100-50="+ obj.func1(100, 50)); //150
	System.out.println("100+20+100="+ obj.func2(100, 20));//220
} 
```
我们发现原本运行正常的相减功能发生了错误。原因就是类B在给方法起名时无意中重写了父类的方法，造成所有运行相减功能的代码全部调用了类B重写后的方法，造成原本运行正常的功能出现了错误。在实际编程中，我们常常会通过重写父类的方法来完成新的功能，这样写起来虽然简单，但是整个继承体系的可复用性会比较差，特别是运用多态比较频繁时，程序运行出错的几率非常大。

如果非要重写父类的方法，比较通用的做法是：原来的父类和子类都继承一个更通俗的基类，原有的继承关系去掉，采用依赖、聚合，组合等关系代替。

#### 5、迪米特法则

定义：一个对象应该对其他对象保持最少的了解。

问题由来：类与类之间的关系越密切，耦合度越大，当一个类发生改变时，对另一个类的影响也越大。

解决方案：尽量降低类与类之间的耦合。

举例：小花要用小明的电脑打字

违反迪米特法则的代码

```java
class Computer{
	public void typeWrite(String content){
		System.out.println(content);
	}
}

class XiaoMing{
	private Computer computer;
	
	public XiaoMing(){
		computer = new Computer();
	}
	
	public Computer getComputer(){
		return computer;
	}
}

class XiaoHua{
	public void typeWrite(XiaoMing xiaoming){
		xiaoming.getComputer.typeWrite("我是小花")
	}
}
```

现在这个问题主要出现在XiaoHua中，根据迪米特法则，只与直接的朋友发生通信，从逻辑上讲小花只需要与小明通信就行了，而不是小花直接使用小明的电脑，修正后的代码如下：

```java
class Computer{
	private void typeWrite(String content){
		System.out.println(content);
	}
}

//对于被依赖的类来说，都应该尽量的间逻辑封装在类的内部，对外提供pulic方法，不对外泄露任何信息
class XiaoMing{
	private Computer computer;
	
	public XiaoMing(){
		computer = new Computer();
	}
	
	public void typeWriteByComputer(String content){
		computer.typeWrite(content);
	}
}

class XiaoHua{
	public void typeWrite(XiaoMing xiaoming){
		xiaoming. typeWriteByComputer("我是小花")
	}
}

```

#### 5、接口隔离原则

定义：客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上。
 
问题由来：类A通过接口I依赖类B，类C通过接口I依赖类D，如果接口I对于类A和类B来说不是最小接口，则类B和类D必须去实现他们不需要的方法。

解决方案：将臃肿的接口I拆分为独立的几个接口，类A和类C分别与他们需要的接口建立依赖关系。也就是采用接口隔离原则

举例：

```java
interface I{
	void mothod1();
	void mothod2();
}

class A implements I{
	public void method1(){
		System.out.println("我只需要方法1");
	}
	
	public void method2(){
		//do nothing
	}
}

class B implements I{
	public void method1(){
		//do nothing
	}
	
	public void method2(){
		System.out.println("我只需要方法2");
	}
}


public class Test{
	A a = new A();
	a.method1();
	
	B b = new B();
	b.method2();
}
```

可以看到，如果接口过于臃肿，只要接口中出现的方法，不管对依赖它的类有没有用处，实现类中都必须去实现这些方法，这显然是不好的设计，如果将这个设计修改为符合接口隔离原则，就必须对接口I进行拆分，修改后的代码为：

```java
interface I1{
	void mothod1();
}

interface I2{
	void mothod2();
}

class A implements I1{
	public void method1(){
		System.out.println("我只需要方法1");
	}
}

class B implements I2{	
	public void method2(){
		System.out.println("我只需要方法2");
	}
}


public class Test{
	A a = new A();
	a.method1();
	
	B b = new B();
	b.method2();
}

```

接口隔离原则的含义是：建立单一接口，不要建立庞大臃肿的接口，尽量细化接口，接口中的方法尽量少。也就是说，我们要为各个类建立专用的接口，而不要试图去建立一个很庞大的接口供所有依赖它的类去调用。
















