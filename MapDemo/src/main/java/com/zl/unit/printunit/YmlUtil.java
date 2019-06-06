package com.zl.unit.printunit;


import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Yml文件读取
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-06-06 9:42
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class YmlUtil {
    private static Map<String,String> allMap=new HashMap<String,String>();
    static {
        Yaml yaml = new Yaml();
        InputStream inputStream = YmlUtil.class.getResourceAsStream("/application.yml");
        Iterator<Object> result = yaml.loadAll(inputStream).iterator();
        while(result.hasNext()){
            Map map=(Map)result.next();
            iteratorYml( map,null);
        }
    }
    public static void iteratorYml(Map map,String key){
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key2 = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof LinkedHashMap){
                if(key==null){
                    iteratorYml((Map)value,key2.toString());
                }else{
                    iteratorYml((Map)value,key+"."+key2.toString());
                }
            }
            if(key==null){
                allMap.put(key2.toString(), StringUtil.objToString(value));
            }
            if(key!=null){
                allMap.put(key+"."+key2.toString(), StringUtil.objToString(value));
            }
        }

    }

    public static String getYmlValue(String key){
        return allMap.get(key);
    }
}
