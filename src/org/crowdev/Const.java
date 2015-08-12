package org.crowdev;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Const {
	
	public static void main(String[] args)
	{
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			System.out.println("构建完整项目完整项目");
			System.out.println("输出目录: F:\\output\\CrowDevTest");
			try {
				String in = strin.readLine();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public static final String JAVA_EXT = ".java";
	
	public static final String PROJECT_FILES_HOME = File.separator + "files";
	
}
