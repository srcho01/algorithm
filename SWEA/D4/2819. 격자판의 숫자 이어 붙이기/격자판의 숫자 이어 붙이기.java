import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

    static int[][] map;

    static Set<String> num;
    static Stack<Integer> stack;
    static int[][] direction = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            map = new int[4][4];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            num = new HashSet<>();

            solve();
            System.out.println("#" + tc + " " + num.size());
        }
    }

    static void solve() {
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                stack = new Stack<>();
                dfs(0, i, j);
            }
        }
    }

    static void dfs(int depth, int x, int y) {
        if (depth == 7) {
            num.add(stack.toString());
            return;
        }

        for (int[] d: direction) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                continue;
            }

            stack.push(map[nx][ny]);
            dfs(depth + 1, nx, ny);
            stack.pop();
        }
    }

}