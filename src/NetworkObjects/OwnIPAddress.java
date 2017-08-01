package NetworkObjects;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OwnIPAddress {
	
	
//	public static void main(String[] args) {
//		OwnIPAddress ip = new OwnIPAddress();
//		System.out.println(ip.getIP());
//	}
//	
	public static String getIP() {
		
		String ip = "";
		
		InetAddress ias;
		try {
			ias = InetAddress.getLocalHost();
			if(ias != null) {
					ip = ias.getHostAddress();
				}
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ip;
	}
	
	public static String getOwnerHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

}
