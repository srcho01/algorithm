import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[9][9];
    static List<int[]> positionToFill = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getPositionToFill();
        backTracking(0, positionToFill.size());
    }

    static void getPositionToFill() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    positionToFill.add(new int[] {i, j});
                }
            }
        }
    }

    static void backTracking(int idx, int size) {
        if (idx == size) {
            // StringBuilder sb = new StringBuilder();
            for (int[] line: board) {
                for (int num: line) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }

            // System.out.println(sb);
            System.exit(0);
        }

        int x = positionToFill.get(idx)[0], y = positionToFill.get(idx)[1];
        for (int n=1; n<=9; n++) {
            board[x][y] = n;

            if (checkBox(x, y) && checkLR(x) && checkTD(y)) {
                backTracking(idx+1, size);
            }
            board[x][y] = 0;
        }
    }

    static boolean checkBox(int x, int y) {
        x = x / 3 * 3; y = y / 3 * 3;

        boolean[] visited = new boolean[10];
        for (int i = x; i < x+3; i++) {
            for (int j = y; j < y+3; j++) {
                int num = board[i][j];
                if (num == 0) {continue;}

                if (visited[num]) {
                    return false;
                } else {
                    visited[num] = true;
                }
            }
        }

        return true;
    }

    static boolean checkLR(int row) {
        boolean[] visited = new boolean[10];

        for (int j = 0; j < 9; j++) {
            int num = board[row][j];
            if (num == 0) {continue;}

            if (visited[num]) {
                return false;
            } else {
                visited[num] = true;
            }
        }

        return true;
    }

    static boolean checkTD(int col) {
        boolean[] visited = new boolean[10];

        for (int i = 0; i < 9; i++) {
            int num = board[i][col];
            if (num == 0) {continue;}

            if (visited[num]) {
                return false;
            } else {
                visited[num] = true;
            }
        }

        return true;
    }

}
