package org.crowdev.DAO;

import java.util.List;

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
	
	public Arch getArch(int id)
	{
		Arch arch = null;
		try {
			List<Arch> archs = (List<Arch>) findByHQL("from Arch where id = " + id);
			arch = archs.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return arch;
	}
}
