package sorting;

public class PriorityQueue {
    public static void main(String[] args) {
        MaxPQ<Integer> q = new MaxPQ<>(11);
        Integer a = new Integer("5");
        Integer b = new Integer("4");
        Integer c = new Integer("3");
        Integer d = new Integer("2");
        Integer e = new Integer("1");
        q.insert(c);
        q.traverse();
        q.insert(e);
        q.traverse();
        q.insert(d);
        q.traverse();
        q.insert(a);
        q.traverse();
        q.insert(b);
        q.traverse();
//        q.delMax();
//        q.traverse();
    }

}

class MaxPQ<key extends Comparable<key>> {
    private key[] pq;
    private int N = 0;

    MaxPQ(int maxSize){
        pq = (key[]) new Comparable[maxSize+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    void traverse(){
       for(int i=1;i<=N;i++){
           System.out.println(pq[i]);
       }
        System.out.println("_______________________________");
    }

    void insert(key v){
        pq[++N] = v;
        swim(N);
    }

    public key delMax(){
        key max = pq[1];

        exch(1,N--);

        pq[N+1] = null;
        sink(1);
        return  max;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i,int j){
        key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k){

        while (k >1 && less(k/2 ,k)){
//            if(k== 4){
                System.out.println("Exchange "+k/2+" with "+k);
                System.out.println(pq[k/2]+" :: "+pq[k]);
//            }
            exch(k/2,k);
            k /= 2;
        }
    }

    private void  sink(int k){
        while (2 * k < N){
            int j = 2 * k;
            if(j < N && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }
}