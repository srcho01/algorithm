import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int time;
    static int numBC;
    static int[] moveA;
    static int[] moveB;
    static int[][] charger;
    static int[][] dir = {
            {0, 0},
            {-1, 0},  // up
            {0, 1},  // right
            {1, 0},  // down
            {0, -1} // left
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time = Integer.parseInt(st.nextToken());
            numBC = Integer.parseInt(st.nextToken());

            moveA = new int[time];
            moveB = new int[time];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < time; j++) {
                moveA[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < time; j++) {
                moveB[j] = Integer.parseInt(st.nextToken());
            }

            charger = new int[numBC][4];
            for (int j = 0; j < numBC; j++) {
                st = new StringTokenizer(br.readLine());
                charger[j][1] = Integer.parseInt(st.nextToken());
                charger[j][0] = Integer.parseInt(st.nextToken());
                charger[j][2] = Integer.parseInt(st.nextToken());
                charger[j][3] = Integer.parseInt(st.nextToken());
            }

            System.out.println("#" + i + " " + solution());
        }
    }

    static int solution() {
        int ax = 1, ay = 1, bx = 10, by = 10;
        int sum = thisTimeCharge(ax, ay, bx, by);
        for (int i=1; i<=time; i++) {
            ax += dir[moveA[i-1]][0];
            ay += dir[moveA[i-1]][1];
            bx += dir[moveB[i-1]][0];
            by += dir[moveB[i-1]][1];
            sum += thisTimeCharge(ax, ay, bx, by);
        }

        return sum;
    }

    static int thisTimeCharge(int ax, int ay, int bx, int by) {
        List<Integer> chargableA = new ArrayList<>();
        List<Integer> chargableB = new ArrayList<>();
        for (int bc = 0; bc <numBC; bc++) {
            if (canCharge(ax, ay, bc)) {
                chargableA.add(bc);
            }
            if (canCharge(bx, by, bc)) {
                chargableB.add(bc);
            }
        }

        int max = 0;
        if (!chargableA.isEmpty() && !chargableB.isEmpty()) {
            for (int a: chargableA) {
                for (int b: chargableB) {
                    if (a == b) {
                        max = Math.max(max, charger[a][3]);
                    } else {
                        max = Math.max(max, charger[a][3] + charger[b][3]);
                    }
                }
            }
        } else if (!chargableA.isEmpty()) {
            for (int a: chargableA) {
                max = Math.max(max, charger[a][3]);
            }
        } else if(!chargableB.isEmpty()) {
            for (int b : chargableB) {
                max = Math.max(max, charger[b][3]);
            }
        }

        return max;
    }

    static boolean canCharge(int x, int y, int bcIdx) {
        int bcx = charger[bcIdx][0], bcy = charger[bcIdx][1];
        int coverage = charger[bcIdx][2];

        return Math.abs(x - bcx) + Math.abs(y - bcy) <= coverage;
    }

}
