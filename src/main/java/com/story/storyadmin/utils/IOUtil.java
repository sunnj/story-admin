package com.story.storyadmin.utils;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 */
@Slf4j
public class IOUtil {
    private static String content = "",line=System.getProperty("line.separator");//换行相当于\n
    private static List fileList = new ArrayList();
    private static File tempFile = null;
    private static final long KB = 1024;// KB
    private static final long MB = KB * KB;// MB
    private static final long GB = KB * MB;// GB
    private static final int byt = 2048;
    private static File f=new File("");
    private static BASE64Encoder encoder = new  BASE64Encoder();// 加密
    private static BASE64Decoder decoder = new  BASE64Decoder();// 解密
    private static String webRootPath;
    private static String rootClassPath;

    /**
     * 读文件流
     * @param formPath 从哪里读取的文件路径
     * @return
     */
    public static String reader1(String formPath) {
        FileReader read = null;
        BufferedReader reader = null;
        try {
            read = new FileReader(new File(formPath));
            reader = new BufferedReader(read);
            StringBuffer buffer = new StringBuffer();
            content = reader.readLine();
            while (content != null) {
                buffer.append(content).append(line);
                content = reader.readLine();
            }
            return content = buffer.toString();//返回
        } catch (Exception e) {
            log.error("遇到错误",e);
        } finally {
            try {
                if (reader != null)reader.close();
                if (read != null)read.close();
            } catch (Exception e) {
                log.error("遇到错误",e);
            }
        }
        return "";
    }

    /**
     * InputStreamReader
     * ByteArrayOutputStream
     * ByteArrayInputStream
     * @param formPath
     * @return
     */
    public static String reader3(String formPath){
        String newName="";
        try {
            InputStream is=new FileInputStream(new File(formPath));
            InputStreamReader reader=new InputStreamReader(is);
            //不够大的时候读不出来全部内容来的,他不像available拿多所就读多少
            char c[] = new char[is.available()];//1M=1024*1024,根据需求定
            int len = reader.read(c);
            content=new String(c, 0, len);

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(bout);
            dout.writeUTF(content);

            byte[] buff = bout.toByteArray();
            ByteArrayInputStream bin = new ByteArrayInputStream(buff);
            DataInputStream dis = new DataInputStream(bin);
            newName = dis.readUTF();

            bout.flush();
            dout.flush();
            bout.close();
            dout.close();
            dis.close();

        } catch (Exception e) {
            log.error("遇到错误",e);
        }
        return newName;
    }

    /**
     * 写文件
     * BufferedWriter
     * BufferedReader
     * @param formPath
     * @param toPath
     */
    public static boolean writer1(String formPath,String toPath){
        Reader reader;
        Writer writer;
        boolean flag=true;
        BufferedWriter buffWriter;
        BufferedReader buffReader;
        try {
            reader=new FileReader(new File(formPath));
            writer = new FileWriter(new File(toPath));//writer不能关闭

            buffWriter=new BufferedWriter(writer);//这个写完可以关闭
            buffReader=new BufferedReader(reader);

            content=buffReader.readLine();
            while(content!=null){
                buffWriter.write(line+content);
                content=buffReader.readLine();
                buffWriter.flush();//只要用到缓冲区就flush//其实关闭了缓冲区,就是关闭缓冲区中流的对象. //写一次flush是为了防止停电就挂了~
            }
            reader.close();
            buffReader.close();
            buffWriter.close();
        } catch (Exception e) {
            log.error("遇到错误",e);
            return false;
        }
        return flag;
    }

    /**
     * 写文件
     * @param formPath
     * @param toPath
     */
    public static boolean writer2(String formPath,String toPath){
        Reader reader;
        PrintWriter writer;
        boolean flag=true;
        BufferedWriter buffWriter;//此对象有换行newLine
        BufferedReader buffReader;//为了提高效率,使用字符缓冲流BufferedReader
        try {
            reader=new FileReader(new File(formPath));
            writer = new PrintWriter(new File(toPath));//writer不能关闭

            buffWriter=new BufferedWriter(writer);//这个写完可以关闭
            buffReader=new BufferedReader(reader);

            content=buffReader.readLine();
            while(content!=null){
                buffWriter.write(line+content);
                content=buffReader.readLine();
            }
            reader.close();
            buffReader.close();
            buffWriter.close();

        } catch (Exception e) {
            log.error("遇到错误",e);
            return false;
        }
        return flag;
    }

