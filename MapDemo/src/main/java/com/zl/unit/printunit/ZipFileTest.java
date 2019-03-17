package com.zl.unit.printunit;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

//使用org.apache.tools.zip这个就不会中文乱码
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


/**
 * 文件压缩测试
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-01-03 11:09
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
@Slf4j
public class ZipFileTest {
    static String filePath = "F:/foreignTrade/2c91808262822e680162842f070c0003";//需要压缩的文件夹完整路径
    static String fileName = "2c91808262822e680162842f070c0003";//需要压缩的文件夹名
    static String outPath = "F:/foreignTrade/2c91808262822e680162842f070c0003.zip";

    public static void main(String args[]) throws Exception
    {
        OutputStream is = new FileOutputStream(outPath);//创建Test.zip文件
        CheckedOutputStream cos = new CheckedOutputStream(is, new CRC32());//检查输出流,采用CRC32算法，保证文件的一致性
        ZipOutputStream zos = new ZipOutputStream(cos);//创建zip文件的输出流
        zos.setEncoding("GBK");//设置编码，防止中文乱码
        File file = new File(filePath);//需要压缩的文件或文件夹对象
        ZipFile(zos,file);//压缩文件的具体实现函数
        zos.close();
        cos.close();
        is.close();
        System.out.println("压缩完成");


        /*String path = "F:/foreignTrade/4028b881620478150162047c2e200000/营业执照";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }*/

    }
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    //递归，获取需要压缩的文件夹下面的所有子文件,然后创建对应目录与文件,对文件进行压缩
    public static void ZipFile(ZipOutputStream zos,File file) throws Exception {
        if(file.isDirectory()) {
            //创建压缩文件的目录结构
            zos.putNextEntry(new ZipEntry(file.getPath().substring(file.getPath().indexOf(fileName))+File.separator));

            for(File f : file.listFiles())
            {
                ZipFile(zos,f);
            }
        } else {
            //打印输出正在压缩的文件
            log.info("正在压缩文件:"+file.getName());
            //创建压缩文件
            zos.putNextEntry(new ZipEntry(file.getPath().substring(file.getPath().indexOf(fileName))));
            //用字节方式读取源文件
            InputStream is = new FileInputStream(file.getPath());
            //创建一个缓存区
            BufferedInputStream bis = new BufferedInputStream(is);
            //字节数组,每次读取1024个字节
            byte [] b = new byte[1024];
            //循环读取，边读边写
            while(bis.read(b)!=-1) {
                zos.write(b);//写入压缩文件
            }
            //关闭流
            bis.close();
            is.close();
        }
    }

}
