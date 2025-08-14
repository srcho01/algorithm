import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= t; testCase++) {
            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + testCase + " " + solution());
        }
    }

    static int solution() {
        if (checkBox() && checkLR() && checkTD()) {
            return 1;
        }
        return 0;
    }

    static boolean checkBox() {
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                boolean[] visited = new boolean[10];
                for (int di = 0; di < 3; di++) {
                    for (int dj = 0; dj < 3; dj++) {
                        int num = board[i+di][i+dj];
                        if (visited[num]) {
                            return false;
                        } else {
                            visited[num] = true;
                        }
                    }
                }
            }
        }

        return true;
    }

    static boolean checkLR() {
        for (int i = 0; i < 9; i++) {
            boolean[] visited = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int num = board[i][j];
                if (visited[num]) {
                    return false;
                } else {
                    visited[num] = true;
                }
            }
        }

        return true;
    }

    static boolean checkTD() {
        for (int j = 0; j < 9; j++) {
            boolean[] visited = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int num = board[i][j];
                if (visited[num]) {
                    return false;
                } else {
                    visited[num] = true;
                }
            }
        }

        return true;
    }

}
