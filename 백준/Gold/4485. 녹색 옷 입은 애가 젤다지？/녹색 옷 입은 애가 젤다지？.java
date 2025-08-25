import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 1;
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("Problem " + tc++ + ": " + solution(n, map));
        }
    }

    private static int solution(int n, int[][] map) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{map[0][0], 0, 0});
        int[][] dijkstra = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dijkstra[i], Integer.MAX_VALUE);
        }
        dijkstra[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int lost = polled[0];
            int x = polled[1];
            int y = polled[2];

            if (x == n-1 && y == n-1) {
                return dijkstra[n-1][n-1];
            }

            for (int i=0; i<4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (lost + map[nx][ny] < dijkstra[nx][ny]) {
                    dijkstra[nx][ny] = lost + map[nx][ny];
                    pq.offer(new int[]{dijkstra[nx][ny], nx, ny});
                }
            }
        }

        return 0;
    }
}
