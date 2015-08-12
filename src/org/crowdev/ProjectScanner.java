package org.crowdev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.crowdev.model.SourceFile;

public class ProjectScanner {

	private File rootDir;
	private File srcDir;
	private File configFile;
	private List<SourceFile> javaFiles;
	
	
	public ProjectScanner(File rootDir) {
		this.rootDir = rootDir;
		init();
	}
	
	public File getFileBySrcPath(String srcPath)
	{
		File f = new File(Utils.srcPathToFilePath(srcDir.getPath(), srcPath));
		return f;
	}
	
	public void init()
	{
		// Find src dir.
		srcDir = findDirByName(rootDir, "src").get(0);
		System.out.println("srcDir = " + srcDir.getPath());
		// Find config file.
		configFile = findDirByName(rootDir, "crowdev.xml").get(0);
	}
	
//	public void scanSrcFile()
//	{
//		javaFiles = scanSourceFile(srcDir, "");
//		System.out.println("Find java files: ");
//		for (SourceFile f: javaFiles)
//		{
//			System.out.println(f.getFile().getName());
//			System.out.println(f.getRelPath());
//		}
//	}
//	
//	private List<SourceFile> scanSourceFile(File dir, String relPath)
//	{
//		List<SourceFile> ans = new ArrayList<SourceFile>();
//		String[] list = dir.list();
//		for (String item: list)
//		{
//			File f = new File(dir.getPath() + File.separator + item);
//			if (f.isDirectory())
//				ans.addAll(scanSourceFile(f, relPath + File.separator + f.getName()));
//			else if (f.getName().endsWith(Const.JAVA_EXT))
//				ans.add(new SourceFile(f, relPath));
//		}
//		return ans;
//	}
	
	private List<File> findDirByName(File dir, String name)
	{
		List<File> ans = new ArrayList<File>();
		String[] list = dir.list();
		for (String item: list)
		{
			File f = new File(dir.getPath() + File.separator + item);
			if (f.getName().equals(name))
				ans.add(f);
			if (f.isDirectory())
				ans.addAll(findDirByName(f, name));
		}
		return ans;
	}

	public List<SourceFile> getJavaFiles() {
		return javaFiles;
	}

	public void setJavaFiles(List<SourceFile> javaFiles) {
		this.javaFiles = javaFiles;
	}

	public File getConfigFile() {
		return configFile;
	}

	public void setConfigFile(File configFile) {
		this.configFile = configFile;
	}
	
	
	
	
}
