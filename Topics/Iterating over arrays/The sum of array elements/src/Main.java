import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();

        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += sc.nextInt();
        }

        System.out.print(sum);
    }
}