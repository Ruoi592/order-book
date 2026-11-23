import java.util.ArrayList;
public class Experiment {
    public static void main(String[] args) {
       int[] sizes = {100000, 200000, 400000};
       
       for (int n : sizes) {
        MyLinkedList m = new MyLinkedList();
        for (int i = 0; i < n; i++) {
            m.addLast(i);
        }
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            m.removeFirst();
        }
        long msList = (System.nanoTime() - start) / 1000000;

        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(i);
        }
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a.remove(0);
        }
        long msArr = (System.nanoTime() - start) / 1000000;

        System.out.println("n = " + n + " MyLinkedList: " + msList + " ms, ArrayList: " + msArr + " ms.");
       }
    }
}
