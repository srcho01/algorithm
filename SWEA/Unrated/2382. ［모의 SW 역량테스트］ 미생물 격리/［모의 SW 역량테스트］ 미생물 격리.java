import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n, m, k;
    static List<int[]> micros;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            micros = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int num = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                micros.add(new int[]{x*n + y, num, d});
            }

            System.out.println("#" + tc + " " + go());
        }
    }

    static int go() {
        for (int i=0; i<m; i++) {
            Map<Integer, List<int[]>> afterMove = new HashMap<>();
            for (int[] micro: micros) {
                int x = micro[0] / n;
                int y = micro[0] % n;
                int num = micro[1];
                int d = micro[2];

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx == 0 || ny == 0 || nx == n-1 || ny == n-1) {
                    d = opposite(d);
                    num /= 2;
                }

                int pos = nx*n + ny;
                afterMove.putIfAbsent(pos, new ArrayList<>());
                afterMove.get(pos).add(new int[]{num, d});
            }

            micros.clear();
            for (Map.Entry<Integer, List<int[]>> entry: afterMove.entrySet()) {
                if (entry.getValue().size() == 1) {
                    int[] value = entry.getValue().get(0);
                    micros.add(new int[]{entry.getKey(), value[0], value[1]});
                } else {
                    int maxDirection = 1;
                    int maxGroup = -1;
                    int total = 0;
                    for (int[] value: entry.getValue()) {
                        total += value[0];
                        if (value[0] > maxGroup) {
                            maxGroup = value[0];
                            maxDirection = value[1];
                        }
                    }
                    micros.add(new int[]{entry.getKey(), total, maxDirection});
                }
            }
        }

        int ans = 0;
        for (int[] micro: micros) {
            ans += micro[1];
        }

        return ans;
    }

    static int opposite(int x) {
        switch (x) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }

        return 0;
    }

}
