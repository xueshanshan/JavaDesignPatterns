package com.star.design_patterns.decorate_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-12
 */
public abstract class Component {
    public abstract void operation();

    public static class ConcrateComponent extends Component {

        @Override
        public void operation() {
            System.out.println("具体对象的操作");
        }
    }
}
