package PQ_Heaps;


import java.util.stream.IntStream;

/*
    Root is greater than its child
 */
public class MaxHeap {

    private int size = 0;
    private int[] Heap;
    private int maxSize;

    private static final int FRONT =1;

    private MaxHeap(int maxSize){
        this.maxSize = maxSize;
        Heap = new int[maxSize+1];
        Heap[0] = Integer.MAX_VALUE;
    }
    private int parent(int index){
        return index/2;
    }

    private int leftChild(int index){
        return 2*index;
    }

    private int rightChild(int index){
        return 2*index+1;
    }

    private boolean isLeaf(int index){
        return index >= size / 2 && index <= size;
    }

    private void swap(int fpos,int spos){
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void maxHeapify(int index){
        // check every thing is according the maxHeap
        if(!isLeaf(index)){
            /*
                index is not the leaf
             */
            if(Heap[index] < Heap[leftChild(index)] || Heap[index] < Heap[rightChild(index)]){
                if(Heap[leftChild(index)] > Heap[rightChild(index)]){
                    swap(index,leftChild(index));
                    maxHeapify(leftChild(index));
                }else {
                    swap(index,rightChild(index));
                    maxHeapify(rightChild(index));
                }
            }
        }
    }

    private void extendArray(){
        this.maxSize = maxSize+2; // Add 2 slots if array is full
        int[] newHeap = new int[maxSize];
        IntStream.range(0, Heap.length).forEach(i -> newHeap[i] = Heap[i]);
        Heap = newHeap;
    }

    private void insert(int data){
        if(size == maxSize-1){
           extendArray();
        }

        Heap[++size] = data;
        int current = size;
        while (Heap[current] > Heap[parent(current)]){
            // then swap
            swap(current,parent(current));
            current = parent(current);
        }
    }

    private void maxHeap(){
        for(int pos =(size)/2;pos>=1;pos--){
            maxHeapify(pos);
        }
    }

    private int remove(){
        int popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        maxHeapify(FRONT);
        return popped;
    }

    private int remove(int index){
        if(index > size){
            System.out.println("Index Greater than size of heap");
            return -1;
        }else {
            int popped = Heap[index];
            Heap[index] = Heap[size--];
            maxHeapify(index);
            return popped;
        }
    }

    private void print(){
        System.out.println("\t__________________________________________________________________________");
        for(int i=1;i<=size /2;i++){
            System.out.println("Parent "+Heap[i]+" Left Child "+Heap[2*i]+" Right Child "+Heap[2*i+1]);
        }
    }

    public static void main(String[] args) {
        MaxHeap maxHeap = new MaxHeap(3);
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);
        maxHeap.maxHeap(); // Heapify the tree
        maxHeap.print();
        System.out.println("Size of Heap :: "+ maxHeap.maxSize);
        System.out.println("Max Element of MaxHeap is "+maxHeap.remove());
        maxHeap.print();

    }
}
