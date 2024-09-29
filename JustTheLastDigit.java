import java.util.Scanner;

public class JustTheLastDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] m = new int[n][n];
        int[][] ans = new int[n][n];
        for(int i=0; i<n; i++){
            String line = sc.next();
            for(int j=0; j<n; j++)
                m[i][j] = line.charAt(j) - '0';
        }
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(m[i][j] != 1)
                    continue;
                ans[i][j] = 1;
                for(int k=j+1; k<n; k++){
                    m[i][k] -= m[j][k];
                    if(m[i][k] < 0)
                        m[i][k] += 10;
                }
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++)
                System.out.print(ans[i][j]);
            System.out.println();
        }
    }
}
