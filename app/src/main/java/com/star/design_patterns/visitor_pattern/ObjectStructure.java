package com.star.design_patterns.visitor_pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xueshanshan
 * @date 2019-10-15
 */
//对象结构角色
public class ObjectStructure {
    private List<Element> elements = new ArrayList<>();

    public void add(Element element) {
        elements.add(element);
    }

    public void remote(Element element) {
        elements.remove(element);
    }

    public void accept(Visitor visitor) {
        Iterator<Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            element.accept(visitor);
        }
    }
}
