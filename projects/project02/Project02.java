package projects.project02;

/**
 * Compares all the contiguous sub-arrays of an input integer array and finds
 * the sub-array with the maximum sum.
 *
 *
 * The problem is solved with the following logic:
 *
 * Assume an array A[n]. For every position m of A, there are m sub-rrays
 * that start from position A[0 to m] and end at position A[m].
 *
 * I.e.:
 * SubArray 1: {A[0], A[1], A[2], ... A[m]}
 * SubArray 2: {A[1], A[2], ... A[m]}
 * SubArray 3: {A[2], ... A[m]}
 * SubArray ...
 * SubArray m: {A[m]}
 *
 * For every position m of A there is a local maximum sum (call it
 * locMaxSum(m)), which is the highest among the sums of the integers of
 * each sub-array.
 *
 * Now, consider the following:
 *
 * For m=0 there is one sub-array {A[0]}. So,
 * - locMaxSum(0) = A[0]
 *
 * For m=1 there are two sub-arrays {A[0], A[1]} and {A[1]}. So,
 * - locMaxSum(1) = max_of( (A[0] + A[1]), A[1] ).
 * But since A[0] is locMaxSum(0) we can rewrite as:
 * - locMaxSum(1) = max_of( (locMaxSum(0) + A[1]), A[1] ).
 *
 * For m=2 there are three sub-arrays {A[0], A[1], A[2]}, {A[1], A[2]} and
 * {A[2]}. So,
 * - locMaxSum(2) = max_of( (A[0] + A[1] + A[2]), (A[1] + A[2]), A[2] ).
 * But since the comparison max_of( (A[0] + A[1] + A[2]), (A[1] + A[2]) )
 * adds A[2] to both sides of a comparison we have already made - ie.
 * max_of( (A[0] + A[1]), A[1] ), which is locMaxSum(1) - we can rewrite as:
 * - locMaxSum(2) = max_of( (locMaxSum(1) + A[2]), A[2] ).
 *
 * Therefore, in the general case m we get:
 * - locMaxSum(m) = max_of( (locMaxSum(m-1) + A[m]), A[m] )
 *
 *
 * The logic of the program itself is as follows:
 *
 * We traverse the array A from position A[1] to A[n] and calculate the
 * locMaxSum for each position using the previous formula. To do so, we need
 * to keep track of the locMaxSum of the previous position in the variable
 * prevMaxSum.
 *
 * Finally, in each iteration we compare the locMaxSum to the highest
 * locMaxSum that we have found so far, which keep track of in the variable
 * maxSum.
 *
 */
public class Project02 {
    public static void main(String[] args) {

        int[] inputArr = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int locMaxSum = 0, prevMaxSum = 0, maxSum = 0;
        int startMaxPos = 0, endMaxPos = 0;

        for (int i = 0; i < inputArr.length; i++) {
            if (i == 0) {
                locMaxSum = inputArr[0];
                prevMaxSum = inputArr[0];
            } else {
                locMaxSum = (prevMaxSum + inputArr[i] > inputArr[i]) ? prevMaxSum + inputArr[i] : inputArr[i];
            }
            if (locMaxSum > maxSum) {
                maxSum = locMaxSum;
                if (prevMaxSum < 0)
                    startMaxPos = i;
                endMaxPos = i;
            }
            prevMaxSum = locMaxSum;
        }

        int[] outputArr = new int[endMaxPos - startMaxPos + 1];

        for (int i = 0, j = startMaxPos; i < outputArr.length; i++, j++) {
            outputArr[i] = inputArr[j];
        }

        System.out.printf("The maximum sum sub-array starts at position %d and ends at position %d\n", startMaxPos, endMaxPos);
        System.out.printf("The sum is %d\n", maxSum);
        System.out.println("The items of the sub-array are:");
        for (int item : outputArr) {
            System.out.printf("%d ", item);
        }
        System.out.println();

    }
}
