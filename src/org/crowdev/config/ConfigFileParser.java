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
	
	private Context context;
	private File configFile;
	private Arch arch;
	private List<WorkContext> workContexts = new ArrayList<>();

	public ConfigFileParser(Context context) {
		this.context = context;
		configFile = context.scanner.getConfigFile();
	}
	
	private void parseWithTarget(Element element, Target target, TargetInstance parent)
	{
		TargetInstance targetInstance = TargetInstance.fromTarget(target, element, parent);
		Action action = context.actionSet.get(targetInstance.getName());
		if (action != null)
			action.actBefore(targetInstance, context);
		
		List<Element> elements = element.elements();
		for (Element e: elements)
		{
			Target subTarget = target.findSubTarget(e.getName());
			parseWithTarget(e, subTarget, targetInstance);
		}
		
		if (action != null)
			action.actAfter(targetInstance, context);
	}
	
	public void parse()
	{
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(configFile);
			Element root = document.getRootElement();
			parseWithTarget(root, context.archConfigTarget, null);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
