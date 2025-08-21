import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase=1; testCase<=T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[] {sx, sy, 0});
            int[][] time = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(time[i], Integer.MAX_VALUE);
            }
            time[sx][sy] = 0;

            while (!queue.isEmpty()) {
                int[] polled = queue.poll();
                int x = polled[0], y = polled[1];
                int t = polled[2];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                        continue;
                    }

                    if (map[nx][ny] == 0) {
                        if (t+1 < time[nx][ny]) {
                            time[nx][ny] = t+1;
                            queue.add(new int[] {nx, ny, time[nx][ny]});
                        }
                    } else if(map[nx][ny] == 2) {
                        int passSwirl = (t/3+1) * 3;
                        if (passSwirl < time[nx][ny]) {
                            time[nx][ny] = passSwirl;
                            queue.add(new int[] {nx, ny, time[nx][ny]});
                        }
                    }
                }
            }

            if (time[ex][ey] == Integer.MAX_VALUE) {
                System.out.println("#"+ testCase + " " + -1);
            } else {
                System.out.println("#"+ testCase + " " + time[ex][ey]);
            }
        }
    }
}