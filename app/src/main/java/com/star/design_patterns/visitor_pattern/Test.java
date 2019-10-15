package com.star.design_patterns.visitor_pattern;

/**
 * @author xueshanshan
 * @date 2019-10-15
 */
public class Test {

    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.add(new Element.ConcreteElementA());
        objectStructure.add(new Element.ConcreteElementB());
        objectStructure.accept(new Visitor.ConcreteVisitorA());
        objectStructure.accept(new Visitor.ConcreteVisitorB());
    }
}
