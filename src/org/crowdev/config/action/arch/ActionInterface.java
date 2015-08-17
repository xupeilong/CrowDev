package org.crowdev.config.action.arch;

import java.io.File;

import org.crowdev.DAO.SourceFileDAO;
import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.config.action.ActionUtils;
import org.crowdev.model.SourceFile;
import org.crowdev.model.WorkContext;

public class ActionInterface implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {
		
		ActionUtils.persistantSourceFile(targetInstance, context, SourceFile.TYPE_INTERFACE);
	}
	
	

}
