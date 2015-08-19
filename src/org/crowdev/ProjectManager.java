package org.crowdev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import org.crowdev.DAO.SourceFileDAO;
import org.crowdev.model.Arch;
import org.crowdev.model.SourceFile;
import org.crowdev.model.SourceFileImp;
import org.crowdev.model.WorkContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class ProjectManager {
	
	public void makeWorkProject(WorkContext workContext, String destPath, SourcePlan sourcePlan)
	{
		String projectPath = destPath;
		String srcPath = projectPath + File.separator + "src";
		Utils.makeDir(srcPath);
		List<SourceFile> srcFiles = workContext.getSourceFiles();
		System.out.println("srcFiles count = " + srcFiles.size());
		for (SourceFile sourceFile: srcFiles)
		{
			String sourcePath = sourceFile.getFilePath();
			if (sourcePath != null)
			{
				SourceFileImp sourceFileImp = sourcePlan.getPlan().get(sourceFile);
				if (sourceFileImp != null)
					sourcePath = sourceFileImp.getImp_file_path();
			}
			Utils.fileCopy(new File(sourcePath),
					new File(Utils.srcPathToFilePath(srcPath, sourceFile.getSrcPath())));
		}
		makeDotProject(workContext.getName(), projectPath + File.separator + ".project");
		makeDotClasspath(projectPath + File.separator + ".classpath");
		makeCrowDevConfig(workContext, projectPath + File.separator + "crowdev.xml");
	}
	
	public void makeFinalProject(Arch arch, String destPath)
	{
		List<WorkContext> workContexts = arch.getWorkContexts();
		for (WorkContext workContext: workContexts)
		{
			// default
			SourcePlan sourcePlan = SourcePlan.defaultPlan(workContext);
			makeWorkProject(workContext, destPath + File.separator + arch.getName(), sourcePlan);
		}
	}
	
	private void makeCrowDevConfig(WorkContext workContext, String destFile)
	{	
		String interfaceSrc = null;
		String classSrc = null;
		String testCaseSrc = null;
		
		int interfaceId = -1;
		int classId = -1;
		int testCaseId = -1;
		for (SourceFile sourceFile: workContext.getSourceFiles())
		{
			System.out.println(sourceFile.getType());
			System.out.println(sourceFile.getSrcPath());
			if (sourceFile.getType() == SourceFile.TYPE_INTERFACE)
			{
				interfaceSrc = sourceFile.getSrcPath();
				interfaceId = sourceFile.getId();
			}
			else if (sourceFile.getType() == SourceFile.TYPE_WORK_CLASS)
			{
				classSrc = sourceFile.getSrcPath();
				classId = sourceFile.getId();
			}
			else if (sourceFile.getType() == SourceFile.TYPE_TEST_CASE)
			{
				testCaseSrc = sourceFile.getSrcPath();
				testCaseId = sourceFile.getId();
			}
		}
		
		Document document = DocumentHelper.createDocument();
		Element CrowDev = document.addElement("CrowDev");
		Element WorkContext = CrowDev.addElement("WorkContext");
		WorkContext.addAttribute("name", workContext.getName());
		WorkContext.addAttribute("id", String.valueOf(workContext.getId()));
		Element Interface = WorkContext.addElement("Interface");
		Interface.addAttribute("src", interfaceSrc);
		Interface.addAttribute("id", String.valueOf(interfaceId));
		Element Class = WorkContext.addElement("Class");
		Class.addAttribute("src", classSrc);
		Class.addAttribute("id", String.valueOf(classId));
		Element TestCase = WorkContext.addElement("TestCase");
		TestCase.addAttribute("src", testCaseSrc);
		TestCase.addAttribute("id", String.valueOf(testCaseId));
		
		XMLWriter output;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
	        output = new XMLWriter(new FileWriter(new File(destFile)), format);
	        output.write(document);
	        output.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private void makeDotClasspath(String destFile)
	{
		Document document = DocumentHelper.createDocument();
		Element classpath = document.addElement("classpath");
		
		Element classpathentry1 = classpath.addElement("classpathentry");
		classpathentry1.addAttribute("kind", "src");
		classpathentry1.addAttribute("path", "src");
		
		Element classpathentry2 = classpath.addElement("classpathentry");
		classpathentry2.addAttribute("kind", "con");
		classpathentry2.addAttribute("path", "org.eclipse.jdt.launching.JRE_CONTAINER/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/JavaSE-1.7");
		
		Element classpathentry3 = classpath.addElement("classpathentry");
		classpathentry3.addAttribute("kind", "con");
		classpathentry3.addAttribute("path", "org.eclipse.jdt.junit.JUNIT_CONTAINER/4");
		
		Element classpathentry4 = classpath.addElement("classpathentry");
		classpathentry4.addAttribute("kind", "output");
		classpathentry4.addAttribute("path", "bin");
		
		XMLWriter output;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
	        output = new XMLWriter(new FileWriter(new File(destFile)), format);
	        output.write(document);
	        output.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private void makeDotProject(String projectName, String destFile)
	{
		Document document = DocumentHelper.createDocument();
		Element rootElement = document.addElement("projectDescription");
		
		Element name = rootElement.addElement("name");
		name.addText(projectName);
		
		Element comment = rootElement.addElement("comment");
		Element projects = rootElement.addElement("projects");
		
		Element buildSpec = rootElement.addElement("buildSpec");
		Element buildCommand = buildSpec.addElement("buildCommand");
		Element buildCommandName = buildCommand.addElement("name");
		buildCommandName.setText("org.eclipse.jdt.core.javabuilder");
		Element buildCommandarguments = buildCommand.addElement("arguments");
		
		Element natures = rootElement.addElement("natures");
		Element nature = natures.addElement("nature");
		nature.setText("org.eclipse.jdt.core.javanature");
		
		XMLWriter output;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
	        output = new XMLWriter(new FileWriter(new File(destFile)), format);
	        output.write(document);
	        output.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
}
