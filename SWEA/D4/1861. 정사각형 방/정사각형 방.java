import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static int[][] room;
    static int[][] d = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };
    static int num;
    static int cnt;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            n = Integer.parseInt(br.readLine());

            room = new int[n][n];
            dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], -1);
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            cnt = -1;
            num = Integer.MAX_VALUE;
            go();
            System.out.println("#" + testCase + " " + num + " " + cnt);
        }
    }

    static void go() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bfs(i, j);
            }
        }
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        int sum = 1;
        while (!queue.isEmpty()) {
            int[] polled =  queue.poll();
            int x = polled[0], y = polled[1];

            for (int i=0; i<4; i++){
                int nx = x + d[i][0];
                int ny = y + d[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (!visited[nx][ny] && room[nx][ny] == room[x][y] + 1) {
                    if (dp[nx][ny] != -1) {
                        sum += dp[nx][ny];
                        break;
                    }

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    sum++;
                }
            }
        }

        dp[sx][sy] = sum;
        if (cnt < sum) {
            cnt = sum;
            num = room[sx][sy];
        } else if (cnt == sum) {
            num = Math.min(room[sx][sy], num);
        }
    }

}
