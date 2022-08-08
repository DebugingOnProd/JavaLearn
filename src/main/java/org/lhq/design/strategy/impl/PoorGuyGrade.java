package org.lhq.design.strategy.impl;

import org.lhq.design.strategy.GradeStrategy;

import java.math.BigDecimal;

public class PoorGuyGrade implements GradeStrategy {
    @Override
    public BigDecimal calPrice(BigDecimal originalPrice) {
        return originalPrice.multiply(new BigDecimal("0.8"));
    }
}
