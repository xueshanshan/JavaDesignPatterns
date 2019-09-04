package com.star.design_patterns.composite_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-04
 *
 * 叶子节点
 */
public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("can not add a component to a leaf");
    }

    @Override
    public void remove(Component component) {
        System.out.println("can not remove a component to a leaf");
    }

    @Override
    public void display(int level) {
        System.out.println(String.format("%0" + level + "d", 0).replace("0", "-") + name);
    }
}
