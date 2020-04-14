package io.github.shniu.arts.design.memento;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author niushaohan (shaohan.niu@dfgroup.pro)
 * @date 2020/4/14 10
 */
public class MementoPatternDemoOpt {
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
                Snapshot snapshot = snapshotHolder.popSnapshot();  // 弹出上次的 text
                inputText.restoreSnapshot(snapshot);
            } else {
                // 存储文本
                snapshotHolder.pushSnapshot(inputText.createSnapshot());  // 把上次的 text 先备份
                inputText.append(input);  // 追加新的输入
            }
        }
    }

    static class Snapshot {
        private String text;

        public Snapshot(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    static class InputText {
        private StringBuilder text = new StringBuilder();

        public void append(String input) {
            text.append(input);
        }

        public String toString() {
            return text.toString();
        }

        public Snapshot createSnapshot() {
            return new Snapshot(text.toString());
        }

        public void restoreSnapshot(Snapshot snapshot) {
            this.text.replace(0, this.text.length(), snapshot.getText());
        }
    }

    static class SnapshotHolder {
        private Stack<Snapshot> snapshots = new Stack<>();


        public Snapshot popSnapshot() {
            return snapshots.pop();
        }

        public void pushSnapshot(Snapshot snapshot) {
            snapshots.push(snapshot);
        }
    }


}
