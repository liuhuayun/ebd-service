package cn.comtom.cbs.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuhy
 *
 */
@Slf4j
public class VLCUtils {
	
	public  static final String CHANGE_CMD = "ffmpeg -i SRC_URL -vcodec flv  -acodec aac -ar 44100 -strict -2 -f flv  DEST_URL";
	
	public  static final String STOP_FFMPEG = "ps -ef | grep ffmpeg | grep -v grep | awk '{print $2}' |xargs kill -9";
	
	public static void UDP2RTMP(String srcUrl, String desUrl) {
		runSh(STOP_FFMPEG);
		String cmd = CHANGE_CMD.replace("SRC_URL", srcUrl).replace("DEST_URL", desUrl);
		log.info("run cmd command start : {}", cmd);
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec(cmd);
			InputStream in = process.getInputStream();
			BufferedReader bs = new BufferedReader(new InputStreamReader(in));
			// System.out.println("[check] now size \n"+bs.readLine());
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[8192];
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			log.info("run cmd command end : {}", cmd);
			in.close();
			// process.waitFor();
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String runSh(String command) {
		String returnString = "success";
		log.info("run cmd command start : {}", command);
		try {
			String[] cmds = {"/bin/sh","-c", STOP_FFMPEG};  
			Process pro = Runtime.getRuntime().exec(cmds);  
			 pro.waitFor();  
			InputStream in = pro.getInputStream();  
			BufferedReader read = new BufferedReader(new InputStreamReader(in));  
			String line = null;  
			while((line = read.readLine())!=null){  
			    System.out.println(line);  
			}  
			log.info("run cmd command end : {}", command);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    return returnString;
		
	}
}
