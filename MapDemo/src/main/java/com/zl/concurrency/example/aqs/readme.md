#AQS同步组件重常用的包括
CountDownLunch，
Semaphore，
CyclicBarrier：一组线程相互等待，只有每个线程准备就绪后才执行后续操作，
ReentrantLock与锁；



CyclicBarrier和CountDownLunch的区别：
1.CountDownLunch的计数器只能使用一次，而CyclicBarrier的计数器可以通过reset方法重置循环使用；
2.CountDownLunch实现的是一个或n个线程等待其他线程的关系；CyclicBarrier多个线程之间相互等待的关系，只有所有的线程都满足后才可以执行后续的操作

ReentrantLock（可重入锁）与synchronized区别
可重入性：都可以重入
锁的实现：
性能的区别：更建议使用synchronized
功能方面区别：
ReentrantLock的独有功能：
    1.可指定是公平锁还是非公平锁
    2.提供了一个Condition类，可以分组唤醒需要唤醒的线程
    3.提供了能够中断等待锁的线程的机制，lock.lockInterruptibly()