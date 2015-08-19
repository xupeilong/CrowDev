package org.crowdev.config.action.work;

import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.config.action.ActionUtils;
import org.crowdev.model.SourceFile;

public class ActionClass_Work implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {
		String nameString = targetInstance.getAttrs().get("src");
		System.out.println("ActionClass_Work: " + nameString);
		ActionUtils.persistantSourceFileImp(targetInstance, context, SourceFile.TYPE_WORK_CLASS);
	}

	@Override
	public void actAfter(TargetInstance targetInstance, Context context) {
		// TODO Auto-generated method stub
		
	}

}
