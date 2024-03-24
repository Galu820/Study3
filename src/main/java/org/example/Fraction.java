package org.example;

import java.lang.reflect.Proxy;

public class Fraction implements Fractionable {
    public Object getProxy() {
        Class cls = this.getClass();
        return Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[]{Fractionable.class},
                new FractionableInvHandler(this));
    }
    private volatile int num;
    private volatile int denum;
    public Fraction(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }
    @Mutator
    public synchronized void setNum(int num) {
        System.out.println("--- setNum = "+ num);
        this.num = num;
    }

    @Mutator
    public synchronized void setDenum(int denum) {
        System.out.println("--- setDenum = "+ denum);
        this.denum = denum;
    }
    @Override
    @Cache
        public synchronized double doubleValue() {
        //System.out.println("invoke double value");
        return (double) num/denum;
    }
}
