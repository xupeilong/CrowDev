package org.crowdev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "work_context")
public class WorkContext{

	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private int arch_id;
	
	public WorkContext() {
	}

	public WorkContext(String name, int arch_id) {
		this.name = name;
		this.arch_id = arch_id;
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
