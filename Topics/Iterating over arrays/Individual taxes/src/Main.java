import java.util.*;

public class Main {
    public static void main(String[] args) {

        final double hundred = 100D;
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int[] income = new int[l];
        int[] percent = new int[l];
        double maxTax = 0; //first this was my mistake : double needs instead of int
        int maxTaxIndex = 0;

        for (int i = 0; i < l; i++) {
            income[i] = sc.nextInt();
        }

        double tax = 0;
        for (int i = 0; i < l; i++) {
            percent[i] = sc.nextInt();
            tax = (double) income[i] / hundred * percent[i];
            if (maxTax < tax) {
                maxTax =  tax;
                maxTaxIndex = i;
            }
        }

        System.out.println(maxTaxIndex + 1);




    }
}