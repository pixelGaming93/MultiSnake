package Logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeLogger {
	
	public Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public SnakeLogger() {
		Logger root = Logger.getLogger("");
		FileHandler txt = null;
		try {
			txt = new FileHandler("SnakeLog.txt");
		}catch(SecurityException | IOException e) {
			e.printStackTrace();
		}
		root.setLevel(Level.ALL);
		txt.setFormatter(new FormatForOutput());
		root.addHandler(txt);
	}
	
	public String getException(String name, String msg, StackTraceElement[] ste) {
		String ret = name;
		ret += "\r\n";
		ret += msg;
		ret += "\r\n";
		
		for(StackTraceElement s : ste) {
			ret += s.toString();
			ret += "\r\n";
		}
		
		return ret;
	}

}
