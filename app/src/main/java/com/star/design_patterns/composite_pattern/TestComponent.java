package com.star.design_patterns.composite_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-04
 */
public class TestComponent {

    public static void main(String[] args) {
        Composite root = new Composite("Root");
        root.add(new Leaf("Leaf A"));
        root.add(new Leaf("Leaf B"));

        Composite comp = new Composite("Composite X");
        comp.add(new Leaf("Leaf XA"));
        comp.add(new Leaf("Leaf XB"));

        root.add(comp);

        Composite comp2 = new Composite("Composite XY");
        comp2.add(new Leaf("Leaf XYA"));
        comp2.add(new Leaf("Leaf XYB"));

        comp.add(comp2);

        root.add(new Leaf("Leaf C"));
        Leaf leaf_d = new Leaf("Leaf D");
        root.add(leaf_d);
        root.remove(leaf_d);

        root.display(1);
    }
}
