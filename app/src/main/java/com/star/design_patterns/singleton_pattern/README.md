### <center>常用设计模式之Singleton Pattern</center>

### 什么是单例模式?

单例模式指的是在应用整个生命周期内只能存在一个实例。单例模式是一种被广泛使用的设计模式。他有很多好处，能够避免实例对象的重复创建，减少创建实例的系统开销，节省内存。
单例写法要求：

- 某个类只能有一个实例
- 它必须自行创建这个实例
- 他必须自行向整个系统提供整个实例

单例有很多种写法,好的单例模式写法上要要遵循2点:

- 线程安全(多线程)
- 延迟加载(资源占用少)


###  单例模式的几种写法

```java
public class Singleton {

    private Singleton() {
    }

    //----1、饿汉式----
    private static Singleton mSingleton = new Singleton();

    public static Singleton getInstance() {
        return mSingleton;
    }


    //---2、静态代码块饿汉式---
    private static Singleton mSingleton2;

    static {
        mSingleton2 = new Singleton();
    }

    public static Singleton getInstance2() {
        return mSingleton2;
    }

    //---3、懒汉式（线程不安全）---
    private static Singleton mSingleton3;

    public static Singleton getInstance3() {
        if (mSingleton3 == null) {
            mSingleton3 = new Singleton();
        }
        return mSingleton3;
    }

    //---4、懒汉式（线程安全,延迟加载，推荐使用）----
    private volatile static Singleton mSingleton4;

    public static Singleton getInstance4() {
        if (mSingleton3 == null) {
            synchronized (Singleton.class) {
                if (mSingleton3 == null) {
                    mSingleton3 = new Singleton();
                }
            }
        }
        return mSingleton3;
    }

    //---5、静态内部类（线程安全,延迟加载，推荐使用）---
    private static class Holder {
        private static Singleton mSingleton = new Singleton();
    }

    public static Singleton getInstance5() {
        return Holder.mSingleton;
    }
}


    //---6、枚举 （线程安全,延迟加载，推荐使用）---
    public enum SingletonEnum {
        INS;
    }
```


> volatile关键字,🔒,就是一把同步锁,synchronized是阻塞同步的，在线程竞争激烈的情况下会升级为重量级锁。而volatile就可以说是java虚拟机提供的最轻量级的同步锁。Java内存模型告诉我们，各个线程会将共享变量从主内存拷贝到工作内存，然后执行引擎会基于工作内容中的数据进行操作处理。线程在工作内存进行操作后何时会写到主内存中？这个时机对于普通变量是没有规定的，而针对volatile修饰的变量给java虚拟机特殊的约定，线程对volatile变量的修改会立即被其他线程感知，既不会出现数据的可见性。

> 静态内部类和非静态内部类都是延迟加载，即只调用外部类，内部类是不会被加载的

> 静态内部类可以单独加载，非静态内部类必须在外部类加载之后加载，所以静态内部类可以有静态成员和方法，而静态内部类不行


ps:为什么枚举能做到线程安全?

- 枚举中的各个枚举项通过static来定义的
- static类型的属性会在类被加载之后被初始化
- 当一个Java类第一次被真正使用到的时候静态资源被初始化、Java类的加载和初始化过程都是线程安全的（因为虚拟机在加载枚举的类的时候，会使用ClassLoader的loadClass方法，而这个方法使用同步代码块保证了线程安全）。所以，创建一个enum类型是线程安全的。






