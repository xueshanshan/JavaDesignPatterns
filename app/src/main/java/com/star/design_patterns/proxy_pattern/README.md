## 代理模式

### 什么是代理模式

代理模式是指给目标对象提供一个代理对象，并由代理对象访问目标对象，即提供了对目标对象间接的访问方式。 这样做的好处在于：可以在目标对象的实现的基础上，增强额外的功能操作，扩展目标对象的功能。

这里使用到设计模式中的一个原则：开闭原则，对扩展开放，对修改关闭，即软件实体应该尽量在不修改原有代码的情况下进行扩展。

### 有哪几种代理模式

##### 1. 静态代理
静态代理在使用时，需要定义接口或者父类，代理对象和原对象一起实现相同的接口或父类。

```java
//接口
public interface IFactory {
    void produceCars();

	 //实现类
    public class FactoryImpl implements IFactory {

        @Override
        public void produceCars() {
            System.out.println("生产汽车");
        }
    }

	//代理类
    public class FactoryImplProxy implements IFactory {
        private IFactory mIFactory;

        public FactoryImplProxy(IFactory IFactory) {
            mIFactory = IFactory;
        }

        @Override
        public void produceCars() {
            System.out.println("开始");
            mIFactory.produceCars();
            System.out.println("结束");
        }
    }
}

//测试类
public class Test {
    public static void main(String[] args) {
        IFactory.FactoryImpl factory = new IFactory.FactoryImpl();

        IFactory.FactoryImplProxy proxy = new IFactory.FactoryImplProxy(factory);
        proxy.produceCars();
    }
}


//打印结果
开始
生产汽车
结束
```

##### 2. 动态代理

在动态代理中，我们不需要再手动的创建代理类，只需要创建一个动态处理器就可以了，真正的代理对象由JDK在运行时为我们创建。


代理类所在包：`java.lang.reflect.Proxy`

JDK实现代理只需要使用newProxyInstance方法

```java
/**
*  loader 指当前目标对象使用的类加载器，获取加载器的方法是固定的
*  interfaces  目标对象实现的接口类型
*  handler 事件处理器，执行目标对象的方法时，会触发事件处理器的方法，同时会把当前执行目标对象的方法作为参数传入
*/
static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler handler )
```

代码示例：

```java
IFactory proxyInstance = (IFactory) Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("动态代理--开始");
                Object invoke = method.invoke(factory, args);
                System.out.println("动态代理--结束");
                return invoke;
            }
        });
proxyInstance.produceCars();

//打印结果
动态代理--开始
生产汽车
动态代理--结束
```

源码分析：

```java
  private static final Class<?>[] constructorParams =
        { InvocationHandler.class };

   @CallerSensitive
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
        Objects.requireNonNull(h);

        final Class<?>[] intfs = interfaces.clone();
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        /*
         * Look up or generate the designated proxy class.
         * 生成代理类  其实就是类似于静态代理，只不过静态代理类是我们提前写好的
         * 但是这里是JDK帮我们动态生成  里面生成的过程是调用jni来的，就不详细分析了
         */
        Class<?> cl = getProxyClass0(loader, intfs);

        /*
         * Invoke its constructor with the designated invocation handler.
         */
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }

            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
            //反射生成并返回代理类对象
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString(), t);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }
```

由于动态代理生成的class是直接以二进制的方式加载进内存中的，并没有对应的.class文件生成，所以如果想通过反编译工具查看动态代理生成的代码需要通过特殊的手段来处理。

看下别人打印的生成的代理类的真容

```java
//继承了Proxy类，实现目标代理类接口
public final class $Proxy0 extends Proxy implements Run
{
  private static Method m1;
  private static Method m3;
  private static Method m0;
  private static Method m2;

  public $Proxy0(InvocationHandler paramInvocationHandler)
  {
    super(paramInvocationHandler);
  }

  static
  {
    try
    {
      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
      //获取目标代理类的方法
      m3 = Class.forName("proxy.Run").getMethod("run", new Class[0]);
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }

  //方法重写
  public final String run()
  {
    try
    {
      //this.h就是InvocationHandler的实现类了，调用invoke方法，在实现类里面做拦截处理
      return (String)this.h.invoke(this, m3, null);
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }

  public final boolean equals(Object paramObject)
  {
    try
    {
      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }

  public final String toString()
  {
    try
    {
      return (String)this.h.invoke(this, m2, null);
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }

  public final int hashCode()
  {
    try
    {
      return ((Integer)this.h.invoke(this, m0, null)).intValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
}
```

可以看到，动态生成的代理类有如下特性：

1. 继承了Proxy类，实现了代理的接口，由于java不能多继承，这里已经继承了Proxy类了，不能再继承其他的类，所以JDK的动态代理不支持对实现类的代理，只支持接口的代理。

2. 提供了一个使用InvocationHandler作为参数的构造方法。
3. 生成静态代码块来初始化接口中方法的Method对象，以及Object类的equals、hashCode、toString方法。
4. 重写了Object类的equals、hashCode、toString，它们都只是简单的调用了InvocationHandler的invoke方法，即可以对其进行特殊的操作，也就是说JDK的动态代理还可以代理上述三个方法。
5. 我们可以在invoke方法中进行一些特殊操作，甚至不调用实现的方法，直接返回。

优点：不侵入原有代码，能同时代理被代理类的多个方法，使得处理代码汇总到一处。
缺点：只能代理接口，而且使用的过程中需要传入被代理对象，因为需要反射调用被代理对象的方法

##### 3. cglib代理

上面的静态代理和动态代理模式都是要求目标对象继承某个父类或者实现某个接口，但是有的时候目标对象只是一个单独的对象，并没有父类或实现接口，这个时候就可以使用目标对象子类的方式实现代理，这种方法就叫做cglib代理。

cglib代理，也叫做子类代理，它是在内存中构建一个子类对象从而实现对目标对象功能的扩展。

但是，一个很致命的缺点是：cglib底层采用ASM字节码生成框架，使用字节码技术生成代理类，也就是生成的.class文件，而我们在android中加载的是优化后的.dex文件，也就是说我们需要可以动态生成.dex文件代理类，cglib在android中是不能使用的。

cglib使用方式：

```java
 Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(IFactory.FactoryImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("cglib代理--开始");
                Object invoke = methodProxy.invokeSuper(obj, args);
                System.out.println("cglib代理--结束");
                return invoke;
            }
        });
        IFactory.FactoryImpl ps = (IFactory.FactoryImpl) enhancer.create();
        ps.produceCars();
```

JDK调用代理方法，是通过反射机制调用，Cglib是通过FastClass机制直接调用方法，Cglib执行效率更高。
FastClass机制的原理简单来说就是：为代理类和被代理类各生成一个Class，这个Class会为代理类或被代理类的方法分配一个index(int类型)，这个index当做一个入参，FastClass就可以直接定位要调用的方法直接进行调用，这样省去了反射调用，所以调用效率比JDK动态代理通过反射调用高。


由于cglib动态代理不能用于Android中，那么可以使用[dexmaker](https://github.com/linkedin/dexmaker)


```java
try {
            IFactory.FactoryImpl build = ProxyBuilder.forClass(IFactory.FactoryImpl.class)
                    .handler(new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("dexmaker代理--开始");
                            Object o = ProxyBuilder.callSuper(proxy, method, args);
                            System.out.println("dexmaker代理--结束");
                            return o;
                        }
                    }).build();
            build.produceCars();
        } catch (IOException e) {
            e.printStackTrace();
        }
```

跟cglib用法差不多，但是能用于Android

