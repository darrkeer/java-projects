import java.util.Scanner;

public class AccurateMovement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();
        System.out.println(2 * (int)Math.ceil((double)(n - b) / (double)(b - a)) + 1);
    }
}
