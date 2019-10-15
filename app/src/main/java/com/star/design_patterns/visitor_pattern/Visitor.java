package com.star.design_patterns.visitor_pattern;

/**
 * @author xueshanshan
 * @date 2019-10-15
 */
//抽象访问者类
public interface Visitor {
    void visit(Element.ConcreteElementA concreteElementA);

    void visit(Element.ConcreteElementB concreteElementB);

    /**
     * 具体访问者A
     */
    public class ConcreteVisitorA implements Visitor {

        @Override
        public void visit(Element.ConcreteElementA concreteElementA) {
            System.out.print("具体访问者A访问->");
            concreteElementA.operationA();
        }

        @Override
        public void visit(Element.ConcreteElementB concreteElementB) {
            System.out.print("具体访问者A访问->");
            concreteElementB.operationB();
        }
    }

    /**
     * 具体访问者B
     */
    public class ConcreteVisitorB implements Visitor {

        @Override
        public void visit(Element.ConcreteElementA concreteElementA) {
            System.out.print("具体访问者B访问->");
            concreteElementA.operationA();
        }

        @Override
        public void visit(Element.ConcreteElementB concreteElementB) {
            System.out.print("具体访问者B访问->");
            concreteElementB.operationB();
        }
    }
}
