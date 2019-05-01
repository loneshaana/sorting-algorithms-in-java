package sorting;

public class QuickSort extends Sort {
    @Override
    Comparable[] sort(Comparable[] listToSort) {
        sort(listToSort,0,listToSort.length-1);
        return listToSort;
    }

    private void sort(Comparable[] a, int low, int high){
        if(high <= low) return;
        int j = partition(a,low,high);
        sort(a,low,j-1);
        sort(a,j+1,high);
    }

    private int partition(Comparable[] a,int low,int high){
        int i = low;
        int j = high+1;
        // random Comparable
        Comparable item = a[low];
        while (true){
            // scan from left - to right for greater element
            while (!less(item , a[++i])){
                if(i == high) break;
            }

            while (!less(a[--j],item)){
                if(j == low)  break;
            }
            if(i >= j) break;
            replace(i,j,a);

        }
        replace(low,j,a);

        return j;
    }
}
