package com.etree.tms.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.etree.tms.constant.Constants;

public class FileUtils implements Constants {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	public static void createDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}
}
