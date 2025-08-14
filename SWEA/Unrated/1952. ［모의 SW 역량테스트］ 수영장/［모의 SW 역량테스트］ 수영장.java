import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] cost = new int[4];
    static int[] plan = new int[12];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= t; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                cost[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                plan[i] =  Integer.parseInt(st.nextToken());
            }

            ans = cost[3];  // 1년 이용권
            solution(0, 0);
            System.out.println("#" + testCase + " " + ans);
        }

    }

    static void solution(int month, int total) {
        if (month >= 12) {
            ans = Math.min(ans, total);
            return;
        }

        if (total > ans) return;

        // 1일 이용권
        solution(month+1, total + cost[0] * plan[month]);

        // 1달 이용권
        solution(month+1, total + cost[1]);

        // 3달 이용권
        solution(month+3, total + cost[2]);
    }
}