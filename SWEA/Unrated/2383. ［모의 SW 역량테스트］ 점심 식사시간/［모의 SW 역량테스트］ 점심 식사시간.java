import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n;
    static List<int[]> people;
    static List<int[]> stair;
    static int[] use;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());

            people = new ArrayList<>();
            stair = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int x = Integer.parseInt(st.nextToken());
                    if (x == 1) {
                        people.add(new int[]{i, j});
                    } else if (x != 0) {
                        stair.add(new int[]{i, j, x});
                    }
                }
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        use = new int[people.size()];
        ans = Integer.MAX_VALUE;

        bt(0);
        return ans;
    }

    static void bt(int idx) {
        if (idx == people.size()) {
            ans = Math.min(ans, calTime());
            return;
        }

        use[idx] = 0;
        bt(idx + 1);

        use[idx] = 1;
        bt(idx + 1);
    }

    static int calTime() {
        List<Integer> arrivalTime1 = new ArrayList<>();
        List<Integer> arrivalTime2 = new ArrayList<>();

        for (int person = 0; person < people.size(); person++) {
            if (use[person] == 0) {
                arrivalTime1.add(dist(people.get(person), stair.get(0)));
            } else {
                arrivalTime2.add(dist(people.get(person), stair.get(1)));
            }
        }

        arrivalTime1.sort(Comparator.naturalOrder());
        arrivalTime2.sort(Comparator.naturalOrder());
        return Math.max(calEndTime(arrivalTime1, 0), calEndTime(arrivalTime2, 1));
    }

    static int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    static int calEndTime(List<Integer> arrivalTime, int s) {
        int endTime = 0;
        int stairTime = stair.get(s)[2];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int arrive: arrivalTime) {
            if (queue.size() < 3) {
                queue.offer(arrive + 1 + stairTime);
            } else {
                int getOut = queue.poll();
                int waiting = Math.max(0, getOut - (arrive + 1));
                queue.offer(arrive + 1 + waiting + stairTime);
            }
        }
        while (!queue.isEmpty()) {
            endTime = Math.max(endTime, queue.poll());
        }

        return endTime;
    }

}
