package projects.project01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads an input file containing integers from 1 to 49. Creates all possible
 * combinations of these integers. Raises the combinations through several
 * filters. Writes to an output file the combinations that pass the filters.
 */

public class Project01 {
    public static void main(String[] args) {

        File inputFile = new File("src/projects/project01/inputProject01.txt");
        File outputFile = new File("src/projects/project01/ouputProject01.txt");

        if (outputFile.exists()) {
            outputFile.delete();
        }

        ArrayList<Integer> nums = new ArrayList<Integer>();
        int[] row = new int[6];

        try
                (Scanner in = new Scanner(inputFile);
                 PrintStream ps = new PrintStream(new FileOutputStream(outputFile, true)))
        {

            while (in.hasNextInt()) {
                nums.add(in.nextInt());
            }

            nums.sort(null);

            for (int i = 0; i < nums.size(); i++) {
                for (int j = i+ 1; j < nums.size(); j++) {
                    for (int k = j + 1; k < nums.size(); k++) {
                        for (int m = k + 1; m < nums.size(); m++) {
                            for (int n = m + 1; n < nums.size(); n++) {
                                for (int o = n + 1; o < nums.size(); o++) {
                                    row[0] = nums.get(i);
                                    row[1] = nums.get(j);
                                    row[2] = nums.get(k);
                                    row[3] = nums.get(m);
                                    row[4] = nums.get(n);
                                    row[5] = nums.get(o);

                                    if (checkEven(row, 4) &&
                                            checkOdd(row, 4) &&
                                            checkCont(row, 2) &&
                                            checkOnes(row, 3) &&
                                            checkTens(row, 3))
                                    {
                                        for (int item : row) {
                                            ps.printf("%d ", item);
                                        }
                                        ps.println();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return true if the input array has equal or less than a threshold of
     * even integers.
     *
     * @param arr           the input array
     * @param threshold    the threshold
     * @return              true/false
     */
    public static boolean checkEven(int[] arr, int threshold) {
        int count = 0;

        for (int i : arr) {
            if ( (i % 2) == 0) {
                count++;
            }
        }
        return (count <= threshold);
    }

    /**
     * Return true if the input array has equal or less than a threshold
     * of odd integers.
     *
     * @param arr           the input array
     * @param threshold    the threshold
     * @return              true/false
     */
    public static boolean checkOdd(int[] arr, int threshold) {
        int count = 0;

        for (int i : arr) {
            if ( (i % 2) != 0) {
                count++;
            }
        }
        return (count <= threshold);
    }

    /**
     * Return true if the input array has less or equal than a threshold of
     * continuous integers.
     *
     * @param arr           the input array
     * @param threshold     the threshold
     * @return              true/false
     */
    public static boolean checkCont(int[] arr, int threshold) {
        int count = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1] + 1) {
                count++;
            } else {
                count = 1;
            }

            if (count > threshold) {
                return false;
            }
        }

        return true;
    }

    /**
     * Return true if the input array has less or equal than a threshold of
     * integers with the same number in the ones position.
     *
     * @param arr           the input array
     * @param threshold     the threshold
     * @return              true/false
     */
    public static boolean checkOnes(int[] arr, int threshold) {
        int[] ones = new int[10];

        for (int i : arr) {
            ones[i % 10]++;
        }

        for (int i : ones) {
            if (i > threshold) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return true if the input array has less or equal than a threshold of
     * integers with the same number in the tens position.
     *
     * @param arr           the input array
     * @param threshold     the threshold
     * @return              true/false
     */
    public static boolean checkTens(int[] arr, int threshold) {
        int[] tens = new int[5];

        for (int i : arr) {
            tens[i / 10]++;
        }

        for (int i : tens) {
            if (i > threshold) {
                return false;
            }
        }
        return true;
    }
}
