package org.crowdev.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class TargetInstance {

	private Target target;
	private Map<String, String> attrs = new HashMap<>();
	private TargetInstance parent;
	private List<TargetInstance> childs = new ArrayList<>();
	
	public Object modelObj;
	
	
	// No public default 
	private TargetInstance() {
	}
	
	public static TargetInstance fromTarget(Target target, Element element, TargetInstance parent) 
	{
		TargetInstance targetInstance = new TargetInstance();
		targetInstance.target = target;
		for (String name: target.getAttrs())
		{
			String value = "";
			Attribute attr = element.attribute(name);
			if (attr != null)
				value = attr.getValue();
			targetInstance.attrs.put(name, value);
		}
		targetInstance.parent = parent;
		if (parent != null)
			parent.childs.add(targetInstance);
		return targetInstance;
	}
	
	public String getName()
	{
		return target.getName();
	}

	public TargetInstance getParent() {
		return parent;
	}

	public Target getTarget() {
		return target;
	}

	public Map<String, String> getAttrs() {
		return attrs;
	}
	
	
}
