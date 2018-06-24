package com.bigtou.umbrella.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bigtou.umbrella.bean.ApkFileInfo;
import com.bigtou.umbrella.controller.FileController;
import com.bigtou.umbrella.dao.FileRepository;

@Service
public class FileService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private String folder = "F://";
	
	@Autowired
	private FileRepository fileRepository;
	
	public ApkFileInfo getApkFileInfo(String version) {
		if(null == version || "".equals(version)) {
			List<ApkFileInfo> apkFileInfos = fileRepository.queryLatestVersion();
			return apkFileInfos.get(0);
		} else {
			List<ApkFileInfo> apkFileInfos = fileRepository.queryApkFileInfoByVersionOrderByUploadTimeDesc(version);
			return apkFileInfos.get(0);
		}
	}

	/**
	 * 文件上传服务
	 * @param version
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public Object uploadApkFile(ApkFileInfo apkFileInfo, MultipartFile file) throws IllegalStateException, IOException {
		logger.info(file.getName());
		logger.info(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		File localFile = new File(folder, "bigtou_" + apkFileInfo.getVersion() + "_" + new Date().getTime() + ".apk");
		file.transferTo(localFile);
		String location = localFile.getAbsolutePath();
		
		apkFileInfo.setFileName(localFile.getName());
		apkFileInfo.setLocation(location);
		apkFileInfo.setUploadTime(new Date());
		apkFileInfo = fileRepository.save(apkFileInfo);
		
		return apkFileInfo;
	}
	
	/**
	 * 查询所有文件列表
	 * @return
	 */
	public Object queryFileList() {
		return fileRepository.queryLatestVersion();
	}
	
}
