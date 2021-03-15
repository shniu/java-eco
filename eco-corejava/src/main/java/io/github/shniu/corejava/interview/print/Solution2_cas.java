package io.github.shniu.corejava.interview.print;

public class Solution2_cas {
    enum ReadToRun {T1, T2}

    static volatile ReadToRun run = ReadToRun.T1;

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                while (run != ReadToRun.T1) {
                }
                System.out.print(i);
                run = ReadToRun.T2;
            }
        }).start();

        new Thread(() -> {
            for (char c = 'a'; c <= 'z'; c++) {
                while (run != ReadToRun.T2) {
                }
                System.out.print(c);
                run = ReadToRun.T1;
            }
        }).start();
    }
}
