package io.github.shniu.juc.interview;

/**
 * 用多线程去处理"abc"，"def"，“ghi”这个三个字符串，让它们以"adg"，"beh"，“cfi”这种形式输出。
 * 和启动三个线程，依次输出 A B C 是类似的
 */
public class PrintByOrder {

    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(new ByOrder("abc", lock, 0)).start();
        new Thread(new ByOrder("def", lock, 1)).start();
        new Thread(new ByOrder("ghi", lock, 2)).start();
    }
}

class ByOrder implements Runnable {
    private String s;
    private Object lock;
    private int flag;

    private static int count = 0;
    private static int max = 9;

    public ByOrder(String s, Object lock, int flag) {
        this.s = s;
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                if (count >= max) {
                    break;
                }

                if (count % 3 == flag) {
                    System.out.print(s.charAt(count / 3) + " ");
                    count++;
                }
            }
        }
    }
}
