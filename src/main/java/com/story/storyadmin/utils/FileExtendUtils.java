package com.story.storyadmin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件保存工具类
 *
 */
@Slf4j
public class FileExtendUtils {

	/**
	 * 生成新的文件名，不带后缀
	 * @return
	 */
	public static String newFileName(){
		return System.currentTimeMillis()+"";
	}
	
	/**
	 * 创建文件目录
	 * @param path
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String createFilePath(String path)
			throws IllegalStateException, IOException {
		File filePath = new File(path);
		if (!filePath.exists()) {
			if(!filePath.mkdirs()){
				return null;
			}
		}
		return path;
	}
	
	/**
	 * 保存文件
	 * @param file
	 * @param savePath
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String saveFile(MultipartFile file, String savePath, String fileName)
			throws IllegalStateException, IOException {
		File filePath = new File(savePath);
		if (!filePath.exists()) {
			if(!filePath.mkdirs()){
				return null;
			}
		}
		File destFile = new File(savePath + File.separator + fileName);
		file.transferTo(destFile);
		return destFile.getAbsolutePath();
	}

	/**
	 * 保存文件
	 * @param savePath 保存的路径
	 * @param filename 文件名称
	 * @param file 流文件
	 * @throws IOException
	 * @throws IllegalStateException
	 */

	public static void saveFile(String savePath, String filename, File file) {
		try {
			File newsFileRoot = new File(savePath);
			if (!newsFileRoot.exists()) {
				newsFileRoot.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(savePath + File.separator + filename);
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) > 0) {
				fos.write(buf, 0, len);
			}
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		} catch (Exception ex) {
			log.error("遇到错误",ex);
		}
	}

	public static String getSuffix(String fileName) {
		if (fileName.lastIndexOf(".") > 0) {
			return fileName.substring(fileName.lastIndexOf("."), fileName.length());
		}
		return "unknow file type!";
	}

	/**
	 * 复制单个文件
	 * @param srcFileName 待复制的文件名
	 * @param destFileName 目标文件名
	 * @param overlay 如果目标文件存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
		File srcFile = new File(srcFileName);

		// 判断源文件是否存在
		if (!srcFile.exists()) {
			// MESSAGE = "源文件：" + srcFileName + "不存在！";
			// JOptionPane.showMessageDialog(null, MESSAGE);
			return false;
		} else if (!srcFile.isFile()) {
			// MESSAGE = "复制文件失败，源文件：" + srcFileName + "不是一个文件！";
			// JOptionPane.showMessageDialog(null, MESSAGE);
			return false;
		}

		// 判断目标文件是否存在
		File destFile = new File(destFileName);
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				new File(destFileName).delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if (!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				log.error("遇到错误",e);
			}
		}
	}

	/**
	 * 删除文件
	 * @param filePath 文件路径
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除文件与目录
	 * @param filePath 文件路径
	 * @return
	 */
	public static boolean deleteFolder(String filePath) {
		boolean flag = false;
		File file = new File(filePath);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return flag;
		} else {
			// 判断是否为文件
			if (file.isFile()) { // 为文件时调用删除文件方法
				return deleteFile(filePath);
			} else { // 为目录时调用删除目录方法
				return deleteDirectory(filePath);
			}
		}
	}

	/**
	 * 删除目录
	 * @param filePath
	 * @return
	 */
	public static boolean deleteDirectory(String filePath) {
		boolean flag = false;
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!filePath.endsWith(File.separator)) {
			filePath = filePath + File.separator;
		}
		File dirFile = new File(filePath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * 处理跨浏览器附件中文名乱码、空格
     * 
     * @param fileName
     * @return 编码的fileName
     * 
     */
    public static String encodingFileName(String fileName, HttpServletRequest req) {
        String userAgent = req.getHeader("user-agent");
        boolean isMSIE = userAgent.contains("MSIE");
        if(!isMSIE) {
            // IE 11 识别问题 : Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; Touch; rv:11.0) like Gecko
            isMSIE = userAgent.contains("like Gecko");
        }
        boolean isMSIE6 = userAgent.contains("MSIE 6.0");
        boolean isFF = false;
        if(!isMSIE) {
            isFF = userAgent.contains("Mozilla");
            
        }
        String returnFileName = "";
        try {
            if (isMSIE) {// IE
                returnFileName = URLEncoder.encode(fileName, "UTF-8");
                if (returnFileName.length() > 150 && isMSIE6) {// 处理IE6 无法下载问题
                    returnFileName = returnFileName.substring(0, 149);// 截取前150位
                } else {
                    returnFileName = StringUtils.replace(returnFileName, "+",
                        "%20");
                }
            } else if(isFF){// FF
                returnFileName = "=?UTF-8?B?"
                    + (new String(Base64.encodeBase64(fileName
                        .getBytes("UTF-8")))) + "?=";
            } else{
                returnFileName = fileName ;
            }
        } catch (UnsupportedEncodingException e) {
        }
        return returnFileName;
    }
}