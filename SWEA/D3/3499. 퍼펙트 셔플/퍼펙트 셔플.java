import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static String[] deck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.  parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());

            deck = new String[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                deck[i] = st.nextToken();
            }

            StringBuilder sb = solve();
            System.out.println("#" + tc + " " + sb.toString());
        }
    }

    static StringBuilder solve() {
        int first = 0, second = (n % 2 == 0) ? n / 2 : n / 2 + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n/2; i++) {
            sb.append(deck[first+i]).append(" ");
            sb.append(deck[second+i]).append(" ");
        }

        if (n % 2 == 1) {
            sb.append(deck[first+n/2]);
        }

        return sb;
    }

}