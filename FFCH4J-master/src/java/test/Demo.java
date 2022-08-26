package java.test;

import cc.eguid.commandManager.CommandManager;
import cc.eguid.commandManager.CommandManagerImpl;
import cc.eguid.commandManager.commandbuidler.CommandBuidlerFactory;
import cc.eguid.commandManager.data.CommandTasker;

public class Demo {
	public static void main(String[] args) {
		 String cameraId = "test";
		 CommandManager manager = new CommandManagerImpl();
		 CommandTasker commandTasker = manager.query(cameraId);
		 if(commandTasker == null) {
			 manager.start(cameraId, CommandBuidlerFactory.createBuidler()
					 .add("ffmpeg")
					 .add("-rtsp_transport", "tcp")
					 .add("-i", "C:/Users/1/Desktop/111/20220825.flv")
					 .add("-vcodec", "copy")
					 .add("-acodec", "copy")
					 .add("-f", "flv")
					 .add("rtmp://192.168.23.3/live/livestream" + cameraId));
				 }
	}
}
