package org.crowdev.DAO;

import java.util.ArrayList;
import java.util.List;

import org.crowdev.model.SourceFile;
import org.crowdev.model.WorkContext;

public class SourceFileDAO extends BaseDAO{

	public void saveSourceFile(SourceFile sourceFile)
	{
		try {
			saveOrUpdate(sourceFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<SourceFile> getSrcFiles(WorkContext workContext)
	{
		List<SourceFile> ans = null;
		try {
			ans = (List<SourceFile>) findByHQL("from SourceFile where work_context_id = " + workContext.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}
}
