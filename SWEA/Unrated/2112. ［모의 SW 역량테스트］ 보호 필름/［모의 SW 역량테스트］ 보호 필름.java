import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int d, w, k;
    static int[][] film;

    static int[] medicine;
    static boolean finish;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            film = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        medicine = new int[d];
        Arrays.fill(medicine, -1);
        finish = false;

        for (int n=0; n<=d; n++) {
            bt(0, 0, n);
            if (finish) {
                return n;
            }
        }

        return d;
    }

    static void bt(int idx, int cnt, int target) {
        if (cnt == target) {
            if (test()) {
                finish = true;
            }
            return;
        }

        for (int i=idx; i<d; i++) {
            for (int c=0; c<=1; c++) {
                medicine[i] = c;
                bt(i+1, cnt+1, target);
                medicine[i] = -1;

                if (finish) return;
            }
        }
    }

    static boolean test() {
        int[][] apply = new int[d][w];
        for (int i = 0; i < d; i++) {
            int curr = medicine[i];
            if (curr != -1) {
                Arrays.fill(apply[i], curr);
            } else {
                System.arraycopy(film[i], 0, apply[i], 0, w);
            }
        }

        for (int j = 0; j < w; j++) {
            int maxSequence = -1;
            int sequence = 0;
            int type = apply[0][j];
            for (int i = 0; i < d; i++) {
                if (apply[i][j] == type) {
                    sequence++;
                } else {
                    maxSequence = Math.max(maxSequence, sequence);
                    sequence = 1;
                    type = apply[i][j];
                }
            }

            maxSequence = Math.max(maxSequence, sequence);
            if (maxSequence < k) {
                return false;
            }
        }

        return true;
    }

}