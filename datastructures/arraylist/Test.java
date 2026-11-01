public class Test {
    public static void main(String[] args) {
        int[] sizes = {10000, 100000, 200000};
        for (int n : sizes) {
            long start = System.nanoTime();
            MyArrayList list = new MyArrayList();
            for (int i = 0; i < n; i++) {
                list.add(i);
            }
            long ms = (System.nanoTime() - start) / 1000000;
            System.out.println("n = " + n + ": " + ms + ", ms, size = " + list.size() + ", capacity = " + list.capacity());
        }
    }
}
