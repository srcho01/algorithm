import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int k, n, m;
    static int[][] initMap;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            initMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    initMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;
            bt(deepcopy(initMap), 0);
            System.out.println("#" + tc + " " + ans);
        }
    }

    static void bt(int[][] map, int depth) {
        if (depth == k) {
            int left = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0) {
                        left += 1;
                    }
                }
            }

            ans = Math.min(ans, left);
            return;
        }

        for (int drop = 0; drop < m; drop++) {
            // 제거 + 내리기
            int[][] playMap = deepcopy(map);
            play(playMap, drop);
            bt(playMap, depth + 1);
        }
    }

    static void play(int[][] map, int drop) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        boolean[][] crash = new boolean[n][m];

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < n; i++) {
            if (map[i][drop] != 0) {
                q.offer(new int[]{i, drop});
                visited[i][drop] = true;
                crash[i][drop] = true;
                break;
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];

            crash[x][y] = true;
            for (int power=1; power<map[x][y]; power++) {
                for (int i=0; i<4; i++) {
                    int nx = x + dx[i] * power, ny = y + dy[i] * power;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        crash[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (crash[i][j]) {
                    map[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            int[] line = new int[n];
            for (int i = 0; i < n; i++) {
                line[i] = map[i][j];
            }

            int idx = n-1;
            for (int i=n-1; i>=0; i--) {
                if (line[i] != 0) {
                    map[idx--][j] = line[i];
                }
            }

            for (int i=idx; i>=0; i--) {
                map[i][j] = 0;
            }

        }

    }

    static int[][] deepcopy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        return copy;
    }

}
