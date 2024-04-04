import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter two integers1: ");
        int number1 = input.nextInt();
        System.out.print("Enter two integers2: ");
        int number2 = input.nextInt();
        System.out.print(number1+"/"+number2+" is "+(number1/number2));
    }
}