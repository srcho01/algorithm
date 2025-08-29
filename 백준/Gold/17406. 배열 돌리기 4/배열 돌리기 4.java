import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static int[][] inputArr;
    static int[][] command;

    static int ans;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        inputArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        command = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                command[i][j] = Integer.parseInt(st.nextToken())-1;
            }
            command[i][2] = Integer.parseInt(st.nextToken());
        }

        ans = Integer.MAX_VALUE;
        visited = new boolean[k];
        bt(deepcopy(inputArr), 0);
        System.out.println(ans);
    }

    static void bt(int[][] arr, int depth) {
        if (depth == k) {
            for (int[] line: arr) {
                int total = 0;
                for (int num: line) {
                    total += num;
                }
                ans = Math.min(ans, total);
            }

            return;
        }

        for (int i = 0; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int[][] newArr = deepcopy(arr);
                rotate(newArr, i);
                bt(newArr, depth+1);
                visited[i] = false;
            }
        }
    }

    static int[][] deepcopy(int[][] A) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, copy[i], 0, m);
        }

        return copy;
    }

    static void rotate(int[][] arr, int idx) {
        int r = command[idx][0], c = command[idx][1], s = command[idx][2];

        for (int layer=1; layer<=s; layer++) {
            int top = r-layer, bottom = r+layer;
            int left = c-layer, right = c+layer;

            int save = arr[top][left];
            for (int j=left+1; j<=right; j++) {
                int tmp = arr[top][j];
                arr[top][j] = save;
                save = tmp;
            }

            for (int i=top+1; i<=bottom; i++) {
                int tmp = arr[i][right];
                arr[i][right] = save;
                save = tmp;
            }

            for (int j=right-1; j>=left; j--) {
                int tmp = arr[bottom][j];
                arr[bottom][j] = save;
                save = tmp;
            }

            for (int i=bottom-1; i>=top; i--) {
                int tmp = arr[i][left];
                arr[i][left] = save;
                save = tmp;
            }

        }

    }

}
