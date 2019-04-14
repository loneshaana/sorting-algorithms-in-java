package sorting;
class IsSorted{
    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    private static boolean isSorted(Comparable[] l){
        for(int i=1;i<l.length;i++){
            if(less(l[i],l[i-1])) return  false;
        }
        return true;
    }

    /**
     *
     * this is tightly couples with the selection sort
     *
     * TODO Make it loosly coupled with any sorting algorithm;
     */
    public static void main(String[] args){
        String[] alphabets = "a,b,c,d,f,e".split(",");
        Integer[] numbers = new Integer[]{9,8,7,6,5,4,3,2,1,0};

        Comparable[] toSort = numbers;

        SelectionSort ss = new SelectionSort();
        InsertionSort is = new InsertionSort();

        System.out.println("Is Sorted "+isSorted(toSort));
        ss.sort(toSort);
        for(Comparable item : toSort){
            System.out.println(item);
        }
        assert isSorted(toSort);
        System.out.println("Is Sorted "+isSorted(toSort));
    }
}