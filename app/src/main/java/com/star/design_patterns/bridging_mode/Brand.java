package com.star.design_patterns.bridging_mode;

/**
 * @author xueshanshan
 * @date 2019-09-02
 *
 * 电脑品牌分类
 */
public interface Brand {
    void sale();


    /**
     * 戴尔品牌
     */
    public static class Dell implements Brand {
        @Override
        public void sale() {
            System.out.println("出售戴尔电脑");
        }
    }


    /**
     * 联想品牌
     */
    public class Lenovo implements Brand {
        @Override
        public void sale() {
            System.out.println("出售联想电脑");
        }
    }
}
