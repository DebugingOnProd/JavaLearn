package org.lhq.design.corp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorLogger extends AbstractLogger{
    public ErrorLogger(int level){
        this.level = level;
    }
    @Override
    protected void write(String message) {
        log.error("错误输出::{}",message);
    }
}
