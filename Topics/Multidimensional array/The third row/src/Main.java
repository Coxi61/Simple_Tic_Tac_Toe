class ArrayOperations {
    public static void printTheThirdRow(int[][] twoDimArray) {
        for (int i = 0; i < twoDimArray[2].length; i++) {
            System.out.print(twoDimArray[2][i] + " ");
        }

    }
}


public class Main{
    public static void main(String[] args) {
        int[][] twoDimArray = {{1,1,1,1,1}, {2,2,2,2,2,2,2,2},{3,3,3,3,3,3,3,3,3,3,3}};
        ArrayOperations.printTheThirdRow(twoDimArray);
    }
}
