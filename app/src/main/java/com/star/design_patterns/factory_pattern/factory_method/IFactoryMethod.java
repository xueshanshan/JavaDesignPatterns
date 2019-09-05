package com.star.design_patterns.factory_pattern.factory_method;

import com.star.design_patterns.factory_pattern.Operation;

/**
 * @author xueshanshan
 * @date 2019-09-05
 *
 * 工厂方法接口
 */
public interface IFactoryMethod {
    Operation createOperation();

    /**
     * 加法工厂
     */
    public static class AddFactoryMethod implements IFactoryMethod {

        @Override
        public Operation createOperation() {
            return new Operation.AddOperation();
        }
    }

    /**
     * 减法工厂
     */
    public static class SubFactoryMethod implements IFactoryMethod {

        @Override
        public Operation createOperation() {
            return new Operation.SubOperation();
        }
    }

    /**
     * 乘法工厂
     */
    public static class MulFactoryMethod implements IFactoryMethod {

        @Override
        public Operation createOperation() {
            return new Operation.MulOperation();
        }
    }

    /**
     * 除法工厂
     */
    public static class DivFactoryMethod implements IFactoryMethod {

        @Override
        public Operation createOperation() {
            return new Operation.DivOperation();
        }
    }
}
