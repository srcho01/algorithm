import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int n, m;
    static List<int[]> houses;
    static int numHouses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            houses = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        numHouses++;
                        houses.add(new int[]{i, j});
                    }
                }
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        int ret = 1;
        int maxK = 0;
        for (int k = 1;; k++) {
            if (cost(k) > numHouses * m) {
                maxK = k-1;
                break;
            }
        }

        for (int k = 2; k <= maxK; k++) {
            int cost = cost(k);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int servicedHome = countHome(i, j, k);
                    if (servicedHome * m >= cost) {
                        ret = Math.max(ret, servicedHome);
                    }
                }
            }
        }

        return ret;
    }

    static int cost(int k) {
        return k*k + (k-1)*(k-1);
    }

    static int countHome(int sx, int sy, int k) {
        int cnt = 0;
        for (int[] house: houses) {
            if (dist(sx, sy, house[0], house[1]) < k) {
                cnt++;
            }
        }
        
        return cnt;
    }

    static int dist(int ax, int ay, int bx, int by) {
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }

}
