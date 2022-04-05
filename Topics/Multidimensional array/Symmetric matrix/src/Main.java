import java.util.Scanner;

class Main {

    static Scanner sc = new Scanner(System.in);
    static int dim;

    public static void main(String[] args) {
        dim = sc.nextInt();
        int[][] m = new int[dim][dim];

        //fill the matrix with input
        for (int i = 0; i < dim; i++) {
            m[i] = askRowOfInts();
        }
        //printMatrix(m);

        //check symmetry
        boolean isSymmetric = true;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < i; j++) {
                isSymmetric = isSymmetric && m[i][j] == m[j][i];
            }
        }

        //print result
        if (isSymmetric) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    public static boolean isItemSymmetric(int i, int j, int[][] matrix) {
        return matrix[i][j] == matrix[i][j];
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[]  askRowOfInts() {
        int[] integers = new int[dim];

        for (int i = 0; i < dim; i++) {
            integers[i] = askInt();
        }
        return integers;
    }

    public static int askInt() {
        int a = 0;
        boolean repeat = false;
        do {
            if (sc.hasNextInt()) {
                a = sc.nextInt();
            } else {
                System.out.println("enter number");
                sc.nextLine();
                repeat = true;
            }
        } while (repeat);
        return a;
    }
}