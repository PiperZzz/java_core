package base.core.leetcode.two_pointers;

public class MergeTwoSortedArrays {
    public static int[] mergeArrays(int[] array1, int[] array2) {
        // Boundary Condition Check - Revisit

        // Initialization - Solution
        int[] arrayMerged = new int[array1.length + array2.length];
        
        // Initialization - Pointers
        int index1 = 0;
        int index2 = 0;
        int indexMerged = 0;
        
        // Merge
        while (index1 < array1.length && index2 < array2.length) { // When either of the two arrays is looped through, stop the loop
            if (array1[index1] < array2[index2]) { // When the current element of array1 is smaller than the current element of array2
                arrayMerged[indexMerged++] = array1[index1++]; // Copy the current element of array1 to the merged array
                // indexMerged++; // Move the pointer of the merged array
                // index1++; // Move the pointer of array1
            } else {
                arrayMerged[indexMerged++] = array2[index2++]; // Copy the current element of array2 to the merged array
            }
        }
        
        while (index1 < array1.length) { // When array1 is not looped through, copy the remaining elements of array1 to the merged array
            arrayMerged[indexMerged++] = array1[index1++];
        }
      
        while (index2 < array2.length) { // When array2 is not looped through, copy the remaining elements of array2 to the merged array
            arrayMerged[indexMerged++] = array2[index2++];
        }
        
        return arrayMerged;
    }
}
