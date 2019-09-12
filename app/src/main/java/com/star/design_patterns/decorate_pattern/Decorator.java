package com.star.design_patterns.decorate_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-12
 */
public abstract class Decorator extends Component {
    protected Component mComponent;

    public void setComponent(Component component) {
        mComponent = component;
    }

    @Override
    public void operation() {
        if (mComponent != null) {
            mComponent.operation();
        }
    }


    public static class ConcreateDecoratorA extends Decorator {
        @Override
        public void operation() {
            super.operation();
            System.out.println("具体装饰对象A的操作");
        }
    }

    public static class ConcreateDecoratorB extends Decorator {
        @Override
        public void operation() {
            super.operation();
            System.out.println("具体装饰对象B的操作");
        }
    }
}
