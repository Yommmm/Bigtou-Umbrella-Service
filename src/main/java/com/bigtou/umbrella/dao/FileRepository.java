package com.bigtou.umbrella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bigtou.umbrella.bean.ApkFileInfo;

@Repository
public interface FileRepository extends JpaRepository<ApkFileInfo, String> {

	List<ApkFileInfo> queryApkFileInfoByVersionOrderByUploadTimeDesc(String version);
	
	@Query(value = "select t from ApkFileInfo t order by uploadTime")
	List<ApkFileInfo> queryLatestVersion();
	
}
