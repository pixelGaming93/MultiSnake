package Logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class FormatForOutput extends Formatter{

	@Override
	public String format(LogRecord record) {
		String ret = "";
		SimpleDateFormat df = new SimpleDateFormat(" dd MMM yyyy HH:mm ");
		Date d = new Date(record.getMillis());
		
		if(record.getLevel().intValue() >= Level.WARNING.intValue()) {
//			ret += "WARNING!: ";
//			ret += "\r\n";
			ret = addMes(ret,record,df,d);
//		}else if(record.getLevel().intValue() == Level.SEVERE.intValue()) {
//			ret += "SEVERE!: ";
//			ret += "\r\n";
//			ret = addMes(ret,record,df,d);
//		}else if(record.getLevel().intValue() == Level.INFO.intValue()) {
//			ret += "INFO!: ";
//			ret += "\r\n";
//			ret = addMes(ret,record,df,d);
		}else {
			
		}
		return ret;
	}
	
	public String addMes(String ret, LogRecord record, SimpleDateFormat df, Date d) {
		ret += record.getLevel();
		ret += df.format(d);
		
		ret += this.formatMessage(record);
		ret += "\r\n";
		ret += "\r\n";
		
		return ret;
	}

}
