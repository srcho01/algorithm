import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    // 요리사

    static int n;
    static int[][] synergy;
    static boolean[] isFoodA;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            n = Integer.parseInt(br.readLine());
            isFoodA = new boolean[n];
            synergy = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;
            backTracking(0, 0);
            System.out.println("#" + testCase + " " + ans);
        }
    }

    static void backTracking(int idx, int cnt) {
        if (cnt >= n / 2 || idx == n) {
            ans = Math.min(ans, calDiff());
            return;
        }

        // 선택하는 경우
        isFoodA[idx] = true;
        backTracking(idx + 1, cnt + 1);

        // 선택하지 않는 경우
        isFoodA[idx] = false;
        backTracking(idx + 1, cnt);
    }

    static int calDiff() {
        int trueSum = 0;
        int falseSum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isFoodA[i] && isFoodA[j]) {
                    trueSum += synergy[i][j] + synergy[j][i];
                } else if (!isFoodA[i] && !isFoodA[j]) {
                    falseSum += synergy[i][j] + synergy[j][i];
                }
            }
        }

        return Math.abs(trueSum - falseSum);
    }
}
