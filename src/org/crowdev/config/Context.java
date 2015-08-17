package org.crowdev.config;

import java.util.HashMap;
import java.util.Map;

import org.crowdev.config.action.Action;
import org.crowdev.config.action.ActionArchContext;

public class Context {

	public static Target archConfigTarget = null;
	
	public static Map<String, Action> actionSet = new HashMap<>();
	
	static {
		actionSet.put("ArchContext", new ActionArchContext());
	}
}
