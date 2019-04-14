package sorting;

public class SelectionSort extends Sort{

    @Override
    public Comparable[] sort(Comparable[] listToSort) {
        // first find the smallest element in the list
        int Size = listToSort.length;
        int count = 0;
        for(int i=0;i<Size;i++){
            int min = i;
            for(int j=i;j<Size;j++)
                if (less(listToSort[j], listToSort[i])) min = j;
            /*
             * Now We Got The Min Index
            */
            replace(min,i,listToSort);
            ++count;
        }
        System.out.println("No Of Swaps "+count);
        return listToSort;
    }

}
