package com.story.storyadmin.utils;

import java.io.*;

/**
 * 数据库备份/还原通用类
 * @author sunnj
 *
 */
public class DataBaseUtils {

	/**
	 * 备份,返回备份的相对路径
	 * @param mysqlInstallPath
	 * @param serverName
	 * @param userName
	 * @param password
	 * @param dbName
	 * @param outPhyPath
	 * @return
	 */
	public static String backup(String mysqlInstallPath,String serverName,String userName, String password,String dbName,String outPhyPath){
		InputStream in=null;
		InputStreamReader xx=null;
		BufferedReader br=null;
		OutputStreamWriter writer=null;
		FileOutputStream fout=null;
		try{
			Runtime rt = Runtime.getRuntime();
			//调用mysql的安装目录的命令
			Process child = rt.exec(mysqlInstallPath+"/bin/mysqldump -h "+serverName+" -u"+userName+" -p"+password+" "+dbName);
			//设置导出编码为utf-8。这里必须是utf-8
			//把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			in = child.getInputStream();// 控制台的输出信息作为输入流
		 
			xx = new InputStreamReader(in, "utf-8");
			//设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
		 
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
			    sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
		 
			// 备份后的目标文件路径
			String relativePath= "/"+ DateUtils.formatDate(DateUtils.currentDate(), DateUtils.TimeFormat.SHORT_DATE_PATTERN_YEAR_MONTH);
			String outFilePath= outPhyPath+relativePath;
			FileExtendUtils.createFilePath(outFilePath);
			
			String newFileName= FileExtendUtils.newFileName()+".sql";
			fout = new FileOutputStream(outFilePath +"/"+newFileName);
			writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			return relativePath+"/"+newFileName;
		} catch (Exception e) {
            e.printStackTrace();
        }finally {  
            try {
            	if (in != null) {  
            		in.close();  
                }  
            	if (xx != null) {  
            		xx.close();  
                }  
                if (br != null) {  
                	br.close();  
                }  
                if (writer != null) {  
                	writer.close();  
                }
                if (fout != null) {  
                	fout.close();  
                }
            } catch (IOException e) {  
                e.printStackTrace();
            }  
        } 
		return null;
    }

	/**
	 * 还原，此方法有待实际测试
	 * @param mysqlInstallPath
	 * @param serverName
	 * @param userName
	 * @param password
	 * @param dbName
	 * @param outRelativePath
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void restore(String mysqlInstallPath,String serverName,String userName, String password,String dbName,String outRelativePath)throws UnsupportedEncodingException,FileNotFoundException,IOException{
	    Runtime runtime = Runtime.getRuntime();
	    Process process = runtime.exec(mysqlInstallPath+"/bin/mysql -h"+serverName+" -u"+userName+" -p"+password+" --default-character-set=utf8 "+ dbName);
	    OutputStream outputStream = process.getOutputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(outRelativePath), "utf-8"));
	    String str = null;
	    StringBuffer sb = new StringBuffer();
	    while ((str = br.readLine()) != null) {
	        sb.append(str + "\r\n");
	    }
	    str = sb.toString();
	    OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
	    writer.write(str);
	    writer.flush();
	    outputStream.close();
	    br.close();
	    writer.close();
    }
}
