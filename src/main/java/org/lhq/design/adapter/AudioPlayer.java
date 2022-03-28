package org.lhq.design.adapter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AudioPlayer implements MediaPlayer {

	private static final Logger log = LoggerFactory.getLogger(AudioPlayer.class);

	MediaAdapter mediaAdapter;




	@Override
	public void play(String audioType, String fileName) {

		//播放 mp3 音乐文件的内置支持
		if(audioType.equalsIgnoreCase("mp3")){
			log.info("播放mp3文件. Name: {}",fileName);
		}
		//mediaAdapter 提供了播放其他文件格式的支持
		else if(audioType.equalsIgnoreCase("vlc")
				|| audioType.equalsIgnoreCase("mp4")){
			mediaAdapter = new MediaAdapter(audioType);
			mediaAdapter.play(audioType, fileName);
		}
		else{
			log.info("无效的 媒体.{} 格式不受支持",audioType);
		}
	}
}
