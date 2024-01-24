package base.core.leetcode.two_pointers;

public class MergeTwoSortedArrays {
    public static int[] mergeArrays(int[] array1, int[] array2) {
        // Boundary Condition Check

        int[] arrayMerged = new int[array1.length + array2.length];
        
        int index1 = 0;
        int index2 = 0;
        int indexMerged = 0;
      
        while (index1 < array1.length && index2 < array2.length) {
            if (array1[index1] < array2[index2]) {
                arrayMerged[indexMerged++] = array1[index1++];
            } else {
                arrayMerged[indexMerged++] = array2[index2++];
            }
        }
        
        while (index1 < array1.length) {
            arrayMerged[indexMerged++] = array1[index1++]; 
        }
      
        while (index2 < array2.length) {
            arrayMerged[indexMerged++] = array2[index2++];
        }
      
        return arrayMerged;
    }
}
