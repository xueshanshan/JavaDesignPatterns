package com.star.design_patterns.proxy_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-11
 */
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
            System.out.println("静态代理--开始");
            mIFactory.produceCars();
            System.out.println("静态代理--结束");
        }
    }
}