    /**
     * 写文件
     * @param from
     * @param to
     */
    public static void writer3(String from, String to) {
        try {
            InputStream in = new FileInputStream(new File(from));
            OutputStream out = new FileOutputStream(new File(to));
            byte[] buff = new byte[in.available()];
            int len;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
    }
    /**
     * 写文件
     * @param from
     * @param to
     */
    public static void writer4(String from, String to) {
        try {
            InputStream in = new FileInputStream(new File(from));
            OutputStream out = new FileOutputStream(new File(to));
            byte[] buff = new byte[in.available()];
            in.read(buff);
            out.write(buff);
            in.close();
            out.close();
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
    }
    /**
     * 写文件
     * @param formPath
     * @param toPath
     * @return
     */
    public static boolean writer5(String formPath,String toPath) {
        try {
            InputStream input=new FileInputStream(new File(formPath));
            OutputStream output=new FileOutputStream(new File(toPath));
            byte [] byf=new byte[1024*1024];//这里就不能填那么大啦 byte不同char,byte
            int len;
            while((len=input.read())!=-1)
            {
                output.write(byf, 0, len);
            }
            if(output!=null)output.close();
            if(input!=null)input.close();
        } catch (Exception e) {
            log.error("遇到错误",e);
            return false;
        }
        return true;
    }

    /**
     * 文件处理
     * @param content
     * @param toPath
     * @return
     */
    public static boolean writer6(String content, String toPath) {
        boolean flag = true;
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(toPath), "utf-8"));//设置编码
            out.write(line + content);
            out.close();
        } catch (Exception ex) {
            log.error("遇到错误",ex);
            return false;
        }
        return flag;
    }

    /**
     * BufferedOutputStream 套DataOutputStream套FileOutputStream
     * 据说管道套管道效率效率比较高,具体没测试过!
     * BufferedInputStream bis = new BufferedInputStream(new DataInputStream(new FileInputStream(new File(formPath))));
     * @param formPath
     * @param toPath 写到那个路径下
     * @return
     */
    public static boolean writer7(String formPath, String toPath) {
        boolean flag = true;
        /**
         *这里就不能填那么大啦 byte不同char,byte
         */
        byte[] byt = new byte[1];
        try {
            //BufferedInputStream bis = new BufferedInputStream(new FileInputStream(formPath));
            BufferedInputStream bis = new BufferedInputStream(new DataInputStream(new FileInputStream(new File(formPath))));
            DataOutputStream dos=new DataOutputStream(new FileOutputStream(new File(toPath)));
            BufferedOutputStream bos=new BufferedOutputStream(dos);
            while(bis.read(byt)!=-1)
            {
                bos.write(byt);
                System.out.println("文件已经写完!");
            }
            bos.flush();
            bos.close();
        } catch (Exception ex) {
            log.error("遇到错误",ex);
            return false;
        }
        return flag;
    }

    /**
     *
     * @param formPath
     * @param toPath
     * @return
     */
    public static boolean writer8(String formPath,String toPath){
        try {
            BufferedInputStream bis=new BufferedInputStream(new FileInputStream(new File(formPath)));

            ByteArrayOutputStream baos=new ByteArrayOutputStream();

            int c=bis.read();//读取bis流中的下一个字节
            while(c!=-1){
                baos.write(c);
                c=bis.read();
            }
            bis.close();
            byte byt[]=baos.toByteArray();

            FileOutputStream out=new FileOutputStream(new File(toPath));
            out.write(byt);
            out.close();

        } catch (Exception e) {
            log.error("遇到错误",e);
            return false;
        }
        return true;
    }


    /**
     * 处理文件大小
     */
    public static String fileSize(long file) {
        if (file <= 0) {
            return "";
        } else if (file < MB) {
            BigDecimal b = new BigDecimal((double) file / KB);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "K";
        } else if (file < GB) {
            BigDecimal b = new BigDecimal((double) file / MB);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "M";
        } else {
            BigDecimal b = new BigDecimal((double) file / GB);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "G";
        }
    }


    /**
     * 删除目录下的所有子目录和文件
     *
     * @param filepath
     * @throws IOException
     */
    protected static void deleteDirs(String filepath) throws IOException {
        File f = new File(filepath);// 定义文件路径
        if (f.exists()) {// 判断是否存在
            if (f.isDirectory()) {// 判断是文件还是目录
                if (f.listFiles().length == 0) {// 若目录下没有文件则直接删除
                    f.delete();// 删除目录
                } else {// 若有则把文件放进数组，并判断是否有下级目录
                    File delFile[] = f.listFiles();
                    int i = f.listFiles().length;
                    for (int j = 0; j < i; j++) {
                        if (delFile[j].isDirectory()) {
                            deleteDirs(delFile[j].getAbsolutePath());// 递归调用delDirs方法并取得子目录路径
                        } else {
                            delFile[j].delete();// 删除文件
                        }
                    }
                    f.delete();
                }
            } else {
                f.delete();
            }
        }
    }

    /**
     * 直接过滤图片所有格式的
     * 重命名文件里面的名称
     * @param fileDir
     * @param sequenceCode
     */
    public static void fileRename(File fileDir, String sequenceCode) {
        File[] files = fileDir.listFiles();
        for (int k = 0; k < files.length; k++) {
            StringBuffer sb = new StringBuffer(sequenceCode);
            if (files[k].isDirectory()) {
                fileRename(files[k], sequenceCode);
            } else {
                if (k < 10)
                    sb.append("_000").append(k);
                else if (k >= 10 && k < 100)
                    sb.append("_00").append(k);
                else if (k < 1000 && k >= 100)
                    sb.append("_0").append(k);
                else
                    sb.append("_").append(k);
                final int index = files[k].getName().lastIndexOf(".") + 1;
                final String fileType = files[k].getName().substring(index);
                sb.append(".").append(fileType);
                final String name = sb.toString();
                final File dirFile = new File(fileDir, name);
                System.out.println("Rename File Path:"
                        + files[k].getAbsolutePath());
                files[k].renameTo(dirFile);
            }
        }
    }

    /**
     * 根据后缀条件过滤
     *
     * @param fileDir
     * @param sequenceCode
     */
    public static void fileFilterRenames(File fileDir, String sequenceCode) {
        File[] files = fileDir.listFiles(fileFilter);
        for (int k = 0; k < files.length; k++) {
            StringBuffer sb = new StringBuffer(sequenceCode);
            if (files[k].isDirectory()) {
                fileRename(files[k], sequenceCode);
            } else {
                if (k < 10)
                    sb.append("_000").append(k);
                else if (k >= 10 && k < 100)
                    sb.append("_00").append(k);
                else if (k < 1000 && k >= 100)
                    sb.append("_0").append(k);
                else
                    sb.append("_").append(k);
                final int index = files[k].getName().lastIndexOf(".") + 1;
                final String fileType = files[k].getName().substring(index);
                sb.append(".").append(fileType);
                final String name = sb.toString();
                final File dirFile = new File(fileDir, name);
                System.out.println("重命名的文件: :" + files[k].getAbsolutePath());
                files[k].renameTo(dirFile);
            }
        }
    }

    /**
     * 读取文件里面的所有文件
     *
     * @param filePath
     */
    public static void fileList(String filePath) {
        File srcFile = new File(filePath);
        boolean flag = srcFile.exists();
        if (!flag || !srcFile.isDirectory() || !srcFile.canRead()) {
            try {
                srcFile.createNewFile();
            } catch (IOException e) {
                log.error("遇到错误",e);
            }
        } else {
            File[] file = srcFile.listFiles();
            for (int i = 0; i < file.length; i++) {
                System.out.println(file[i].getAbsolutePath());
            }
        }
    }

    /**
     * 以数组形式列出所有文件包括子类的文件
     *
     * @param path
     */
    public static List findFiles(String path) {
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            String[] filenames = file.list();
            if (filenames == null)
                return fileList;
            for (int i = 0; i < filenames.length; i++) {
                if (files[i].isFile()) {
                    fileList.add(files[i].getPath());
                } else if (files[i].isDirectory()) {
                    findFiles(files[i].getPath());
                }
            }
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
        return fileList;
    }
    /**
     * 压缩文件
     * @param path 压缩文件的路径
     * @param toDir 指定压缩文件到那个目录磁盘下
     */
    public static void compressionZip(String path,String toDir) {
        File f = new File(path);
        try {
            InputStream fis = new FileInputStream(f);
            InputStream bis = new BufferedInputStream(fis);
            byte[] buf = new byte[1024*1024];
            int index= f.getName().lastIndexOf("\\")+1;
            String fileName=f.getName().substring(index);
            if(fileName.matches(".*\\.(?i)gif")){ //匹配gif格式文件
                fileName=fileName.replaceAll(".gif", "");
            }else if(fileName.matches(".*\\.(?i)java")){//匹配.java文件
                fileName=fileName.replaceAll(".java", "");
            }
            int len;
            OutputStream fos = new FileOutputStream(toDir+ fileName + ".zip");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ZipOutputStream zos = new ZipOutputStream(bos);// 压缩包
            ZipEntry ze = new ZipEntry(f.getName());// 这是压缩包名里的文件名
            zos.putNextEntry(ze);// 写入新的 ZIP 文件条目并将流定位到条目数据的开始处
            while ((len = bis.read(buf)) != -1) {
                zos.write(buf, 0, len);
                zos.flush();
            }
            bis.close();
            zos.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
        }
    }
    /**
     * 解压zip文件
     * @param path 解压文件路径
     * @param flag 标识
     */
    public static void decompressZip(String path,boolean flag)
    {
        int count;
        int index;
        String savePath;
        savePath = path.substring(0, path.lastIndexOf("\\")) + "\\";
        try
        {
            BufferedOutputStream bos;
            ZipEntry entry ;
            InputStream fis = new FileInputStream(path);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            while((entry = zis.getNextEntry()) != null)
            {
                byte data[] = new byte[byt];
                String temp = entry.getName();
                flag = fileFilter(temp);
                if(!flag)
                    continue;
                index = temp.lastIndexOf("/");
                if(index > -1)
                    temp =  temp.substring(index+1);
                temp=savePath+temp;
                File f = new File(temp);
                if(!f.exists()){//不存在就创建一个新的文件
                    f.createNewFile();
                    //f.mkdirs();
                }
                OutputStream fos = new FileOutputStream(f);
                bos = new BufferedOutputStream(fos, byt);
                while((count = zis.read(data, 0, byt)) != -1)
                {
                    bos.write(data, 0, count);
                }
                bos.flush();//flush缓冲区
                fos.flush();
                bos.close();
                fos.close();
                fis.close();

            }
            zis.close();
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
    }

    /**
     *
     * @param zip 压缩文件文件路径
     * @param zipName 压缩文件里面的文件名称
     * @param srcFiles 多文件
     * @throws IOException
     */
    public static void ZipFiles(File zip, String zipName, File... srcFiles)
            throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
        ZipFiles(out, zipName, srcFiles);
        out.close();
        System.out.println("*****************压缩完毕*******************");
    }

    /**
     * 压缩文件-File
     * @param zipFile zip文件
     * @param srcFiles 被压缩源文件
     */
    public static void ZipFiles(ZipOutputStream zis, String path,
                                File... srcFiles) {
        path = path.replaceAll("\\*", "/");
        if (!path.endsWith("/")) {
            path += "/";
        }
        byte[] buf = new byte[1024];
        try {
            for (int i = 0; i < srcFiles.length; i++) {
                if (srcFiles[i].isDirectory()) {
                    File[] files = srcFiles[i].listFiles();
                    String srcPath = srcFiles[i].getName();
                    srcPath = srcPath.replaceAll("\\*", "/");
                    if (!srcPath.endsWith("/")) {
                        srcPath += "/";
                    }
                    zis.putNextEntry(new ZipEntry(path + srcPath));
                    ZipFiles(zis, path + srcPath, files);
                } else {
                    FileInputStream in = new FileInputStream(srcFiles[i]);
                    System.out.println(path + srcFiles[i].getName());
                    zis.putNextEntry(new ZipEntry(path
                            + srcFiles[i].getName()));
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        zis.write(buf, 0, len);
                    }
                    zis.closeEntry();
                    in.close();
                }
            }
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
    }

    /**
     *
     * @param zipPath
     * @param descDir
     * @throws IOException
     */
    public static void unZipFiles(String zipPath, String descDir)
            throws IOException {
        unZipFiles(new File(zipPath), descDir);
    }

    /**
     * 解压文件到指定目录
     * @param zipFile
     * @param descDir
     */
    public static void unZipFiles(File zipFile, String descDir)
            throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
            System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
    }

    /**
     * 以数组形式存放图片,只拿jpg格式的
     *
     * @param dirName
     * @return
     */
    public static List readDirFiles(String dirName) {// dirName目录全路径
        try {
            File file = new File(dirName);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File tempFile = files[i];
                if (tempFile.isDirectory()) {
                    String path = tempFile.getPath();
                    List list = readDirFiles(path);// 递归
                    for (int j = 0; j < list.size(); j++) {
                        fileList.add(list.get(j));
                    }
                } else {
                    if (!tempFile.isFile()) {
                        continue;
                    }
                    // 判断是jpg格式的就添加进去
                    if (files[i].getName().endsWith(".gif")) {
                        fileList.add(tempFile);
                    }
                }
                if (i == (files.length - 1)) {
                    return fileList;
                }
            }
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
        return fileList;
    }

    /**
     * 创建文件并且拷贝文件
     *
     * @param dirFrom 从那个文件里面拷贝
     * @param dirTo 拷贝到那个文件里面
     */
    public static void copyFiles(File dirFrom, File dirTo) {
        File[] files = dirFrom.listFiles();
        for (File f : files) {
            String tempFrom = f.getAbsolutePath();
            String tempTo = tempFrom.replace(dirFrom.getAbsolutePath(), dirTo
                    .getAbsolutePath()); // 后面的路径 替换前面的路径名
            if (f.isDirectory()) {
                tempFile = new File(tempTo);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                } else {
                    copyFiles(dirFrom, dirTo);
                }
            } else {
                System.out.println("源文件:" + f.getAbsolutePath());
                int index = tempTo.lastIndexOf("\\");// 找到"/"所在的位置
                String mkdirPath = tempTo.substring(0, index);
                tempFile = new File(mkdirPath);
                if (!tempFile.exists()) {
                    tempFile.mkdirs();
                } else {
                    System.out.println("目标点:" + tempTo);
                    readFiles(tempFrom, tempTo);
                }
            }
        }
    }

    /**
     * 拷贝方法
     *
     * @param from 从那个路径
     * @param to 到那个路径
     */
    public static void readFiles(String from, String to) {
        try {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);
            byte[] buff = new byte[1024 * 1024];
            int len;
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
        }
    }

    /**
     * xml格式化
     * @param document
     * @param filePath
     * @return
     */
    private static boolean format(Document document, String filePath) {
        boolean ret=true;
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");// 设置格式编码
        try {
            /** 将document中的内容写入文件中new XMLWriter(new FileWriter(new File(filename))); */
            XMLWriter writer = new XMLWriter(new FileWriter(new File(filePath)),format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
            ret=false;
        }
        return ret;
    }
    /**
     * 文件处理
     * @param content
     * @param htmlPath
     * @return
     */
    public static boolean write(String content, String htmlPath) {
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlPath), "utf-8"));
            out.write("\n" + content);
            out.close();
        } catch (Exception ex) {
            log.error("遇到错误",ex);
            return false;
        }
        return true;
    }

    /**
     * 过滤器
     */
    private static FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File fileName) {
            String f = fileName.getName();
            if (f.endsWith(".png") || f.endsWith(".gif")) {
                return true;
            }
            return false;
        }
    };

    /**
     * 文件过滤
     * @param filName
     * @return
     */
    public static boolean fileFilter(String filName)
    {
        if(filName.endsWith(".jpg") || filName.endsWith(".gif")  || filName.endsWith(".bmp") || filName.endsWith(".png"))
        {
            return false;
        }else{
            return true;
        }
    }


    /**
     * 获取当前工程路径
     *
     * @return
     */
    public static String getSysPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource(".").toString();
        String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    /**
     *
     * @return
     */
    public static String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource(".").toString();
        String temp = path.replaceFirst("file:/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    /**
     *
     * @return
     */
    private String getClassesAbsolutePath() {
        // 得到的是 项目的绝对路径
        String path = this.getClass().getClassLoader().getResource("")
                .getPath();
        String temp = path.replaceFirst("/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    /**
     *
     * @return
     */
    private String getCurrentClassPath() {
        String path = this.getClass().getResource("/").getPath();
        String temp = path.replaceFirst("/", "");
        String separator = System.getProperty("file.separator");
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }



    public static String getPath(Class clazz) {
        String path = clazz.getResource("").getPath();
        return new File(path).getAbsolutePath();
    }

    public static String getPath(Object object) {
        String path = object.getClass().getResource("").getPath();
        return new File(path).getAbsolutePath();
    }

    public static String getRootClassPath() {
        if (rootClassPath == null) {
            try {
                String path = IOUtil.class.getClassLoader().getResource("").toURI().getPath();
                rootClassPath = new File(path).getAbsolutePath();
            }
            catch (Exception e) {
                String path = IOUtil.class.getClassLoader().getResource("").getPath();
                rootClassPath = new File(path).getAbsolutePath();
            }
        }
        return rootClassPath;
    }

    public static String getPackagePath(Object object) {
        Package p = object.getClass().getPackage();
        return p != null ? p.getName().replaceAll("\\.", "/") : "";
    }

    public static File getFileFromJar(String file) {
        throw new RuntimeException("Not finish. Do not use this method.");
    }

    public static String getWebRootPath() {
        if (webRootPath == null)
            webRootPath = detectWebRootPath();
        return webRootPath;
    }

    public static void setWebRootPath(String webRootPath) {
        if (webRootPath == null)
            return ;

        if (webRootPath.endsWith(File.separator))
            webRootPath = webRootPath.substring(0, webRootPath.length() - 1);
        IOUtil.webRootPath = webRootPath;
    }

    private static String detectWebRootPath() {
        try {
            String path = IOUtil.class.getResource("/").toURI().getPath();
            return new File(path).getParentFile().getParentFile().getCanonicalPath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密文件
     * @param path
     */
    private static String encryptFile(String path) {
        InputStream in;
        OutputStream out;
        String key = "";
        try {
            File f = new File(path);
            in = new FileInputStream(f);
            out = new ByteArrayOutputStream();
            encoder.encodeBuffer(in, out);
            key = out.toString();
            in.close();
            out.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
        }
        return key;
    }

    /**
     *解密
     * @param path
     */
    private static String decryptFile(String path) {
        InputStream in;
        OutputStream out;
        String key = "";
        try {
            File f = new File(path);
            in = new FileInputStream(f);
            out = new ByteArrayOutputStream();
            decoder.decodeBuffer(in, out);
            key = out.toString();
            in.close();
            out.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
        }
        return key;
    }


    /**
     * 加密
     * @param inputStr
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(String inputStr) {
        String value = "";
        try {
            byte[] key = inputStr.getBytes();
            value = encoder.encodeBuffer(key);
        } catch (Exception e) {
            log.error("遇到错误",e);
        }
        return value;
    }

    /**
     * 解密
     * @param outputStr
     * @return
     * @throws Exception
     */
    public static String decryptBASE64(String outputStr) {
        String value = "";
        try {
            byte[] key = decoder.decodeBuffer(outputStr);
            value = new String(key);
        } catch (Exception e) {
        }
        return value;
    }

    public static String getInputStream(InputStream inputStream){
        String str = null;
        ByteArrayOutputStream baos=null;
        try {
            baos = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream.read()) != -1) {
                baos.write(i);
            }
            str = baos.toString();
            baos.close();
        } catch (IOException e) {
            log.error("遇到错误",e);
            try {
                if(baos!=null){
                    baos.close();
                }
            } catch (IOException e1) {
                log.error("遇到错误",e1);
            }
        }
        return str;
    }
}
