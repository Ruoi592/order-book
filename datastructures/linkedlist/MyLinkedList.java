import java.util.NoSuchElementException;

public class MyLinkedList {
    
    private static class Node {
        int val;
        Node prev;
        Node next;

        Node (int val) {
            this.val = val;
        }
    }

        private Node head;
        private Node tail;
        private int size;

        public MyLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return (size == 0);
        }

        public static void main(String[] args) {
            MyLinkedList list = new MyLinkedList();
            System.out.println(list.size());
            System.out.println(list.isEmpty());
            list.addLast(1);
            list.addLast(2);
            list.addFirst(0);
            System.out.println(list.get(0));
            System.out.println(list.get(1));
            System.out.println(list.get(2));
            System.out.println(list.size());
        }

        public void addLast(int x) {
            Node node = new Node(x);
            if (isEmpty()) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            size++;
        }

        public void addFirst(int x) {
            Node node = new Node(x);
            if (isEmpty()) {
                head = node;
                tail = node;
            } else {
                head.prev = node;
                node.next = head;
                head = node;
            }
            size++;
        }

        public int get(int i) {
            if (i < 0 || i >= size) {
                throw new IndexOutOfBoundsException("index out of range: " + i);
            }
            Node x = head;
            for (int y = 0; y < i; y++) {
                x = x.next;
            }

            return x.val;
        }

        public int removeFirst() {
            if (size() == 0) {
                throw new NoSuchElementException("The list is empty.");
            }
            int value = head.val;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }

            size--;
            return value;
        }

        public int removeLast() {
            if (size() == 0) {
                throw new NoSuchElementException("The list is empty.");
            }
            int value = tail.val;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.prev;
                tail.next = null;
            }

            size--;
            return value;
        }

        public int remove(int i) {
            if (size == 0) {
                throw new NoSuchElementException("The list is empty.");
            } else if (i < 0 || i >= size) {
                throw new IndexOutOfBoundsException("Invalid index");
            }

            if (i == 0) {
                return removeFirst();
            } else if (i == size - 1) {
                return removeLast();
            }

            Node x = head;
            for (int y = 0; y < i; y++) {
                x = x.next;
            }
            Node previousNode = x.prev;
            Node nextNode = x.next;
            x.prev.next = nextNode;
            x.next.prev = previousNode;

            size--;
            return x.val;
        }
}
