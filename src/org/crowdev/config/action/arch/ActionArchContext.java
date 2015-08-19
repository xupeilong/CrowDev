package org.crowdev.config.action.arch;

import org.crowdev.DAO.ArchDAO;
import org.crowdev.config.Context;
import org.crowdev.config.Target;
import org.crowdev.config.TargetInstance;
import org.crowdev.config.action.Action;
import org.crowdev.model.Arch;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class ActionArchContext implements Action{

	@Override
	public void actBefore(TargetInstance targetInstance, Context context) {
		String nameString = targetInstance.getAttrs().get("name");
		System.out.println("ActionArchContext: " + nameString);
		
		Arch arch = new Arch(nameString);
		
		// Persistant Arch
		ArchDAO dao = new ArchDAO();
		dao.saveArch(arch);
		targetInstance.modelObj = arch;
	}

	@Override
	public void actAfter(TargetInstance targetInstance, Context context) {
		// TODO Auto-generated method stub
		
	}

}
