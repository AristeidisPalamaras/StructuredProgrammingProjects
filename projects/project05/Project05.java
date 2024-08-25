package projects.project05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Project05 {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        boolean[][] seats = new boolean[12][30];
        int choice = 0;

        do {
            printMenu();
            try {
                choice = getChoice();
                choose(seats, choice);
            } catch (InputMismatchException e) {
                System.out.println("*** Invalid input. Not a number ***\n");
            } catch (IllegalArgumentException e) {
                System.out.println("*** Invalid input. Number out of range ***\n");
            }
        } while (choice != 4);
    }

    public static void printMenu() {
        System.out.println("Enter 1 to book a seat");
        System.out.println("Enter 2 to cancel a booking");
        System.out.println("Enter 3 to view booked seats");
        System.out.println("Enter 4 to exit");
    }

    public static int getChoice() throws InputMismatchException, IllegalArgumentException {
        int choice;

        if (!input.hasNextInt()) {
            input.nextLine();
            throw new InputMismatchException();
        }

        choice = input.nextInt();
        if (choice < 1 | choice > 4) {
            String s = input.nextLine();
            throw new IllegalArgumentException();
        }
        return choice;
    }

    public static void choose(boolean[][] seats, int choice) {
        switch (choice) {
            case 1:
                book(seats);
                break;
            case 2:
                cancel(seats);
                break;
            case 3:
                printBooked(seats);
                break;
            case 4:
                System.out.println("Exiting");
                break;
            default:
                System.out.println("Error");
        }
    }

    public static void printBooked(boolean[][] seats) {

        System.out.println("Booked seats:");
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j]) {
                    System.out.printf("%c%d\n", 'A' + i, j + 1);
                }
            }
        }
        System.out.println();
    }

    public static void book(boolean[][] seats) {
        int row;
        int col;

        System.out.println("Enter row (A-L) and seat (1-30) to book");
        row = input.next().charAt(0) - 'A';
        col = input.nextInt() - 1;

        if (row < 0 || row > 11 || col < 0 || col > 29) {
            input.nextLine();
            System.out.println("*** Error. Row or seat out of range ***\n");
            return;
        }
        if (isBooked(seats, row, col)) {
            input.nextLine();
            System.out.println("*** Error. Seat is already booked ***\n");
            return;
        }
        seats[row][col] = true;
        System.out.printf("Seat %c%d booked\n\n", row + 'A', col + 1);
    }

    public static void cancel(boolean[][] seats) {
        int row;
        int col;

        System.out.println("Enter row (A-L) and seat (1-30) to cancel");
        row = input.next().charAt(0) - 'A';
        col = input.nextInt() - 1;

        if (row < 0 || row > 11 || col < 0 || col > 29) {
            System.out.println("*** Error. Row or seat out of range ***\n");
            return;
        }
        if (!isBooked(seats, row, col)) {
            System.out.println("*** Error. Seat is not booked ***\n");
            return;
        }
        seats[row][col] = false;
        System.out.printf("Seat %c%d cancelled\n\n", row + 'A', col + 1);
    }

    public static boolean isBooked(boolean[][] seats, int row, int col) {
        return seats[row][col];
    }
}
