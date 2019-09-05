package com.star.design_patterns.factory_pattern.abstract_factory;

import com.star.design_patterns.factory_pattern.Operation;

/**
 * @author xueshanshan
 * @date 2019-09-05
 *
 * 抽象工厂接口
 */
public interface IFactoryAbstract {
    Operation createOperation();

    Tool createTool();


    /**
     * 加法工厂
     */
    public static class AddFactoryAbstract implements IFactoryAbstract {

        @Override
        public Operation createOperation() {
            return new Operation.AddOperation();
        }

        @Override
        public Tool createTool() {
            return new Tool.AddTool();
        }
    }

    /**
     * 减法工厂
     */
    public static class SubFactoryAbstract implements IFactoryAbstract {

        @Override
        public Operation createOperation() {
            return new Operation.SubOperation();
        }

        @Override
        public Tool createTool() {
            return new Tool.SubTool();
        }
    }
}
