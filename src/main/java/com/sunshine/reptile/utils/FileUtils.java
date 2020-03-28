package com.sunshine.reptile.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 * @author 张梓枫
 * @Description 文件上传类
 * @date:   2019年1月2日 下午4:03:46
 */
public abstract class FileUtils {

	/**
	 * 生成文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param fileContent
	 *            文件内容
	 * @param path
	 *            文件路径
	 * @param fileType
	 *            文件后缀名
	 * @return
	 */
	public static String createFile(String fileName,  byte[] fileContent, String path, String fileType) {
		if (!ObjectUtils.isNotEmpty(fileContent))
			throw new RuntimeException("文件内容不能空!");
		if (ObjectUtils.isEmpty(path))
			throw new RuntimeException("上传路径不能为空!");
		if (ObjectUtils.isEmpty(fileType))
			throw new RuntimeException("文件类型不能为空!");
		if (path.indexOf("/") > 0)
			path = StringUtils.replace(path, "/", "\\");
		String s = path.substring(path.length() - 1, path.length());
		if (!s.equals("\\"))
			path = path.concat("\\");
		File file = new File(path.concat(fileName).concat(fileType));
		if (!file.exists())
			file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			String url = writeFile(file, fileContent);
			return url;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除文件
	 * @param url
	 */
	public static void delFile(String url){
		File file = new File(url);
		if(file.exists()&&!file.isDirectory()){
			file.delete();
		}
	}
	/**
	 * 将内容写入文件
	 * @param file
	 * @param fileContent
	 * @return
	 */
	public static String writeFile(File file, byte[] fileContent) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(fileContent);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getName();
	}
}
