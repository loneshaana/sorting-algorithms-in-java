/*
    Write a program to get all the 3 digits which make the sum equal to 0;
    for eq {-4,-1,-1,0,1,2}
            -1,-1,2 == 0
            -1,1,0 == 0
 */

package interview_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ThreeSum {
    private static ArrayList<int[]> threeSum(int[] arr){
        ArrayList<int[]> results = new ArrayList<>();
        Arrays.sort(arr); // sort the arrays first
        if(arr.length == 3){
            if(arr[0] + arr[1] + arr[2] == 0){
                results.add(new int[]{arr[0],arr[1],arr[2]});
            }
            return results;
        }
        for(int i=0;i<arr.length-3;i++){
            if(i == 0 || arr[i] != arr[i-1]){
                int start = i+1;
                int end = arr.length-1;
                while (start < end) {
                    if (arr[i] + arr[start] + arr[end] == 0) {
                        results.add(new int[]{arr[i], arr[start], arr[end]});
                    }
                    if (arr[i] + arr[start] + arr[end] < 0) {
                        int currentStart = start;
                        while (arr[currentStart] == arr[start] && start < end) { // get the unique element than the previous
                            start++;
                        }
                    } else {
                        int currentEnd = end;
                        while (arr[currentEnd] == arr[end] && start < end) {
                            end--;
                        }
                    }
                }
            }
        }
        return results;
    }

    /**
        Another implementation using the set to store the
        elements which had being processed;
     */
    private static ArrayList<int[]> threeSum1(int[] arr){
        ArrayList<int[]> results = new ArrayList<>();
        Arrays.sort(arr); // sort the arrays first
        if(arr.length == 3){
            if(arr[0] + arr[1] + arr[2] == 0){
                results.add(new int[]{arr[0],arr[1],arr[2]});
            }
            return results;
        }
        for(int i=0;i<arr.length-3;i++){
            if(i == 0 || arr[i] != arr[i-1]){
                Set<Integer> processed = new HashSet<>();
                int start = i+1;
                int end = arr.length-1;
                while (start < end) {
                    processed.add(arr[start]);
                    processed.add(arr[end]);
                    if (arr[i] + arr[start] + arr[end] == 0) {
                        results.add(new int[]{arr[i], arr[start], arr[end]});
                    }
                    if (arr[i] + arr[start] + arr[end] < 0) {
                        while (processed.contains(arr[start]) && start < end){
                            start++;
                        }
                    } else {
                        while (processed.contains(arr[end]) && start < end){
                            end--;
                        }
                    }
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        ArrayList<int[]> results = threeSum1(new int[]{-4,-1,-1,0,1,2});
        for(int[] arr : results){
            System.out.println(arr[0]+" :: "+arr[1]+" :: "+arr[2]);
        }
    }
}
