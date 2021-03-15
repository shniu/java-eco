package io.github.shniu.corejava.pattern.activeobject.sample1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author niushaohan
 * @date 2020/11/16 00
 */
public class ClientMain {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.create();

        new Thread(() -> {
            Future<String> result = activeObject.doTask("Joe", 100);

            try {
                String s = result.get();
                System.out.println("Joe's result is " + s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Future<String> result = activeObject.doTask("Visa", 110);

            try {
                String s = result.get();
                System.out.println("Visa's result is " + s);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            activeObject.shutdown();
        }
    }
}
