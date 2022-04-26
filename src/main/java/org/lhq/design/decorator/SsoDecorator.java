package org.lhq.design.decorator;

public  abstract class SsoDecorator {
    private HandlerInterceptor handlerInterceptor;
    private SsoDecorator(){}
    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }
    public boolean preHandle(String request, String response, Object handler) {
        return handlerInterceptor.preHandle(request, response, handler);
    }
}
