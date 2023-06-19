package com.trch.config;

import java.io.*;

public class ResourcesReader {
	
	public static InputStream readFile (String fileName) {
		
		ResourcesReader reader = new ResourcesReader();
		
		InputStream is = reader.getFileFromResourcesAsStream(fileName);
		
		return is;
		
	}
	
	private InputStream getFileFromResourcesAsStream (String fileName) {
		
		ClassLoader classLoader = getClass().getClassLoader();
		
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        
        if (inputStream == null) {
        	
            throw new IllegalArgumentException("файл " + fileName + " не найден");
            
        } else {
        	
            return inputStream;
        }
	}

}
