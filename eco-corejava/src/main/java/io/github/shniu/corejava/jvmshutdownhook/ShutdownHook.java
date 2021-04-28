package io.github.shniu.corejava.jvmshutdownhook;

public class ShutdownHook extends Thread {

    private Thread mainThread;
    private boolean shutdownSignal;

    @Override
    public void run() {
        System.out.println("接受到 shutdown 信号");
        shutdownSignal = true;
        mainThread.interrupt();
        try {
            mainThread.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("优雅关闭完成");
    }

    public ShutdownHook(Thread mainThread) {
        super();
        this.mainThread = mainThread;
        this.shutdownSignal = false;
        Runtime.getRuntime().addShutdownHook(this);
    }

    public boolean shouldShutdown() {
        return this.shutdownSignal;
    }
}
