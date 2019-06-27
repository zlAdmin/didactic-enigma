package com.zl.unit.printunit;

import org.springframework.util.ResourceUtils;
import org.apache.commons.io.FileUtils;
import java.io.File;

/**
 * 文件工具读取类
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-06-06 9:41
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class ReadKeysUtil {

    public static final  String CONFIG_PATH_PREFIX = YmlUtil.getYmlValue("zl.key.path");
    public static String PRIVATE_KEY = "";

    static {
        try {
            File privateKey = ResourceUtils.getFile(CONFIG_PATH_PREFIX + "private_key");
            PRIVATE_KEY = FileUtils.readFileToString(privateKey);
        } catch (Exception e) {
            System.out.println("资源读取错误");
        }
    }

}
