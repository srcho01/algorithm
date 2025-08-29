import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] paper;

    static int white = 0;
    static int blue = 0;

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

        dac(0, 0, n);
        System.out.println(white + "\n" + blue);
    }

    static void dac(int x, int y, int s) {
        int c = color(x, y, s);

        if (c == 0) {
            white++;
        } else if (c == 1) {
            blue++;
        } else {
            s /= 2;
            dac(x, y, s);
            dac(x, y+s, s);
            dac(x+s, y, s);
            dac(x+s, y+s, s);
        }
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
