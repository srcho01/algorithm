import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Set<Integer> coins = new HashSet<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[k+1];
        Arrays.fill(dp, (int)1e8);
        dp[0] = 0;
        for (int price = 1; price <= k; price++) {
            for (int coin: coins) {
                if (coin <= price) {
                    dp[price] = Math.min(dp[price], dp[price-coin] + 1);
                }
            }
        }

        System.out.println((dp[k] == (int)1e8) ? -1 : dp[k]);
    }
}
