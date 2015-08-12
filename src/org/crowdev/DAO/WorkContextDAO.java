package org.crowdev.DAO;

import java.util.List;

import org.crowdev.model.WorkContext;

public class WorkContextDAO extends BaseDAO{

	public void saveWorkContext(WorkContext workContext)
	{
		try {
			saveOrUpdate(workContext);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<WorkContext> getWorkContexts(int arch_id)
	{
		List<WorkContext> ans = null;
		try {
			ans = (List<WorkContext>) findByHQL("from WorkContext where arch_id = " + arch_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ans;
	}
}
