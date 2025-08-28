import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int k;
    static int[][] magnet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            k = Integer.parseInt(br.readLine());
            magnet = new int[4][8];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i=0; i<k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                rotate(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
            }

            int score = 0;
            for (int i=0; i<4; i++) {
                if (magnet[i][0] == 1) {
                    score += (int) Math.pow(2, i);
                }
            }

            System.out.println("#" + tc + " " + score);
        }
    }

    static void rotate(int idx, int d) {
        // 1 : 시계 방향, -1 : 반시계 방향
        int[] rotDir = new int[4];

        // 기준
        rotDir[idx] = d;

        // 기준 왼쪽
        for (int i=idx-1; i>=0; i--) {
            if (rotDir[i+1] == 0) {
                break;
            }

            if (magnet[i][2] != magnet[i+1][6]) {
                rotDir[i] = rotDir[i+1] * (-1);
            } else {
                rotDir[i] = 0;
            }
        }

        // 기준 오른쪽
        for (int i=idx+1; i<4; i++) {
            if (rotDir[i-1] == 0) {
                break;
            }

            if (magnet[i-1][2] != magnet[i][6]) {
                rotDir[i] = rotDir[i-1] * (-1);
            } else {
                rotDir[i] = 0;
            }
        }

        for (int i=0; i<4; i++) {
            if (rotDir[i] == 1) {
                clockwise(magnet[i]);
            } else if (rotDir[i] == -1) {
                counterClockwise(magnet[i]);
            }
        }

    }

    static void clockwise(int[] m) {
        int last = m[7];
        for (int i=6; i>=0; i--) {
            m[i+1] = m[i];
        }
        m[0] = last;
    }

    static void counterClockwise(int[] m) {
        int first = m[0];
        for (int i=1; i<8; i++) {
            m[i-1] = m[i];
        }
        m[7] = first;
    }

}
