package org.crowdev.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.crowdev.ProjectScanner;
import org.crowdev.config.action.Action;
import org.crowdev.config.action.arch.ActionArchContext;
import org.crowdev.config.action.arch.ActionClass;
import org.crowdev.config.action.arch.ActionInterface;
import org.crowdev.config.action.arch.ActionTestCase;
import org.crowdev.config.action.arch.ActionWorkContext;
import org.crowdev.config.action.work.ActionClass_Work;
import org.crowdev.config.action.work.ActionWorkContext_Work;

public class Context {

	public ProjectScanner scanner = null;
	public Target archConfigTarget = null;
	public Map<String, Action> actionSet = new HashMap<>();
	
	// Use no default.
	private Context() {
	}
	
	public static Context workContextFromDir(File projectDir)
	{
		Context context = new Context();
		context.scanner = new ProjectScanner(projectDir);
		
		// Target
		ConfigTMPParser configTMPLoader = new ConfigTMPParser();
		context.archConfigTarget = configTMPLoader.parseWorkTMP(); 
		
		// Actions 
		context.actionSet.put("WorkContext", new ActionWorkContext_Work());
		context.actionSet.put("Class", new ActionClass_Work());
		
		return context;
	}
	
	public static Context archContextFromDir(File projectDir)
	{
		Context context = new Context();
		context.scanner = new ProjectScanner(projectDir);
		
		// Target
		ConfigTMPParser configTMPLoader = new ConfigTMPParser();
		context.archConfigTarget = configTMPLoader.parseArchTMP(); 
		
		// Actions 
		context.actionSet.put("ArchContext", new ActionArchContext());
		context.actionSet.put("WorkContext", new ActionWorkContext());
		context.actionSet.put("Interface", new ActionInterface());
		context.actionSet.put("Class", new ActionClass());
		context.actionSet.put("TestCase", new ActionTestCase());
		
		return context;
	}
}
