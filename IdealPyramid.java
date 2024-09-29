import java.util.Scanner;

public class IdealPyramid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] h = new int[n];
        for(int i=0; i<n; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            h[i] = sc.nextInt();
        }
        int INF = 2000000000;
        int min_x = INF;
        int max_x = -INF;
        int min_y = INF;
        int max_y = -INF;
        for(int i=0; i<n; i++){
            min_x = Math.min(min_x, x[i] - h[i]);
            max_x = Math.max(max_x, x[i] + h[i]);
            min_y = Math.min(min_y, y[i] - h[i]);
            max_y = Math.max(max_y, y[i] + h[i]);
        }
        int H = (int) Math.ceil(1.0 * Math.max(max_x - min_x, max_y - min_y) / 2);
        int X = (int) (1.0 * (max_x + min_x) / 2);
        int Y = (int) (1.0 * (max_y + min_y) / 2);
        System.out.println(X + " " + Y + " " + H);
    }
}
