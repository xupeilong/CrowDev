package org.crowdev.config.action;

import java.io.File;

import org.crowdev.DAO.SourceFileDAO;
import org.crowdev.DAO.SourceFileImpDAO;
import org.crowdev.config.Context;
import org.crowdev.config.TargetInstance;
import org.crowdev.model.SourceFile;
import org.crowdev.model.SourceFileImp;
import org.crowdev.model.WorkContext;

public class ActionUtils {

	public static void persistantSourceFile(TargetInstance targetInstance, Context context, int type)
	{
		String srcString = targetInstance.getAttrs().get("src");
		System.out.println("Source: " + srcString);
		
		File file = context.scanner.getFileBySrcPath(srcString);
        SourceFileDAO sourceFileDAO = new SourceFileDAO();
        int workContextId = ((WorkContext)targetInstance.getParent().modelObj).getId();
        sourceFileDAO.saveSourceFile(new SourceFile(workContextId, file.getName(),
        		srcString, file.getPath(), type));
	}
	
	public static void persistantSourceFileImp(TargetInstance targetInstance, Context context, int type)
	{
		String srcString = targetInstance.getAttrs().get("src");
		int srcId = Integer.parseInt(targetInstance.getAttrs().get("id"));
		System.out.println("SourceImp: " + srcString);
		
		File file = context.scanner.getFileBySrcPath(srcString);
		SourceFileImpDAO sourceFileImpDAO = new SourceFileImpDAO();
		sourceFileImpDAO.saveSourceFileImp(new SourceFileImp(srcId, file.getPath()));
	}
	
}
