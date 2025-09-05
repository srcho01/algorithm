import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n, k;
    static int[][] map;
    static int[][] direction = {
            {0, -1}, {-1, 0}, {1, 0}, {0, 1}
    };
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        int max = -1;
        for (int[] line: map) {
            for (int i: line) {
                max = Math.max(max, i);
            }
        }

        int ans = 1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (map[i][j] == max) {
                    visited = new boolean[n][n];
                    ans = Math.max(ans, dfs(i, j, map[i][j], false));
                }
            }
        }

        return ans;
    }

    static int dfs(int x, int y, int h, boolean isCut) {
        int ret = 1;
        
        visited[x][y] = true;
        for (int[] d: direction) {
            int nx = x + d[0], ny = y + d[1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                continue;
            }

            if (!visited[nx][ny]) {
                if (h > map[nx][ny]) {
                    ret = Math.max(ret, dfs(nx, ny, map[nx][ny], isCut) + 1);
                } else if (!isCut) {
                    if (map[nx][ny] - h + 1 <= k) {
                        ret = Math.max(ret, dfs(nx, ny, map[x][y]-1, true) + 1);
                    }
                }
            }
        }

        visited[x][y] = false;
        return ret;
    }
}
