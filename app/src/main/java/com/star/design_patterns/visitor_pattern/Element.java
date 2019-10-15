package com.star.design_patterns.visitor_pattern;

/**
 * @author xueshanshan
 * @date 2019-10-15
 */
//抽象元素类
public interface Element {
    void accept(Visitor visitor);


    /**
     * 具体元素A
     */
    public class ConcreteElementA implements Element {

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public void operationA() {
            System.out.println("具体元素A的操作");
        }
    }

    /**
     * 具体元素B
     */
    public class ConcreteElementB implements Element {

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public void operationB() {
            System.out.println("具体元素B的操作");
        }
    }

}
