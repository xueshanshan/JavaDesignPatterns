package com.star.design_patterns.factory_pattern.simple_factory;

import com.star.design_patterns.factory_pattern.Operation;

/**
 * @author xueshanshan
 * @date 2019-09-05
 */
public class SimpleFactory {
    //简单工厂，根据字符串创建相应的对象
    public static Operation createOperation(String name) {
        Operation operation = null;
        switch (name) {
            case "+":
                operation = new Operation.AddOperation();
                break;
            case "-":
                operation = new Operation.SubOperation();
                break;
            case "*":
                operation = new Operation.MulOperation();
                break;
            case "/":
                operation = new Operation.DivOperation();
                break;
        }
        return operation;
    }

    public static void main(String[] args) {
        System.out.println(createOperation("+").getResult(1,2));
        System.out.println(createOperation("-").getResult(1,2));
        System.out.println(createOperation("*").getResult(1,2));
        System.out.println(createOperation("/").getResult(1,2));
    }
}
