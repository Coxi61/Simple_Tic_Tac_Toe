import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int[] numbers = new int[l];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.nextInt();
        }
        int tripleCount = 0;
        for (int i = 2; i <= numbers.length - 1; i++) {
            if (numbers[i - 2] == (numbers[i - 1] - 1) && numbers[i - 1] == (numbers[i] - 1)) {
                tripleCount++;
            }
        }

        System.out.print(tripleCount);
    }
}