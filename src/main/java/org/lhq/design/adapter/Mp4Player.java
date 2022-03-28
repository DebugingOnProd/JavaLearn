package org.lhq.design.adapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mp4Player implements AdvancedMediaPlayer {
	@Override
	public void playVlc(String fileName) {

	}

	@Override
	public void playMp4(String fileName) {
		log.info("播放mp4文件. Name: "+ fileName);
	}
}
