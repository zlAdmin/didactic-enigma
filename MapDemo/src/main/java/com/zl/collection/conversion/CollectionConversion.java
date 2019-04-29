package com.zl.collection.conversion;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

/**
 * 集合之间的相互转换类
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-03-01 10:52
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Slf4j
public class CollectionConversion {
    public static void main(String[] args) {
        arrayToList();
    }


    /**
     * @Description 数据转list
     * @return void
     * @throws 
     * @Author zhanglei
     * @Date 10:53 2019/3/1
     * @Param []
     **/
    public static void arrayToList() {
        String[] arr = new String[]{"java", "go", "hadoop", "storm", "Python"};
        //方式1
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        //方式2
        List<String> list2 = List.of(arr);

        list.forEach(v -> log.info(v));
        list2.forEach(v -> log.info(v));
    }

    public static void ListToArray() {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("go");
        list.add("hadoop");
        list.add("storm");
        list.add("Python");
        String[] arr = (String[]) list.toArray();
        for (String v : arr) {
            log.info(v);
        }
    }

    /**
     * @Description map的values转List
     * @return void
     * @throws 
     * @Author zhanglei
     * @Date 11:12 2019/3/1
     * @Param []
     **/
    public static void mapValuesToList() {
        Map<String, String> map = new HashMap(4);
        map.put("java", "01");
        map.put("go", "02");
        map.put("hadoop", "03");
        map.put("storm", "04");
        List<String> list = new ArrayList<>(map.values());
        list.forEach(v -> log.info(v));
    }

    public static void arrayToSet() {
        String[] arr = new String[]{"java", "go", "hadoop"};
        Set<String> set = new HashSet<>(Arrays.asList(arr));
        Iterator<String> stringIterator = set.iterator();
        if (stringIterator.hasNext()) {
            log.info(stringIterator.next());
        }
    }

    public static void listToSet() {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("go");
        list.add("hadoop");
        list.add("storm");
        list.add("Python");
        Set<String> set = new HashSet(list);
        Iterator<String> stringIterator = set.iterator();
        if (stringIterator.hasNext()) {
            log.info(stringIterator.next());
        }
    }
}
