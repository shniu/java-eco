package io.github.shniu.arts.design.memento;

import java.util.Scanner;
import java.util.Stack;

/**
 * 用户输入文本时，程序将其追加存储在内存文本中；用户输入“:list”，程序在命令行中输出内存文本的内容；用户输入“:undo”，
 * 程序会撤销上一次输入的文本，也就是从内存文本中将上次输入的文本删除掉。
 *
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/14 09
 */
public class MementoPatternDemo {

    public static void main(String[] args) {
        InputText inputText = new InputText();
        SnapshotHolder snapshotHolder = new SnapshotHolder();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();

            if (":list".equals(input)) {
                // 罗列输入的文本
                System.out.println(inputText.toString());
            } else if (":undo".equals(input)) {
                // 撤销上次输入的文本
                InputText snapshot = snapshotHolder.popSnapshot();  // 弹出上次的 text
                inputText.setText(snapshot.getText());  // 恢复
            } else {
                // 存储文本
                snapshotHolder.pushSnapshot(inputText);  // 把上次的 text 先备份
                inputText.append(input);  // 追加新的输入
            }
        }
    }

    // 保存一个快照
    static class SnapshotHolder {
        private Stack<InputText> snapshots = new Stack<>();

        // SnapshotHolder 利用 InputText 来做修改，违背了封装原则
        // 理论上快照应该是只读的不能被修改
        public void pushSnapshot(InputText text) {
            InputText deepCopyInputText = new InputText();
            deepCopyInputText.setText(text.getText());
            snapshots.push(deepCopyInputText);
        }

        public InputText popSnapshot() {
            return snapshots.pop();
        }
    }

    // 保存输入的文本
    static class InputText {
        private StringBuilder text = new StringBuilder();

        public void append(String input) {
            text.append(input);
        }

        // 这个方法可能会被其他业务使用，暴露了本不该暴露的接口，违背了封装原则
        public void setText(String input) {
            this.text.replace(0, this.text.length(), input);
        }

        public String getText() {
            return text.toString();
        }

        public String toString() {
            return text.toString();
        }
    }
}
