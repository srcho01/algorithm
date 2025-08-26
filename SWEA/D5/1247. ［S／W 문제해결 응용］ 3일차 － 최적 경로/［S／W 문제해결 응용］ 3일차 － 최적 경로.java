import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int[][] customers;

    static int ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            customers = new int[n+2][2];
            customers[0][0] = Integer.parseInt(st.nextToken());
            customers[0][1] = Integer.parseInt(st.nextToken());
            customers[n+1][0] = Integer.parseInt(st.nextToken());
            customers[n+1][1] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                customers[i+1][0] = Integer.parseInt(st.nextToken());
                customers[i+1][1] = Integer.parseInt(st.nextToken());
            }

            ans = Integer.MAX_VALUE;
            visited = new boolean[n+2];
            go(1, 0, 0);
            System.out.println("#" + tc + " " + ans);
        }
    }

    static void go(int depth, int prev, int total) {
        if (depth == n+1) {
            total += dist(prev, n+1);
            ans = Math.min(ans, total);
            return;
        }

        if (total >= ans) return;

        for (int i = 1; i < n+1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                go(depth+1, i, total + dist(prev, i));
                visited[i] = false;
            }
        }

    }

    static int dist(int x, int y) {
        return Math.abs(customers[x][0] - customers[y][0]) + Math.abs(customers[x][1] - customers[y][1]);
    }
}
