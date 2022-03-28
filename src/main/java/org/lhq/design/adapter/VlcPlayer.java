package org.lhq.design.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VlcPlayer implements AdvancedMediaPlayer {
	@Override
	public void playVlc(String fileName) {
		log.info("播放 VLC 文件. Name: "+ fileName);
	}

	@Override
	public void playMp4(String fileName) {
		//什么也不做
	}
}
