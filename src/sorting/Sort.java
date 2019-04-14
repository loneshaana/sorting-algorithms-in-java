package sorting;

abstract class  Sort {
    abstract Comparable[] sort(Comparable[] listToSort);

    boolean less(Comparable a,Comparable b){
        return a.compareTo(b) < 0;
    }

    void replace(int to , int with , Comparable[] a){
        Comparable temp = a[to];
        a[to] = a[with];
        a[with] = temp;
    }
}
