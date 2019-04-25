package sorting;

/*
* Insertion Sort is slow for huge set of data
* The alternative for that is shell sort which is based on
* the insertion sort
*/

 class ShellSort extends Sort {

    @Override
    Comparable[] sort(Comparable[] listToSort) {
        int Size = listToSort.length;
        int gap = Size/2;
        int count = 0;
        while (gap >= 1){
            for(int i=0;i<Size;i++){
                for(int j=i+gap;j <Size && less(listToSort[j],listToSort[i]);j += gap){
                    replace(i,j,listToSort);
                    ++count;
                }
            }
            gap /=2;
        }
        System.out.println("No Of Swaps USING Shell Sort "+count);
        return  listToSort;
    }
}