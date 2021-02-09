import java.util.Arrays;

public class PriorityQueue<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] heap;
    private int size;
    private int capacity;
    
    public PriorityQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    public PriorityQueue(int initCapacity) {
        heap = (T[]) new Comparable[initCapacity];
        capacity = initCapacity;
    }
    
    public void insert(T newElement) {
        if (size == capacity)
            increaseCapacity();
        heapifyUp(size, newElement);
        size++;
    }
    
    public T findMin() {
        return heap[0];
    }
    
    public void delete(int deleteIndex) {
        if (deleteIndex < 0 || deleteIndex > size-1)
            System.out.println("illegal index");
        else {
            T freeElement = heap[--size];
            heap[size] = null;
            if (deleteIndex != size)
                heapifyDown(deleteIndex, freeElement);
        }
    }
    
    public T extractMin() {
        T priorityElement = heap[0];
        if (priorityElement != null) {
            delete(0);
        }
        return priorityElement; 
    }
    
    public int size() {
        return size;
    }
    
    public int capacity() {
        return capacity;
    }

    private void heapifyUp(int index, T key) {
        while (index > 0) {
            int parentIndex = (index-1) / 2;
            T parent = heap[parentIndex];
            if (key.compareTo(parent) >= 0)
                break;
            heap[index] = parent;
            index = parentIndex;
        }
        heap[index] = key;
    }
    
    private void heapifyDown(int index, T key) {
        while (index < size/2) {
            int childIndex = 2*index + 1;
            T child = heap[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < size && child.compareTo((T) heap[rightIndex]) > 0) {
                childIndex = rightIndex;
                child = heap[rightIndex];
            }
            if (key.compareTo(child) <= 0)
                break;
            heap[index] = child;
            index = childIndex;
        }
        heap[index] = key;
    }

    private void increaseCapacity() {
        heap = Arrays.copyOf(heap, capacity+10);
        capacity += 10;
    }
    
    private void print() {
        if (size == 0)
            System.out.println("empty queue");
        else {
            System.out.print("queue is: ");
            for (int i = 0; i < capacity; i++)
                System.out.print(heap[i] + " ");
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.print();
        System.out.println("size is: " + q.size());
        System.out.println("capacity is: " + q.capacity());
        q.insert(17);
        q.print();
        q.insert(11);
        q.print();
        q.insert(4);
        q.print();
        System.out.println("min is: " + q.findMin());
        System.out.println("size is: " + q.size());
        System.out.println("capacity is: " + q.capacity());
        q.insert(12);
        q.print();
        q.insert(8);
        q.print();
        q.insert(3);
        q.print();
        System.out.println("min is: " + q.findMin());
        System.out.println("size is: " + q.size());
        System.out.println("capacity is: " + q.capacity());
        q.insert(6);
        q.print();
        q.insert(5);
        q.print();
        q.insert(7);
        q.print();
        q.insert(9);
        q.print();
        q.insert(2);
        q.print();
        System.out.println("min is: " + q.findMin());
        System.out.println("size is: " + q.size());
        System.out.println("capacity is: " + q.capacity());
        System.out.println("extract min: " + q.extractMin());
        q.print();
        System.out.println("extract min: " + q.extractMin());
        q.print();
        System.out.println("delete element at 7: ");
        q.delete(7);
        q.print();
    }
}
