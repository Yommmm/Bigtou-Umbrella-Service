package com.bigtou.umbrella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.ApkFileInfo;

@Repository
public interface FileRepository extends JpaRepository<ApkFileInfo, String> {

	List<ApkFileInfo> queryApkFileInfoByVersionOrderByUploadTimeDesc(String version);
	
	@Query(value = "SELECT t.* from APK_FILE_INFO t ORDER BY UPLOAD_TIME DESC", nativeQuery = true)
	List<ApkFileInfo> queryLatestVersion();
	
}
