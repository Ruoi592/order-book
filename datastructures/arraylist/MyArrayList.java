public class MyArrayList {
    private int[] data;
    private int size;

    public MyArrayList() {
        data = new int[4];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return data.length;
    }

    public void add(int x) {
        if(size == data.length) {
            grow();
        }
        data[size] = x;
        size++;
    }
    
    public int get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("index " + i);
        } else {
            return data[i];
        }
    }

    public void set(int i, int x) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("index " + i);
        } else {
            data[i] = x;
        }
    }

    public int removeLast() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("list is empty");
        }
        int a = data[size - 1];
        data[size - 1] = 0;
        size--;
        return a;
    }

    private void grow() {
        int newCapacity = data.length * 2;
        int[] newData = new int[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public void add(int i, int x) {
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException("index " + i);
        } else {
            if (size == data.length) {
                grow();
            }
            for (int j = size - 1; j >= i; j--) {
                data[j + 1] = data[j];
            }
            data[i] = x;
            size++;
        }
    }

    public int remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("index " + i);
        } else {
            int a = data[i];
            for (int j = i; j < size - 1; j++) {
                data[j] = data[j+1];
            }
            data[size - 1] = 0;
            size--;
            return a;
        }
    }
}
