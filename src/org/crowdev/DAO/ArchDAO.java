package org.crowdev.DAO;

import org.crowdev.model.Arch;

public class ArchDAO extends BaseDAO{

	public void saveArch(Arch arch)
	{
		try {
			saveOrUpdate(arch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
