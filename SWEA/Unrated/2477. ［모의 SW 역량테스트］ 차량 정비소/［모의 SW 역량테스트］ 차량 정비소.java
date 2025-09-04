import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    static int n, m, k, a, b;
    static int[] reception;
    static int[] repair;
    static int[] customer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;

            reception = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                reception[i] = Integer.parseInt(st.nextToken());
            }

            repair = new int[m];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                repair[i] = Integer.parseInt(st.nextToken());
            }

            customer = new int[k];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                customer[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println("#" + tc + " " + solve());
        }
    }

    static int solve() {
        Arrays.sort(customer);

        PriorityQueue<int[]> pq = passReception();
        return getRepair(pq);
    }

    static PriorityQueue<int[]> passReception() {
        // [리셉션에서 나온 시간, 이용했던 접수 창구 번호, 고객 번호]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[0]).
                        thenComparing((int[] a) -> a[1])
        );

        int[][] endTime = new int[n][2];  // [끝날 시간, 고객 번호]
        for (int person = 0; person < k; person++) {
            int arrivalTime = customer[person];

            // 집어넣을 곳 찾기
            int idx = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (endTime[i][0] == 0 || endTime[i][0] <= arrivalTime) {
                    idx = i;
                    break;
                }

                if (endTime[i][0] < min) {
                    idx = i;
                    min = endTime[i][0];
                }
            }

            // 이전 사람 빼기
            if (endTime[idx][0] != 0) {
                pq.offer(new int[]{endTime[idx][0], idx, endTime[idx][1]});
            }

            // 이번 사람 집어넣기
            endTime[idx][1] = person;
            if (arrivalTime < endTime[idx][0]) {  // 대기
                endTime[idx][0] += reception[idx];
            } else {
                endTime[idx][0] = arrivalTime + reception[idx];
            }

        }

        // 남은 사람 전부 빼주기
        for (int i = 0; i < n; i++) {
            if (endTime[i][0] != 0) {
                pq.offer(new int[]{endTime[i][0], i, endTime[i][1]});
            }
        }

        return pq;
    }

    static int getRepair(PriorityQueue<int[]> pq) {
        // pq : [리셉션에서 나온 시간, 이용했던 접수 창구 번호, 고객 번호]
        int[][] endTime = new int[m][3];  // [끝날 시간, 이용했던 접수 창구 번호, 고객 번호]

        int ans = 0;

        while (!pq.isEmpty()) {
            int[] polled = pq.poll();
            int arrivalTime = polled[0], usedReception = polled[1], person = polled[2];

            // 집어넣을 곳 찾기
            int idx = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < m; i++) {
                if (endTime[i][0] == 0 || endTime[i][0] <= arrivalTime) {
                    idx = i;
                    break;
                }

                if (endTime[i][0] < min) {
                    idx = i;
                    min = endTime[i][0];
                }
            }

            // 이전 사람 빼기
            if (endTime[idx][0] != 0) {
                if (endTime[idx][1] == a && idx == b) {
                    ans += endTime[idx][2] + 1;
                }
            }

            // 이번 사람 집어넣기
            if (arrivalTime < endTime[idx][0]) {  // 대기
                endTime[idx][0] += repair[idx];
            } else {
                endTime[idx][0] = arrivalTime + repair[idx];
            }
            endTime[idx][1] = usedReception;
            endTime[idx][2] = person;
        }

        // 남은 사람 전부 빼주기
        for (int i = 0; i < m; i++) {
            if (endTime[i][0] != 0) {
                if (endTime[i][1] == a && i == b) {
                    ans += endTime[i][2] + 1;
                }
            }
        }

        return (ans != 0) ? ans : -1;
    }

}