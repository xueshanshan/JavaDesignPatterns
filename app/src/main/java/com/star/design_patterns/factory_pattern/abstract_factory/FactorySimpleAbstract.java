package com.star.design_patterns.factory_pattern.abstract_factory;

import com.star.design_patterns.factory_pattern.Operation;

/**
 * @author xueshanshan
 * @date 2019-09-05
 *
 * 简单工厂+抽象工厂
 */
public class FactorySimpleAbstract {

    public static Operation createOperation(String type) {
        Operation operation = null;
        switch (type) {
            case "add":
                operation = new Operation.AddOperation();
                break;
            case "sub":
                operation = new Operation.SubOperation();
                break;
            default:
                break;
        }
        return operation;
    }

    public static Tool createTool(String type) {
        Tool tool = null;
        switch (type) {
            case "add":
                tool = new Tool.AddTool();
                break;
            case "sub":
                tool = new Tool.SubTool();
                break;
            default:
                break;
        }
        return tool;
    }
}
