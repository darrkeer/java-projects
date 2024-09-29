public class Reverse {
    static final int N = 1000010;
    public static void main(String[] args) {
        int[][] arr = new int[N][];
        int[] line = new int[N];

        int size = 0;

        FastScanner linesc = new FastScanner(System.in);
        String line_s;
        while ((line_s = linesc.getNextLine()) != null) {
            int n = 0;
            FastScanner numsc = new FastScanner(line_s);
            String num_s;
            while ((num_s = numsc.getNext()) != null) {
                line[n] = Integer.parseInt(num_s);
                n++;
            }
            arr[size] = new int[n];

            for (int i = 0; i < n; ++i)
                arr[size][i] = line[i];
            size++;
        }


        for (int i = size - 1; i >= 0; i--) {
            for (int j = arr[i].length - 1; j >= 0; j--) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}