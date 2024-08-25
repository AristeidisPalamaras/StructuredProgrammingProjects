package projects.project03;

import java.util.Arrays;

public class Project03 {
    public static void main(String[] args) {

        int[][] inputArr = new int[][] {{1,2},{3,4}};
        int[][] shallowCopyArr;
        int[][] deepCopyArr;

        System.out.print("inputArr: ");
        printArr(inputArr);

        shallowCopyArr = shallowCopy(inputArr);
        deepCopyArr = deepCopy(inputArr);

        System.out.print("shallowCopyArr: ");
        printArr(shallowCopyArr);

        System.out.print("deepCopyArr: ");
        printArr(deepCopyArr);

        System.out.println("\nChange shalowCopyArr[0][0] to 42\n");
        shallowCopyArr[0][0] = 42;

        System.out.print("inputArray: ");
        printArr(inputArr);

        System.out.print("shallowCopyArr: ");
        printArr(shallowCopyArr);

        System.out.print("deepCopyArr: ");
        printArr(deepCopyArr);

        System.out.println("\nChange deepCopyArr[1][1] to 42\n");
        deepCopyArr[1][1] = 42;

        System.out.print("inputArray: ");
        printArr(inputArr);

        System.out.print("shallowCopyArr: ");
        printArr(shallowCopyArr);

        System.out.print("deepCopyArr: ");
        printArr(deepCopyArr);
    }

    public static void printArr(int[][] arr) {
        System.out.print("{");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("{");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d%s", arr[i][j], (j != arr[i].length - 1) ? ", " : "");
            }
            System.out.printf("}%s", (i != arr.length -1) ? ", " : "");
        }
        System.out.print("}\n");
    }

    public static int[][] shallowCopy(int[][] arr) {
        int[][] copy = Arrays.copyOf(arr, arr.length);
        return copy;
    }

    public static int[][] deepCopy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

}
