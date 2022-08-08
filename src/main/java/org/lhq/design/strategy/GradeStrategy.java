package org.lhq.design.strategy;

import java.math.BigDecimal;

public interface GradeStrategy {
    BigDecimal calPrice(BigDecimal originalPrice);
}
