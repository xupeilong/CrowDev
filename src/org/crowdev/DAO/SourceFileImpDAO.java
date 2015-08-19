package org.crowdev.DAO;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.crowdev.model.SourceFile;
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
	
	public List<SourceFileImp> getSourceFileImps(SourceFile sourceFile)
	{
		List<SourceFileImp> sourceFileImps = null;
		try {
			sourceFileImps = (List<SourceFileImp>) findByHQL("from SourceFileImp where source_file_id = " + sourceFile.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sourceFileImps;
	}
	
}
