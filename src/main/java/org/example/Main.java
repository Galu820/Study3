package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fraction fr = new Fraction(3,2);
        System.out.println(fr.doubleValue());
        fr.setNum(4);
        //System.out.println(fr.doubleValue());
        //System.out.println(fr.doubleValue());
        System.out.println("-----Use CACHE-----");
        Fractionable fr_cached = (Fractionable)fr.getProxy();
        System.out.println(fr_cached.doubleValue());
        System.out.println(fr_cached.doubleValue());
        fr.setNum(80);
        System.out.println(fr.doubleValue());
        System.out.println(fr.doubleValue());
        System.out.println(fr_cached.doubleValue());
        System.out.println(fr_cached.doubleValue());
        fr.setDenum(8);
        System.out.println(fr_cached.doubleValue());
        System.out.println(fr_cached.doubleValue());
        fr_cached.setNum(40);
        System.out.println(fr_cached.doubleValue());
        System.out.println(fr_cached.doubleValue());
        Thread.sleep(1500);
        System.out.println(fr_cached.doubleValue());
        System.out.println(fr_cached.doubleValue());
    }
}