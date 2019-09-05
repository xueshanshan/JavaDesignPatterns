package com.star.design_patterns.factory_pattern.abstract_factory;

/**
 * @author xueshanshan
 * @date 2019-09-05
 *
 * 定义新的接口用于抽象工厂生产具体的对象
 */
public interface Tool {
    void doSomething();


    /**
     * 加法工具类
     */
    public static class AddTool implements Tool {

        @Override
        public void doSomething() {
            System.out.println("做加法工具");
        }
    }

    /**
     * 减法工具类
     */
    public static class SubTool implements Tool {

        @Override
        public void doSomething() {
            System.out.println("做减法工具");
        }
    }
}
