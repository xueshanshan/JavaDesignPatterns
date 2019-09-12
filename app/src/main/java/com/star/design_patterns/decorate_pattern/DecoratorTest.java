package com.star.design_patterns.decorate_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-12
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Component.ConcrateComponent concrateComponent = new Component.ConcrateComponent();
        Decorator.ConcreateDecoratorA concreateDecoratorA = new Decorator.ConcreateDecoratorA();
        Decorator.ConcreateDecoratorB concreateDecoratorB = new Decorator.ConcreateDecoratorB();

        concreateDecoratorA.setComponent(concrateComponent);
        concreateDecoratorB.setComponent(concreateDecoratorA);
        concreateDecoratorB.operation();
    }
}
