package org.lhq.design.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cricket extends Game{
    @Override
    void initialize() {
        log.info("游戏初始化完毕，可以开始游戏");
    }

    @Override
    void startPlay() {
        log.info("开始游戏");
    }

    @Override
    void endPlay() {
        log.info("游戏结束");
    }
}
