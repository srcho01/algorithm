import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        paper = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] ans = daq(0, 0, n);
        System.out.println(ans[0] + "\n" + ans[1]);
    }

    static int[] daq(int x, int y, int s) {
        int c = color(x, y, s);
        int zero = 0, one = 0;

        if (c != -1) {
            if (c == 0) {
                zero++;
            } else {
                one++;
            }
        } else {
            int half = s/2;

            int[] ret = daq(x, y, half);
            zero +=  ret[0];
            one += ret[1];

            ret = daq(x, y+half, half);
            zero +=  ret[0];
            one += ret[1];

            ret = daq(x+half, y, half);
            zero +=  ret[0];
            one += ret[1];

            ret = daq(x+half, y+half, half);
            zero +=  ret[0];
            one += ret[1];
        }

        return new int[]{zero, one};
    }

    static int color(int x, int y, int s) {
        int c = paper[x][y];
        for (int i=x; i<x+s; i++) {
            for (int j=y; j<y+s; j++) {
                if (c != paper[i][j]) {
                    return -1;
                }
            }
        }

        return c;
    }

}
