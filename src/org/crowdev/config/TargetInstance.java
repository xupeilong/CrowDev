package org.crowdev.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class TargetInstance {

	private Target target;
	private Map<String, String> attrs = new HashMap<>();
	private TargetInstance parent;
	
	
	// No public default 
	private TargetInstance() {
	}
	
	public static TargetInstance fromTarget(Target target, Element element, TargetInstance parent) 
	{
		TargetInstance targetInstance = new TargetInstance();
		targetInstance.target = target;
		for (String name: target.attrs)
		{
			String value = "";
			Attribute attr = element.attribute(name);
			if (attr != null)
				value = attr.getValue();
			targetInstance.attrs.put(name, value);
		}
		targetInstance.parent = parent;
		return targetInstance;
	}
	
	public String getName()
	{
		return target.name;
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
