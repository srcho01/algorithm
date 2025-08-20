import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == '1') {
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        int[][] dir = {
                {0, -1},
                {0, 1},
                {1, 0},
                {-1, 0},
        };

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        map[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] polled = queue.poll();
            int x = polled[0];
            int y = polled[1];

            if (x == n-1 && y == m-1) {
                System.out.println(map[n-1][m-1]);
                break;
            }

            for (int i=0; i<4; i++) {
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (map[nx][ny] == -1) {
                    map[nx][ny] = map[x][y] + 1;
                    queue.add(new int[] {nx, ny});
                }
            }
        }
    }
}
