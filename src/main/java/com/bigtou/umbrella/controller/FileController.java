package com.bigtou.umbrella.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件服务接口
 * @author Yom
 *
 */

@RestController
@RequestMapping(value = "/bigtou/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@GetMapping("/version")
	public Object getApkFile(@RequestParam String apkVersion) {
		return null;
	}
	
	@PostMapping("/apkFile")
	public Object uploadApkFile() {
		return null;
	}
	
}
