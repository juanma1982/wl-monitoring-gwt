package wlstatistics.server;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import wlstatistics.shared.model.Domain;

public class PropertiesReader {

	public static ArrayList<Domain> getDomains() {
		

		Properties prop = new Properties();
		for (Enumeration e = prop.keys(); e.hasMoreElements() ; ) {
		    // Obtenemos el objeto
		    Object obj = e.nextElement();
		    System.out.println(obj + ": " + prop.getProperty(obj.toString()));
		}
		// TODO Auto-generated method stub
		return null;
	}

}
