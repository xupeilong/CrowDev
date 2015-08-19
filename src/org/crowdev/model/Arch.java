package org.crowdev.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.crowdev.DAO.WorkContextDAO;

@Entity
@Table(name = "arch")
public class Arch {

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@Transient
	private List<WorkContext> workContexts = null;
	
	public Arch() {
	}
	
	public Arch(String name) {
		super();
		this.name = name;
	}
	
	public List<WorkContext> getWorkContexts()
	{
		if (workContexts == null)
		{
			WorkContextDAO workContextDAO = new WorkContextDAO();
			workContexts = workContextDAO.getWorkContexts(id);
		}
		return workContexts;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
