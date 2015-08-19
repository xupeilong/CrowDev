package org.crowdev.config.action.arch;

import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.config.action.ActionUtils;
import org.crowdev.model.SourceFile;

public class ActionClass implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {
		
		ActionUtils.persistantSourceFile(targetInstance, context, SourceFile.TYPE_WORK_CLASS);
	}

	@Override
	public void actAfter(TargetInstance targetInstance, Context context) {
		// TODO Auto-generated method stub
		
	}

}
