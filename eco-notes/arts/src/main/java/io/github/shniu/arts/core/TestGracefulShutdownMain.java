package io.github.shniu.arts.core;

public class TestGracefulShutdownMain {

    private ShutdownHook shutdownHook;

    public static void main(String[] args) {
        TestGracefulShutdownMain mainThread = new TestGracefulShutdownMain();
        System.out.println("hello main");
        mainThread.execute();
        System.out.println("end of main");
    }

    public TestGracefulShutdownMain() {
        this.shutdownHook = new ShutdownHook(Thread.currentThread());
    }

    public void execute() {
        while (!shutdownHook.shouldShutdown()) {
            System.out.println("i am sleep");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("execute() interrupted");
            }
            System.out.println("i am not sleep");
        }
        System.out.println("end of execute");
    }
}
