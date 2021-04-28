package io.github.shniu.corejava.jvm;

public class StackOverflowMock {
    private static int count = 0;

    public void call() {
        count++;
        call();
    }

    public static void main(String[] args) {
        StackOverflowMock stackOverflowMock = new StackOverflowMock();

        try {
            stackOverflowMock.call();
        } catch (StackOverflowError e) {
            System.out.println("Stack deep: " + count);
            e.printStackTrace();
        }
    }
}
