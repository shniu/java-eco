package io.github.shniu.corejava.pattern.activeobject.sample1;

/**
 * @author niushaohan
 * @date 2020/11/16 00
 */
public class ActiveObjectFactory {
    public static ActiveObject create() {
        return new DefaultActiveObject();
    }
}
