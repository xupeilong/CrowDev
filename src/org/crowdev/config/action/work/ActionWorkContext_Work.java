package org.crowdev.config.action.work;

import org.crowdev.DAO.ArchDAO;
import org.crowdev.DAO.WorkContextDAO;
import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.model.Arch;
import org.crowdev.model.WorkContext;

public class ActionWorkContext_Work implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {
		String nameString = targetInstance.getAttrs().get("name");
		System.out.println("WorkContext_Work: " + nameString);
	}

}
