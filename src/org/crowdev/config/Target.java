package org.crowdev.config;

import java.util.ArrayList;
import java.util.List;

public class Target {
	public String name;
	public boolean isMulti;
	public List<String> attrs = new ArrayList<>();
	public List<Target> subTarget = new ArrayList<>();

	public Target(String name, boolean isMulti) {
		this.name = name;
		this.isMulti = isMulti;
	}
	
	public Target findSubTarget(String name)
	{
		for (Target t: subTarget)
			if (t.name.equals(name))
				return t;
		return null;
	}
}
