package org.crowdev;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crowdev.DAO.ArchDAO;
import org.crowdev.DAO.SourceFileDAO;
import org.crowdev.DAO.WorkContextDAO;
import org.crowdev.model.Arch;
import org.crowdev.model.SourceFile;
import org.crowdev.model.WorkContext;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigXMLParser {

	private File projectPath;
	private File configFile;
	private Arch arch;
	private List<WorkContext> workContexts = new ArrayList<>();
	private ProjectScanner scanner;
	
	
	public ConfigXMLParser(File projectPath) {
		this.projectPath = projectPath;
		scanner = new ProjectScanner(projectPath);
		configFile = scanner.getConfigFile();
		System.out.println("configFile = " + configFile.getPath());
		parseConfigFile();
	}
	
	private void parseSourceFile(WorkContext workContext, Element element, String type)
	{
		List interfaces = element.elements(type);
		for(Iterator it=interfaces.iterator();it.hasNext();){       
	        Element e = (Element) it.next();
	        Attribute srcAttr = e.attribute("src");
	        String srcString = srcAttr.getValue();
	        
	        // Scan the file
	        File file = scanner.getFileBySrcPath(srcString);
	        SourceFileDAO sourceFileDAO = new SourceFileDAO();
	        sourceFileDAO.saveSourceFile(new SourceFile(workContext.getId(), file.getName(),
	        		srcString, file.getPath()));
		} 
	}
	
	private void parseClassContextElement(Element element)
	{
		Attribute nameAttr = element.attribute("name");
		String nameString = nameAttr.getValue();
		WorkContext workContext = new WorkContext(nameString, arch.getId());
		workContexts.add(workContext);
		
		// Persistant WorkContext
		WorkContextDAO workContextDAO = new WorkContextDAO();
		workContextDAO.saveWorkContext(workContext);
		
		parseSourceFile(workContext, element, "Interface");
		parseSourceFile(workContext, element, "Class");
		parseSourceFile(workContext, element, "TestCase");
	}
	
	private void parseArchContextElement(Element element)
	{
		Attribute nameAttr = element.attribute("name");
		String nameString = nameAttr.getValue();
		System.out.println("arch name = " + nameString);
		this.arch = new Arch(nameString);
		
		// Persistant Arch
		ArchDAO dao = new ArchDAO();
		dao.saveArch(arch);

		List elements = element.elements("WorkContext");
		for(Iterator it=elements.iterator();it.hasNext();){       
	        Element e = (Element) it.next();
	        parseClassContextElement(e);
		}  
	}

	public void parseConfigFile()
	{
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(configFile);
			Element root = document.getRootElement();
			List elements = root.elements("ArchContext");
			System.out.println("Arch Elements Count = " + elements.size());
			for(Iterator it=elements.iterator();it.hasNext();){
				
		        Element element = (Element) it.next();
		        parseArchContextElement(element);
			}  
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
