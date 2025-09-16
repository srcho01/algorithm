import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        System.out.println(solve());
    }

    static long solve() {
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        return ((fact[n] * pow(fact[k], MOD-2)) % MOD * pow(fact[n-k], MOD-2)) % MOD;
    }

    static long pow(long a, long b) {
        return BigInteger.valueOf(a).
                modPow(BigInteger.valueOf(b), BigInteger.valueOf(MOD)).longValue() % MOD;
    }
}