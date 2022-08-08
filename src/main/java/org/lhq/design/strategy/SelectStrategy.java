package org.lhq.design.strategy;

import java.math.BigDecimal;

public class SelectStrategy {
    private GradeStrategy strategy;

    public SelectStrategy (GradeStrategy strategy){
        this.strategy = strategy;
    }
    public BigDecimal executeCal(BigDecimal originalPrice){
        return  strategy.calPrice(originalPrice);
    }
}
