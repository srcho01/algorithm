import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            long n = Long.parseLong(br.readLine());
            long cnt = 0;

            while (n != 2) {
                long root = (long) Math.ceil(Math.sqrt(n));
                cnt += root * root - n + 1;
                n = root;
            }

            System.out.println("#" + testCase + " " + cnt);
        }
    }
}
