package org.crowdev.config.action.arch;

import org.crowdev.DAO.ArchDAO;
import org.crowdev.DAO.WorkContextDAO;
import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.model.Arch;
import org.crowdev.model.WorkContext;

public class ActionWorkContext implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {
		String nameString = targetInstance.getAttrs().get("name");
		System.out.println("WorkContext: " + nameString);
		
		// Persistant WorkContext
		int archId = ((Arch) targetInstance.getParent().modelObj).getId();
		WorkContext workContext = new WorkContext(nameString, archId);
		WorkContextDAO workContextDAO = new WorkContextDAO();
		workContextDAO.saveWorkContext(workContext);
		targetInstance.modelObj = workContext;
	}

	@Override
	public void actAfter(TargetInstance targetInstance, Context context) {
		// TODO Auto-generated method stub
		
	}

}
