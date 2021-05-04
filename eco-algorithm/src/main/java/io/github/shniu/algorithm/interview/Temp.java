package io.github.shniu.algorithm.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niushaohan
 * @date 2021/5/2 10
 */
public class Temp {

    class LRUCache {

        Node head;
        Node tail;

        Map<String, Node> cacheMaps;

        int capacity;
        int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;

            this.cacheMaps = new HashMap<>(capacity);

            this.head = new Node(null, null);
            this.tail = new Node(null, null);

            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        void put(String key, String value) {
            assert key != null;

            Node node = cacheMaps.get(key);
            if (node != null) {
                node.value = value;
                moveToHead(node);
            } else {
                node = new Node(key, value);
                cacheMaps.put(key, node);

                if (isFull()) {
                    Node removed = removeFromTail();
                    if (removed != null) {
                        cacheMaps.remove(removed.key);
                        removed.prev = null;
                        removed.next = null;
                        removed = null;
                    }
                }

                addNode(node);
                size++;
            }
        }

        private Node removeFromTail() {
            Node last = tail.prev;
            if (last != head) {
                removeNode(last);
                return last;
            }

            return null;
        }

        private boolean isFull() {
            return size >= capacity;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addNode(node);
        }

        private void addNode(Node node) {
            node.next = head.next;
            head.next.prev = node;

            head.next = node;
            node.prev = head;
        }

        // n1 <-> n2 <-> n3
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
//
//            node.next = null;
//            node.prev = null;
        }

        String get(String key) {
            assert key != null;

            Node node = cacheMaps.get(key);
            if (node != null) {
                moveToHead(node);
                return node.value;
            }
            return null;
        }
    }

    static class Node {
        String key;
        String value;

        Node prev;
        Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
