package com.star.design_patterns.composite_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-04
 *
 * 一个抽象构件，声明一个接口用于访问和管理Component的子部件
 */
public abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void remove(Component component);

    public abstract void display(int level);
}
