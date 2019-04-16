package sorting;

class MergeSort extends Sort{

    private void  mergeSort(int start, int end, Comparable[] arr){
        if(start < end){
            int mid = start + (end - start) /2;
            mergeSort(start, mid, arr);
            mergeSort(mid+1, end, arr);
            merge(start, mid, end, arr);
        }
    }

    private void merge(int low,int mid,int high,Comparable[] a){
      int i,j,k;

      // create left array
        int n1 = mid-low + 1;
        int n2 = high - mid;

        Comparable[] left = new Comparable[n1];
        Comparable[] right = new Comparable[n2];

        /**
         * Copy the data to the left array
         */
        for(i=0;i<n1;i++){
            left[i] = a[i+low];
        }

        /**
         * Copy the data to the right array
         */
        for(j=0;j<n2;j++){
            right[j] = a[mid+j+1];
        }

        i=0;
        j=0;
        k=low;

        while (i < n1 || j < n2){
            if(i < n1 && j < n2) {
                if (less(left[i], right[j])) {
                    a[k] = left[i];
                    i++;
                } else {
                    a[k] = right[j];
                    j++;
                }
            }else if(i < n1){
                a[k] = left[i++];
            }else if(j < n2){
                a[k] = right[j++];
            }else{
                a[k] = left[i++];
            }
            k++;
        }
    }

    @Override
    Comparable[] sort(Comparable[] listToSort) {
        mergeSort(0,listToSort.length-1,listToSort);
        return  listToSort;
    }
}
