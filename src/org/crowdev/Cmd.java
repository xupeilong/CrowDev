package org.crowdev;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import org.crowdev.DAO.WorkContextDAO;
import org.crowdev.model.WorkContext;

public class Cmd {

	public static void main(String[] args)
	{
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Start!");
		while (true)
		{
			
			System.out.println("-----------------------------------");
			System.out.println("1 = Load Arch Project");
			System.out.println("2 = Make Work Project");
			System.out.println("3 = Load Work Project");
			System.out.println("Input command number: ");
			try {
				String in = strin.readLine();
				int i = Integer.parseInt(in);
				
				switch (i) {
				case 1:
					System.out.println("Input arch dir path:");
					String archPath = strin.readLine();
					ProjectManager loader = new ProjectManager();
					loader.loadArchProject(new File(archPath));
					break;
					
				case 2:
					System.out.println("Input arch id");
					int archId = Integer.parseInt(strin.readLine());
					System.out.println("Input work projects dest dir");
					String workPath = strin.readLine();
					WorkContextDAO workContextDAO = new WorkContextDAO();
					List<WorkContext> workContexts = workContextDAO.getWorkContexts(archId);
					for (WorkContext workContext: workContexts)
					{
						System.out.println("WorkContext id = " + workContext.getId());
						ProjectManager manager = new ProjectManager();
						manager.makeWorkProject(workContext, workPath);
					}
					break;

				default:
					System.out.println("No such command!");
					break;
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Please input cmd number.");
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
