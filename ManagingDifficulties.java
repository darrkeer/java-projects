import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManagingDifficulties {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int tt=0; tt<t; tt++){
            int n = sc.nextInt();
            int[] a = new int[n];
            for(int i=0; i<n; ++i)
                a[i] = sc.nextInt();
            Map<Integer, Integer> mp = new HashMap<>();
            long cnt = 0;
            for(int j=n-1; j>=0; j--){
                for(int i=0; i<j; i++){
                    int ak = 2 * a[j] - a[i];
                    cnt += mp.getOrDefault(ak, 0);
                }
                mp.put(a[j], mp.getOrDefault(a[j], 0) + 1);
            }
            System.out.println(cnt);
        }
    }
}
