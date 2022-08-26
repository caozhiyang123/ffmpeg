package cc.eguid.commandManager.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cc.eguid.commandManager.CommandManager;
import cc.eguid.commandManager.CommandManagerImpl;
import cc.eguid.commandManager.commandbuidler.CommandBuidlerFactory;
import cc.eguid.commandManager.data.CommandTasker;
/**
 * demo
 * @author michael
 * @since jdk1.8
 * @version 2022年8月26日
 */
public class Test {
	/**
	 * 命令组装器测试
	 * @throws InterruptedException
	 */
	public static void test1() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		Map<String,String> map = new HashMap<String,String>();
		map.put("appName", "test123");
		map.put("input", "rtsp://192.168.23.3:37779/cam/realmonitor?channel=1&subtype=0");
		map.put("output", "rtmp://192.168.23.3/live/livestream");
		map.put("codec", "h264");
		map.put("fmt", "flv");
		map.put("fps", "25");
		map.put("rs", "960x544");
		map.put("twoPart", "2");
		// 执行任务，id就是appName，如果执行失败返回为null
		String id = manager.start(map);
		System.out.println(id);
		// 通过id查询
		CommandTasker info = manager.query(id);
		System.out.println("id"+info);
		// 查询全部
		Collection<CommandTasker> infoList = manager.queryAll();
		System.out.println(infoList);
		Thread.sleep(30000);
		// 停止id对应的任务
//		 manager.stop(id);
	}
	
	/**
	 * use cmd
	 * ffmpeg -re -i ../../video/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream
	 * ffmpeg from local path ->CommandManager.ProgramConfig.path
	 * // 是否有必输项：应用名-appName，拉流地址-input，推流地址-output，twoPart：0-推一个元码流；1-推一个自定义推流；2-推两个流（一个是自定义，一个是元码）
	 */
	public static void test11() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		Map<String,String> map = new HashMap<String,String>();
		map.put("appName", "test11");
		map.put("input", "C:/Users/1/Desktop/111/20220825.flv");
		map.put("output", "rtmp://192.168.23.3/live/livestream");
		map.put("twoPart", "0");
		map.put("codec", "h264");
		map.put("fmt", "flv");
		map.put("fps", "25");
		map.put("rs", "960x544");
		// 执行任务，id就是appName，如果执行失败返回为null
		String id = manager.start(map);
		System.out.println(id);
		// 通过id查询
		CommandTasker info = manager.query(id);
		System.out.println("id"+info);
		// 查询全部
		Collection<CommandTasker> infoList = manager.queryAll();
		System.out.println(infoList);
		Thread.sleep(30000);
		// 停止id对应的任务
//		 manager.stop(id);
	}
	
	/**
	 * default use cmd to push/play video/audio
	 * ffmpeg from local path ->CommandManager.ProgramConfig.path
	 */
	public static void test2() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		manager.start("tomcat", "ffmpeg -re -i C:/Users/1/Desktop/111/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream");
		Thread.sleep(30000);
		manager.stopAll();
	}
	
	/**
	 * default use cmd to push/play video/audio
	 * ffmpeg from current project src path
	 */
	public static void test3() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		// -rtsp_transport tcp 
		//默认方式发布任务
//		manager.start("tomcat", "D:/TestWorkspaces/FFmpegCommandHandler/src/cc/eguid/FFmpegCommandManager/ffmpeg/ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat",true);
		manager.start("tomcat", "E:/ffmpeg/FFCH4J-master/config/windows/ffmpeg -re -i C:/Users/1/Desktop/111/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream",true);
		
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	
	/**
	 * rtsp-rtmp转流多任务测试
	 * @throws InterruptedException
	 */
	public static void test4() throws InterruptedException{
		CommandManager manager = new CommandManagerImpl();
		// -rtsp_transport tcp 
		//测试多个任何同时执行和停止情况
		//false表示使用配置文件中的ffmpeg路径，true表示本条命令已经包含ffmpeg所在的完整路径
//		manager.start("tomcat", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat",false);
//		manager.start("tomcat1", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat1",false);
//		manager.start("tomcat2", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat2",false);
//		manager.start("tomcat3", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat3",false);
//		manager.start("tomcat4", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat4",false);
//		manager.start("tomcat5", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat5",false);
//		manager.start("tomcat6", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat6",false);
//		manager.start("tomcat7", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat7",false);
//		manager.start("tomcat8", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat8",false);
//		manager.start("tomcat9", "ffmpeg -i rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov -vcodec copy -acodec copy -f flv -y rtmp://106.14.182.20:1935/rtmp/tomcat9",false);
		
		manager.start("tomcat1", "E:/ffmpeg/FFCH4J-master/config/windows/ffmpeg -re -i C:/Users/1/Desktop/111/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream",true);
		manager.start("tomcat2", "E:/ffmpeg/FFCH4J-master/config/windows/ffmpeg -re -i C:/Users/1/Desktop/111/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream",true);
		manager.start("tomcat3", "E:/ffmpeg/FFCH4J-master/config/windows/ffmpeg -re -i C:/Users/1/Desktop/111/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream",true);
		//发现很快就停止
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	
	/**
	 * 测试流式命令行构建器
	 * @throws InterruptedException
	 */
	public static void testStreamCommandAssmbly() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://192.168.23.3/live/livestream/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	
	/**
	 * 测试流式命令行构建器
	 * ffmpeg -re -i ../../video/20220825.flv -c copy -f flv rtmp://192.168.23.3/live/livestream
	 */
	public static void test2StreamCommandAssmbly() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg")
				.add("-re")
				.add("-i","C:/Users/1/Desktop/111/20220825.flv")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("rtmp://192.168.23.3/live/livestream"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
	}
	
	/**
	 * 测试任务中断自动重启任务
	 * @throws InterruptedException 
	 */
	public static void testBroken() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","C:/Users/1/Desktop/111/20220825.flv")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test1"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
		manager.destory();
	}
	
	/**
	 * 批量测试任务中断自动重启任务
	 * @throws InterruptedException 
	 */
	public static void testBrokenMuti() throws InterruptedException {
		CommandManager manager = new CommandManagerImpl();
		manager.start("test1", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test1"));
		manager.start("test2", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test2"));
		manager.start("test3", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test3"));
		manager.start("test4", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test4"));
		manager.start("test5", CommandBuidlerFactory.createBuidler()
				.add("ffmpeg").add("-i","rtsp://184.72.239.149/vod/mp4://BigBuckBunny_175k.mov")
//				.add("-rtsp_transport","tcp")
				.add("-vcodec","copy")
				.add("-acodec","copy")
				.add("-f","flv")
				.add("-y").add("rtmp://106.14.182.20:1935/rtmp/test5"));
		Thread.sleep(30000);
		// 停止全部任务
		manager.stopAll();
		manager.destory();
	}
	
	public static void main(String[] args) throws InterruptedException {
//		test1();
//		test11();
//		test2();
//		test3();
//		test4();
//		testStreamCommandAssmbly();
		test2StreamCommandAssmbly();
//		testBroken();
//		testBrokenMuti();
	}
		
}
