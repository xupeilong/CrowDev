package org.crowdev.config;

import java.util.ArrayList;
import java.util.List;

public class Target {
	private String name;
	private boolean isMulti;
	private List<String> attrs = new ArrayList<>();
	private Target parent;
	private List<Target> childs = new ArrayList<>();

	public Target(String name, boolean isMulti) {
		this.name = name;
		this.isMulti = isMulti;
	}
	
	public Target findSubTarget(String name)
	{
		for (Target t: childs)
			if (t.name.equals(name))
				return t;
		return null;
	}
	
	public void addChild(Target target)
	{
		childs.add(target);
		target.parent = this;
	}
	
	public void addAttr(String attrName)
	{
		attrs.add(attrName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMulti() {
		return isMulti;
	}

	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}

	public List<String> getAttrs() {
		return attrs;
	}


	public Target getParent() {
		return parent;
	}

	public List<Target> getChilds() {
		return childs;
	}
	
	
	
	
}
