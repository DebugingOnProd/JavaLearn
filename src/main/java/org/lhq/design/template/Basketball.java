package org.lhq.design.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Basketball extends Game{
    @Override
    void initialize() {
       log.info("篮球比赛初始化完毕");
    }

    @Override
    void startPlay() {
        log.info("篮球比赛开始");
    }

    @Override
    void endPlay() {
        log.info("篮球比赛结束");
    }
}
