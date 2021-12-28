package org.lhq.designPattern.strategy;

public class OperationAdd implements Operation {
    @Override
    public int operate(int mun,int m) {
        return  m+mun;
    }
}
