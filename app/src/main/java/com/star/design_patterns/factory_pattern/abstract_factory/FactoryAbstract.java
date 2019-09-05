package com.star.design_patterns.factory_pattern.abstract_factory;

/**
 * @author xueshanshan
 * @date 2019-09-05
 */
public class FactoryAbstract {
    public static void main(String[] args) {
        IFactoryAbstract factoryAbstract = new IFactoryAbstract.AddFactoryAbstract();
        System.out.println(factoryAbstract.createOperation().getResult(1, 2));
        factoryAbstract.createTool().doSomething();

        factoryAbstract = new IFactoryAbstract.SubFactoryAbstract();
        System.out.println(factoryAbstract.createOperation().getResult(1, 2));
        factoryAbstract.createTool().doSomething();
    }
}
