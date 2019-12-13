## 责任链模式

### 定义
>责任链模式（ `Chain of Responsibility Pattern `）：为请求创建了一个接收者对象的链，每个接收者都包含对另一个接收者的引用。如果一个对象不能处理该请求，那么它会把相同的请求传给下一个接收者，依此类推。

### 适用场景
在处理某个请求的时候，解决策略因条件不同而不同。这时，相对于使用 `if-else` 来区分不同的条件和对应的解决策略，我们可以使用责任链模式，将不同条件和对应的解决策略封装到一个类中，即不同的处理者。然后将这些处理者组成责任链，在当前处理者无法处理或不符合当前条件时，将请求传递给下一个处理者。

### 成员与类图

#### 成员
责任链模式的结构比较简单，不包括客户端只有两个成员：

* 处理者（Handler）：处理者定义处理请求的接口
* 具体处理者（Concrete Handler）: 具体处理者实现处理者声明的接口，负责处理请求

#### 模式类图
![](images/ChainofResponsibility1.png)

* `Handler` 是上层抽象类，定义 `handleRequest` 方法（处理他知道如何让处理的请求对象）。`Handler` 有一个指向另一个同类型实例的引用，即 `successor`。当调用`Handle` 实例的 `handleRequest` 方法不知道如何处理请求时，它会用同样的方法把请求转发给 `successor`，一直如此，直到传给链中的最后一个`Handle` ,
* `ConcreteHandler1` 和 `ConcreteHandler2` 实现了 `handleRequest` 方法（处理他们认识的请求对象）。

看一个形象点的图：

![](images/ChainofResponsibility2.png)

就好比如上图：由发送者发起请求，由具体的接受者处理该请求，先是一级接受者进行处理，如果它能够处理就直接处理请求，如果不能处理就传递到二级接受者，二级接受者同样看是否自己能够处理该请求，如果能够处理就执行不能处理就传递给下一级。

### 代码示例

举例，我想请假：
请假2天以内找TeamLeader就能批
请假大于2天但5天以内，TeamLeader做不了主，他需要找他的上级领导HRD审批
请假大于5天，HRD也做不了主，他需要找他的上级领导CEO审批

通常理论上不能越级找领导，所以我们只能找TeamLeader。
这种逻辑关系在代码里就可能是很长很深的if嵌套：

```java
	if (请假天数 <= 2) {
		// TeamLeader签字审批
	} else {
		if (请假天数 <= 5) {
			// HRD签字审批
		} else {
			// CEO签字审批
		}
	}
```

管理者基类：

```java
public abstract class Manager {
	
	protected Manager superior;
	
	public void setSuperior(Manager superior) {
		this.superior = superior;
	}
	
	public abstract void approve(int days);
}
```

TeamLeader类：

```java
publi class TeamLeader extands Manager {
	
	@Override
	public void approve(int days) {
		if (days <= 2) {
			// TeamLeader签字审批
		} else {
			if (superior != null) {
				superior.approve(days);
			}
		}
	}
}
```

HRD类：

```java
publi class HRD extands Manager {
	
	@Override
	public void approve(int days) {
		if (days <= 5) {
			// HRD签字审批
		} else {
			if (superior != null) {
				superior.approve(days);
			}
		}
	}
}
```

CEO类：

```java
publi class CEO extands Manager {
	
	@Override
	public void approve(int days) {
		if (days <= 30) {
			// CEO签字审批
		} else {
			if (superior != null) {
				superior.approve(days);
			}
		}
	}
}
```

我们只需要这样写代码：

```java
Manager teamLeader = new TeamLeader();
Manager hrd = new HRD();
Manager ceo = new CEO();

// 设置上级领导
teamLeader.setSuperior(hrd);
hrd.setSuperior(ceo);

// 让TeamLeader审批请假
teamLeader.approve(2);
```

这样就替代了原有的if嵌套结构，如果改变了上级关系，我们也可以轻松维护。

#### 优点
责任链模式非常显著的优点是将请求和处理分开。请求者可以不用知道是谁处理的，处理者可以不用知道请求的全貌，两者解耦，提高系统的灵活性。

#### 缺点
责任链模式有两个非常显著的缺点：一是性能问题，每个请求都是从链头遍历到链尾，特别是在链比较长的时候，性能是一共非常大的问题。二是调试不方便，特别是链条比较长，环节比较多的时候，由于采用了类似递归的方式，调试的时候逻辑可能比较复杂

