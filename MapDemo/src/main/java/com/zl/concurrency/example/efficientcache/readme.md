本节将构建高效结果缓存
    设计思路，涉及的问题以及一步步调优的过程

       1.Memoizer1中通过HashMap然后加锁的方式实现
       2.Memoizer2中将Hashm改成了ConcurrentHashMap
       3.Memoizer3中将ConcurrentHashMap中的value换成了Future
       4.Memoizer4中将普通的put方法换成了putIfAbsent原子方法

     运行：在TestMain的入口函数中运行，通过改变继承，验证不同的效果

2018年12月19日23:26
