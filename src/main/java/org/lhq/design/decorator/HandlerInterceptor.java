package org.lhq.design.decorator;

public interface HandlerInterceptor {
    boolean preHandle(String request, String response, Object handler);
}
