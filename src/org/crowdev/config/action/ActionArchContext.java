package org.crowdev.config.action;

import org.crowdev.DAO.ArchDAO;
import org.crowdev.config.Target;
import org.crowdev.config.TargetInstance;
import org.crowdev.model.Arch;
import org.dom4j.Attribute;
import org.dom4j.Element;

public class ActionArchContext implements Action{

	@Override
	public void act(TargetInstance targetInstance) {
		String nameString = targetInstance.getAttrs().get("name");
		System.out.println("ActionArchContext: " + nameString);
		
		Arch arch = new Arch(nameString);
		
		// Persistant Arch
		ArchDAO dao = new ArchDAO();
		dao.saveArch(arch);
	}

}
