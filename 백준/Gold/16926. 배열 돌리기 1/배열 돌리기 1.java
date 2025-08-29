import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
            Deque<Integer> q = new ArrayDeque<>();
            int a = l, b = l;
            for (a = l; a < n-1-l; a++) {
                q.offer(arr[a][b]);
            }
            for (b = l; b < m-1-l; b++) {
                q.offer(arr[a][b]);
            }
            for (a = n-1-l; a>l; a--) {
                q.offer(arr[a][b]);
            }
            for (b = m-1-l; b>l; b--) {
                q.offer(arr[a][b]);
            }

            int loop = ((n - l*2) + (m - l*2)) * 2 - 4;
            int rotate = r % loop;
            for (int rr=0; rr<rotate; rr++) {
                q.offerFirst(q.pollLast());
            }

            a = l; b = l;
            for (a = l; a < n-1-l; a++) {
                arr[a][b] = q.poll();
            }
            for (b = l; b < m-1-l; b++) {
                arr[a][b] = q.poll();
            }
            for (a = n-1-l; a>l; a--) {
                arr[a][b] = q.poll();
            }
            for (b = m-1-l; b>l; b--) {
                arr[a][b] = q.poll();
            }
        }
    }

}
