package org.lhq.design.decorator;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class LoginSsoDecorator  extends SsoDecorator{
    private static final Map<String, String> authMap = new ConcurrentHashMap<>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }


    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean result = super.preHandle(request, response, handler);
        if (!result) return false;
        String userId = request.substring(8);
        String method = authMap.get(userId);
        log.info("登录校验{},{}",userId,method);
        return "queryUserInfo".equals(method);
    }
}
