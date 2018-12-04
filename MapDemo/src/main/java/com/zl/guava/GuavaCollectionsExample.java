package com.zl.guava;

import com.google.common.base.Function;
import com.google.common.collect.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Guava中集合样例
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-11-13 17:06
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
public class GuavaCollectionsExample {
    public static void main(String[] args) {

    }

    private static void collectionsFactory() {
        List<String> list = Lists.newArrayList();
        Map<String, String> map = Maps.newLinkedHashMap();
        Set<String> copySet = Sets.newHashSet("0110", "101001");
        List<String> theseElements = Lists.newArrayList("alpha", "beta", "gamma");

        List<String> exactly100 = Lists.newArrayListWithCapacity(100);
        List<String> approx100 = Lists.newArrayListWithExpectedSize(100);
        Set<String> approx100Set = Sets.newHashSetWithExpectedSize(100);
        Multiset<String> multiset = HashMultiset.create();
    }

    private static void SetsExample() {
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");
        Sets.SetView<String> intersection = Sets.intersection(primes,wordsWithPrimeLength);
        // intersection包含"two", "three", "seven"
        /** 可以使用交集，但不可变拷贝的读取效率更高 */
        ImmutableSet<String> a = intersection.immutableCopy();

    }

    private static void MapsExample() {
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        diff.entriesInCommon(); // 两个Map中都有的映射项，包括匹配的键与值
        diff.entriesInCommon(); // 键相同但是值不同值映射项。返回的Map的值类型为MapDifference.ValueDifference，以表示左右两个不同的值
        diff.entriesOnlyOnLeft(); // 键只存在于左边Map的映射项
        diff.entriesOnlyOnRight(); // 键只存在于右边Map的映射项

    }
    private static void MapsUniqueIndexExample() {
        List<String> list = Lists.newArrayList("00", "212", "3321");
        ImmutableMap<Integer, String> stringsByIndex = Maps.uniqueIndex(list,
        new Function<String, Integer>() {
            @Override
            public Integer apply(String string) {
                return string.length();
            }
        });
    }

}
