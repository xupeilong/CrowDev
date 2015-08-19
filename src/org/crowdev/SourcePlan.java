package org.crowdev;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.crowdev.DAO.SourceFileImpDAO;
import org.crowdev.model.SourceFile;
import org.crowdev.model.SourceFileImp;
import org.crowdev.model.WorkContext;

public class SourcePlan {

	private WorkContext workContext;
	private Map<SourceFile, SourceFileImp> plan = new HashMap<>();
	
	private SourcePlan(){
	}
	
	public static SourcePlan defaultPlan(WorkContext workContext)
	{
		SourcePlan sourcePlan = new SourcePlan();
		sourcePlan.workContext = workContext;
		
		SourceFileImpDAO sourceFileImpDAO = new SourceFileImpDAO();
		for (SourceFile sourceFile: workContext.getSourceFiles())
		{
			List<SourceFileImp> sourceFileImps = sourceFileImpDAO.getSourceFileImps(sourceFile);
			sourcePlan.plan.put(sourceFile, sourceFileImps.get(0));
		}
		return sourcePlan;
	}

	public WorkContext getWorkContext() {
		return workContext;
	}

	public Map<SourceFile, SourceFileImp> getPlan() {
		return plan;
	}
	
	
}
