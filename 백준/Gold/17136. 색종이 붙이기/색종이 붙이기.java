import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[10][10];
    static int[] numPaper = {0, 5, 5, 5, 5, 5};
    static int ans = 30;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(ans == 30 ? -1 : ans);
    }

    static void dfs(int x, int y, int cnt) {
        if (x >= 9 && y > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (cnt >= ans) {
            return;
        }

        if (y > 9) {
            dfs(x + 1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            for (int paper = 5; paper >= 1; paper--) {
                if (numPaper[paper] > 0 && canAttach(x, y, paper)) {
                    attach(x, y, paper, 0);
                    numPaper[paper]--;
                    dfs(x,y + 1, cnt + 1);
                    numPaper[paper]++;
                    attach(x, y, paper, 1);
                }
            }
        } else {
            dfs(x, y + 1, cnt);
        }
    }

    static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) {
            return false;
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    static void attach(int x, int y, int size, int status) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = status;
            }
        }
    }

}
