package org.crowdev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "source_file_imp")
public class SourceFileImp {
	
	@Id
	@GeneratedValue
	private int id;
	
	private int source_file_id;
	
	private String imp_file_path;
	
	public SourceFileImp() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSource_file_id() {
		return source_file_id;
	}

	public void setSource_file_id(int source_file_id) {
		this.source_file_id = source_file_id;
	}

	public String getImp_file_path() {
		return imp_file_path;
	}

	public void setImp_file_path(String imp_file_path) {
		this.imp_file_path = imp_file_path;
	}
}
