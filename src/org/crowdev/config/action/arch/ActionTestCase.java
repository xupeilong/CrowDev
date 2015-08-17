package org.crowdev.config.action.arch;

import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.config.action.ActionUtils;
import org.crowdev.model.SourceFile;

public class ActionTestCase implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {

		ActionUtils.persistantSourceFile(targetInstance, context, SourceFile.TYPE_TEST_CASE);
	}

}
