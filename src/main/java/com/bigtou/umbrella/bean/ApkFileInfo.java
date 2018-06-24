package com.bigtou.umbrella.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "APK_FILE_INFO")
public class ApkFileInfo {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "FILE_ID")
	private String fileId;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "VERSION")
	private String version;
	
	@Column(name = "LOCATION")
	private String location;
	
	@Column(name = "UPLOAD_TIME")
	private Date uploadTime;
	
	@Column(name = "UPGRADE_REASON")
	private String upgradeReason;
	
	@Column(name = "REMARK")
	private String remark;

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

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
}
