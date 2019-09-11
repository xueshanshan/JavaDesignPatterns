package com.star.design_patterns.singleton_pattern;

/**
 * @author xueshanshan
 * @date 2019-09-11
 */
public class Singleton {

    private Singleton() {
    }

    //----1、饿汉式----
    private static Singleton mSingleton = new Singleton();

    public static Singleton getInstance() {
        return mSingleton;
    }


    //---2、静态代码块饿汉式---
    private static Singleton mSingleton2;

    static {
        mSingleton2 = new Singleton();
    }

    public static Singleton getInstance2() {
        return mSingleton2;
    }

    //---3、懒汉式（线程不安全）---
    private static Singleton mSingleton3;

    public static Singleton getInstance3() {
        if (mSingleton3 == null) {
            mSingleton3 = new Singleton();
        }
        return mSingleton3;
    }

    //---4、懒汉式（线程安全,延迟加载，推荐使用）----
    private volatile static Singleton mSingleton4;

    public static Singleton getInstance4() {
        if (mSingleton3 == null) {
            synchronized (Singleton.class) {
                if (mSingleton3 == null) {
                    mSingleton3 = new Singleton();
                }
            }
        }
        return mSingleton3;
    }

    //---5、静态内部类（线程安全,延迟加载，推荐使用）---
    private static class Holder {
        private static Singleton mSingleton = new Singleton();
    }

    public static Singleton getInstance5() {
        return Holder.mSingleton;
    }
}
