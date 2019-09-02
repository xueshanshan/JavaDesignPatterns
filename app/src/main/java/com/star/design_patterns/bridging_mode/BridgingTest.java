package com.star.design_patterns.bridging_mode;

/**
 * @author xueshanshan
 * @date 2019-09-02
 */
public class BridgingTest {

    public static void main(String[] args) {
        //联想台式电脑
        Computer computer = new Computer.Desktop(new Brand.Lenovo());
        computer.sale();

        //戴尔台式电脑
        computer = new Computer.Desktop(new Brand.Dell());
        computer.sale();

        //联想笔记本
        computer = new Computer.Laptop(new Brand.Lenovo());
        computer.sale();

        //戴尔笔记本
        computer = new Computer.Laptop(new Brand.Dell());
        computer.sale();
    }
}
