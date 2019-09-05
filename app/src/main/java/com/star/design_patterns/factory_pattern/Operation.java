package com.star.design_patterns.factory_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-05
 *
 * 定义一个操作接口
 */
public interface Operation {
    public double getResult(double num1, double num2);

    /**
     * 加法类
     */
    public static class AddOperation implements Operation {

        @Override
        public double getResult(double num1, double num2) {
            return num1 + num2;
        }
    }

    /**
     * 减法类
     */
    public static class SubOperation implements Operation {

        @Override
        public double getResult(double num1, double num2) {
            return num1 - num2;
        }
    }

    /**
     * 乘法类
     */
    public static class MulOperation implements Operation {

        @Override
        public double getResult(double num1, double num2) {
            return num1 * num2;
        }
    }

    /**
     * 乘法类
     */
    public static class DivOperation implements Operation {

        @Override
        public double getResult(double num1, double num2) {
            if (num2 == 0) {
                throw new RuntimeException("除数不能为0");
            }
            return num1 / num2;
        }
    }
}
