import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;  // n개의 물건
    static int W;  // 배낭의 용량
    static int[] w;  // 물건 i의 무게 w[i]
    static int[] v;  // 물건 i의 가치 v[i]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        w = new int[n+1];
        v = new int[n+1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[][] K = new int[n+1][W+1];  // K[i][j] : i번째 물건까지 봤을 때, 가방 용량이 j일 때 가치 최댓값
        for (int i = 0; i <= n; i++) {  // 가방 용량이 0이면 가치 0
            K[i][0] = 0;
        }
        for (int i = 0; i <= W; i++) {  // 넣을 물건이 0개면 가치 0
            K[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                if (w[i] > j) {  // 현재 가방 용량보다 i번째 물건 무게가 더 나간다면
                    K[i][j] = K[i-1][j];  // 넣을 수 없음
                } else {
                    // 현재 물건을 넣는 경우와 넣지 않는 경우 중 뭐가 더 최대인지
                    K[i][j] = Math.max(K[i-1][j-w[i]] + v[i], K[i-1][j]);
                }
            }
        }

        System.out.println(K[n][W]);
    }
}
