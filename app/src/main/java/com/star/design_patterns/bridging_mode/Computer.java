package com.star.design_patterns.bridging_mode;

/**
 * @author xueshanshan
 * @date 2019-09-02
 *
 * 电脑类型分类
 */
public class Computer {
    protected Brand mBrand;

    public Computer(Brand brand) {
        mBrand = brand;
    }

    public void sale() {
        mBrand.sale();
    }


    /**
     * 台式电脑
     */
    public static class Desktop extends Computer {

        public Desktop(Brand brand) {
            super(brand);
        }

        @Override
        public void sale() {
            super.sale();
            System.out.println("出售台式电脑");
        }
    }


    /**
     * 笔记本电脑
     */
    public static class Laptop extends Computer {

        public Laptop(Brand brand) {
            super(brand);
        }

        @Override
        public void sale() {
            super.sale();
            System.out.println("出售笔记本");
        }
    }
}
