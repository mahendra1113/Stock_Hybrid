package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileutil {
	public static String getvaluekey(String key) throws Exception{
		Properties value=new Properties();
		FileInputStream fi=new FileInputStream("D:\\mahiselenium\\Stockaccounting_BDD\\propertyfiles\\Repository.properties");
		value.load(fi);
		
       return (String) value.get(key);
	
	
	}
	
}
