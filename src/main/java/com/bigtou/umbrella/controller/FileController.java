package com.bigtou.umbrella.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bigtou.umbrella.bean.ApkFileInfo;
import com.bigtou.umbrella.service.FileService;

/**
 * 文件服务接口
 * @author Yom
 *
 */

@RestController
@RequestMapping(value = "/bigtou/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private String folder = "F://";
	
	@Autowired
	private FileService fileService;

	/**
	 * APK下载
	 * @param version
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/appVersion")
	public void download1(@RequestParam(name = "version", required = false) String version, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(version);
		
		ApkFileInfo apkFileInfo = fileService.getApkFileInfo(version);
		
		// try后面的括号里面的流会自动关闭
		try (InputStream inputStream = new FileInputStream(new File(folder, apkFileInfo.getFileName()));
				OutputStream outputStream = response.getOutputStream();) {
			
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + apkFileInfo.getFileName());
			
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
	
	/**
	 * APK文件上传
	 * @param apkFileInfo
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public Object uploadApkFile(ApkFileInfo apkFileInfo, MultipartFile file) throws IllegalStateException, IOException {
		return fileService.uploadApkFile(apkFileInfo, file);
	}
	
	/**
	 * 所有版本APK信息
	 * @return
	 */
	@GetMapping("/allVersion")
	public Object queryAllVersion() {
		return fileService.queryFileList();
	}
	
}
