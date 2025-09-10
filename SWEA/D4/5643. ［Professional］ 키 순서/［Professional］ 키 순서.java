import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n, m;
    static int[][] graph;
    static final int MAX = (int)1e8;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            m = Integer.parseInt(br.readLine());

            graph = new int[n+1][n+1];
            for (int[] line: graph) {
                Arrays.fill(line, MAX);
            }

            for (int i = 0; i < m; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a][b] = 1;
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (a == b) continue;
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        int ret = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int in = 1; in <= n; in++) {
                if (i == in) continue;
                if (graph[in][i] != MAX) cnt++;
            }

            for (int out = 1; out <= n; out++) {
                if (i == out) continue;
                if (graph[i][out] != MAX) cnt++;
            }

            if (cnt == n-1) ret++;
        }

        return ret;
    }

}