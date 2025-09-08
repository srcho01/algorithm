import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int n, k;
    static int[][] things;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            things = new int[n+1][2];
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                things[i][0] = Integer.parseInt(st.nextToken());
                things[i][1] = Integer.parseInt(st.nextToken());
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        int[][] dp = new int[n+1][k+1];

        for (int thing = 1; thing <= n; thing++) {
            for (int bag = 1; bag <= k; bag++) {
                int weight = things[thing][0];
                int cost = things[thing][1];

                dp[thing][bag] = dp[thing-1][bag];
                if (bag - weight >= 0) {
                    dp[thing][bag] = Math.max(dp[thing][bag], dp[thing-1][bag-weight] + cost);
                }
            }
        }

        return dp[n][k];
    }
}