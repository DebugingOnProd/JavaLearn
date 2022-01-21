package org.lhq.jdbc.wrappers.lambda;

import org.lhq.jdbc.wrappers.AbsWrappers;

public abstract class AbstractLambdaWrapper<T,Chrildren extends AbstractLambdaWrapper<T,Chrildren>>
 extends AbsWrappers<T,SFunction<T,?>,Chrildren> {

}
