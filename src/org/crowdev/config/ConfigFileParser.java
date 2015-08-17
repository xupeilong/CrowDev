package org.crowdev.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crowdev.ProjectScanner;
import org.crowdev.config.action.Action;
import org.crowdev.model.Arch;
import org.crowdev.model.WorkContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigFileParser {
	
	private File projectPath;
	private File configFile;
	private Arch arch;
	private List<WorkContext> workContexts = new ArrayList<>();
	private ProjectScanner scanner;

	public ConfigFileParser(File projectPath) {
		this.projectPath = projectPath;
		scanner = new ProjectScanner(projectPath);
		configFile = scanner.getConfigFile();
	}
	
	private void parseWithTarget(Element element, Target target, TargetInstance parent)
	{
		TargetInstance targetInstance = TargetInstance.fromTarget(target, element, parent);
		Action action = Context.actionSet.get(targetInstance.getName());
		if (action != null)
			action.act(targetInstance);
		
		List<Element> elements = element.elements();
		for (Element e: elements)
		{
			Target subTarget = target.findSubTarget(e.getName());
			parseWithTarget(e, subTarget, targetInstance);
		}
	}
	
	public void parse()
	{
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(configFile);
			Element root = document.getRootElement();
			parseWithTarget(root, Context.archConfigTarget, null);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
