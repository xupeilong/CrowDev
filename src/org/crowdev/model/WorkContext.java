package org.crowdev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.crowdev.DAO.SourceFileDAO;

@Entity
@Table(name = "work_context")
public class WorkContext{

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private int arch_id;
	
	@Transient
	private List<SourceFile> sourceFiles = null;
	
	public WorkContext() {
	}

	public WorkContext(String name, int arch_id) {
		this.name = name;
		this.arch_id = arch_id;
	}
	
	

	public List<SourceFile> getSourceFiles() {
		if (sourceFiles == null)
		{
			SourceFileDAO sourceFileDAO = new SourceFileDAO();
			sourceFiles = sourceFileDAO.getSrcFiles(this);
			System.out.println("src count = " + sourceFiles.size());
		}
		return sourceFiles;
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


	public int getArch_id() {
		return arch_id;
	}


	public void setArch_id(int arch_id) {
		this.arch_id = arch_id;
	}
	
	
	
}
