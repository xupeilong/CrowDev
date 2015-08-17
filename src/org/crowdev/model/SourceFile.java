package org.crowdev.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "source_file")
public class SourceFile {

	public static final int TYPE_INTERFACE = 0;
	public static final int TYPE_WORK_CLASS = 1;
	public static final int TYPE_TEST_CASE = 2;
	
	@Id
	@GeneratedValue
	private int id;
	
	private int work_context_id;
	
	private String name;
	
	private String srcPath;
	
	private String filePath;
	
	private int type;
	
	public SourceFile() {
	}
	
	


	public SourceFile(int work_context_id, String name, String srcPath, String filePath, int type) {
		super();
		this.work_context_id = work_context_id;
		this.name = name;
		this.srcPath = srcPath;
		this.filePath = filePath;
		this.type = type;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getWork_context_id() {
		return work_context_id;
	}


	public void setWork_context_id(int work_context_id) {
		this.work_context_id = work_context_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSrcPath() {
		return srcPath;
	}


	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}




	public int getType() {
		return type;
	}




	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
