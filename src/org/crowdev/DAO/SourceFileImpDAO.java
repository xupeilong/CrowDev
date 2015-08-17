package org.crowdev.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.crowdev.model.SourceFileImp;

public class SourceFileImpDAO extends BaseDAO{
	
	public void saveSourceFileImp(SourceFileImp sourceFileImp)
	{
		try {
			saveOrUpdate(sourceFileImp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
