package org.crowdev;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Utils {
	
	public static String srcPathToFilePath(String srcDir, String srcPath)
	{
		String path = srcDir + File.separator + srcPath.replace("/", File.separator);
		if (!path.endsWith(".java"))
			path = path + ".java";
		return path;
	}
	
	public static void makeDir(String path)
	{
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
	}

	public static void fileCopy(File s, File t) {
		
		System.out.println("Copy to: " + t.getPath());
		File tp = t.getParentFile();
		if (!tp.exists())
			tp.mkdirs();
		
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;

		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// �õ���Ӧ���ļ�ͨ��
			out = fo.getChannel();// �õ���Ӧ���ļ�ͨ��
			in.transferTo(0, in.size(), out);// ��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
