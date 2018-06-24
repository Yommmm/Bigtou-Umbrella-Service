package com.bigtou.umbrella.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "APK_FILE_INFO")
public class ApkFileInfo {

	@Id
	@Column(name = "FILE_ID")
	private String fileId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "VERSION")
	private String version;
	
	@Column(name = "LOCATION")
	private String location;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
