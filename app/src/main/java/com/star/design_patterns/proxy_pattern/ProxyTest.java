package com.star.design_patterns.proxy_pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xueshanshan
 * @date 2019-09-11
 */
public class ProxyTest {
    public static void main(final String[] args) {
        //静态代理
        final IFactory.FactoryImpl factory = new IFactory.FactoryImpl();

        IFactory.FactoryImplProxy proxy = new IFactory.FactoryImplProxy(factory);
        proxy.produceCars();

        //java动态代理
        IFactory proxyInstance = (IFactory) Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("动态代理--开始");
                //可以不调用原方法
                Object invoke = method.invoke(factory, objects);
                System.out.println("动态代理--结束");
                return invoke;
            }
        });
        proxyInstance.produceCars();
    }
}
