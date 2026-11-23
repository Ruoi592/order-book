import java.util.LinkedList;
import java.util.Random;

public class Fuzz {
    // Nếu a và b khác nhau thì dừng chương trình và báo lỗi
    static void check(int a, int b, String what) {
        if (a != b) {
            throw new RuntimeException(what + " khac nhau: MyLinkedList=" + a + ", Java=" + b);
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(1);

        for (int t = 0; t < 2000; t++) {
            MyLinkedList m = new MyLinkedList();
            LinkedList<Integer> j = new LinkedList<>();

            for (int s = 0; s < 60; s++) {
                int op = rand.nextInt(5);
                int v = rand.nextInt(100);

                if (op == 0) {
                    m.addFirst(v);
                    j.addFirst(v);
                } else if (op == 1) {
                    m.addLast(v);
                    j.addLast(v);
                } else if (j.isEmpty()) {
                    continue;
                } else if (op == 2) {
                    int a = m.removeFirst();
                    int b = j.removeFirst();
                    check(a, b, "removeFirst");
                } else if (op == 3) {
                    int a = m.removeLast();
                    int b = j.removeLast();
                    check(a, b, "removeLast");
                } else {
                    int i = rand.nextInt(j.size());
                    int a = m.remove(i);
                    int b = j.remove(i);
                    check(a, b, "remove(" + i + ")");
                }

                check(m.size(), j.size(), "size");
                for (int k = 0; k < m.size(); k++) {
                    check(m.get(k), j.get(k), "get(" + k + ")");
                }
            }
        }
        System.out.println("OK");
    }
}