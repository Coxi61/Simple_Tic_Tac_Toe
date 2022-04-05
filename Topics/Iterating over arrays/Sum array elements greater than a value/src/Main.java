import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int[] numbers = new int[l];
        for (int i = 0; i < l; i++) {
            numbers[i] = sc.nextInt();
        }
        int lowLimit = sc.nextInt();

        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += lowLimit < numbers[i] ? numbers[i] : 0;
        }

        System.out.print(sum);

    }
}