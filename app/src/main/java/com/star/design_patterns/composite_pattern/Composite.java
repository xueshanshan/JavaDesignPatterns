package com.star.design_patterns.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xueshanshan
 * @date 2019-09-04
 *
 * 枝节点
 */
public class Composite extends Component {
    private List<Component> mComponents = new ArrayList<>();

    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        mComponents.add(component);
    }

    @Override
    public void remove(Component component) {
        mComponents.remove(component);
    }

    @Override
    public void display(int level) {
        System.out.println(String.format("%0" + level + "d", 0).replace("0", "-") + name);
        for (int i = 0; i < mComponents.size(); i++) {
            Component component = mComponents.get(i);
            component.display(level + 2);
        }
    }
}
