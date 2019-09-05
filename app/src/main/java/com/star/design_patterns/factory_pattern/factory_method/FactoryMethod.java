package com.star.design_patterns.factory_pattern.factory_method;

/**
 * @author xueshanshan
 * @date 2019-09-05
 */
public class FactoryMethod {

    public static void main(String[] args) {
        IFactoryMethod factory = new IFactoryMethod.AddFactoryMethod();
        System.out.println(factory.createOperation().getResult(1, 2));

        factory = new IFactoryMethod.SubFactoryMethod();
        System.out.println(factory.createOperation().getResult(1, 2));

        factory = new IFactoryMethod.MulFactoryMethod();
        System.out.println(factory.createOperation().getResult(1, 2));

        factory = new IFactoryMethod.DivFactoryMethod();
        System.out.println(factory.createOperation().getResult(1, 2));
    }
}
