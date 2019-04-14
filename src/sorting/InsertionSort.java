package sorting;

public class InsertionSort extends Sort{

    @Override
    public Comparable[] sort(Comparable[] listToSort) {
        int count = 0;
        int Size = listToSort.length;
        for(int i=1;i<Size;i++){
            for(int k = i; k > 0 && less(listToSort[k] , listToSort[k-1]); k--){
                replace(k,k-1,listToSort);
                ++count;
            }
        }
        System.out.println("No Of Swaps "+count);
        return  listToSort;
    }
}