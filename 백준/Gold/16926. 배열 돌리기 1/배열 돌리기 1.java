import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, r;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         r = Integer.parseInt(st.nextToken());

         arr = new int[n][m];
         for (int i = 0; i < n; i++) {
             st = new StringTokenizer(br.readLine());
             for (int j = 0; j < m; j++) {
                 arr[i][j] = Integer.parseInt(st.nextToken());
             }
         }

         go();
         for (int[] line: arr) {
             for (int k : line) {
                 System.out.print(k + " ");
             }
             System.out.println();
         }
    }

    static void go() {
        int layer = Math.min(n, m) / 2;
        for (int l = 0; l < layer; l++) {
            int loop = ((n - l*2) + (m - l*2)) * 2 - 4;
            int shortR = r % loop;
            for (int rr=0; rr<shortR; rr++) {
                int save = arr[l][l];

                // 왼쪽
                for (int i=l+1; i<n-l; i++) {
                    int tmp = arr[i][l];
                    arr[i][l] = save;
                    save = tmp;
                }

                // 아래
                for (int j=l+1; j<m-l; j++) {
                    int tmp = arr[n-1-l][j];
                    arr[n-1-l][j] = save;
                    save = tmp;
                }

                // 오른쪽
                for (int i=n-l-2; i>=l; i--) {
                    int tmp = arr[i][m-l-1];
                    arr[i][m-l-1] = save;
                    save = tmp;
                }

                // 위
                for (int j=m-l-2; j>=l; j--) {
                    int tmp = arr[l][j];
                    arr[l][j] = save;
                    save = tmp;
                }

            }

        }
    }
}
