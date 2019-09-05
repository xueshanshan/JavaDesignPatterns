package com.star.design_patterns.factory_pattern.abstract_factory;

import com.star.design_patterns.factory_pattern.Operation;

/**
 * @author xueshanshan
 * @date 2019-09-05
 */
public class FactorySimpleInvoke {
    public static Operation createOperation(String packageName, String className) {
        try {
            String name = packageName + "." + className;
            return (Operation) Class.forName(name).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Tool createTool(String packageName, String className) {
        try {
            String name = packageName + "." + className;
            return (Tool) Class.forName(name).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
