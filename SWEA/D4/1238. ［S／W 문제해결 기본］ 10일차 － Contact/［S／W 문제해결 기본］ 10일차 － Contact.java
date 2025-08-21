import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int testCase=1; testCase<=10; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            Map<Integer, Set<Integer>> graph = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i+=2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.putIfAbsent(from, new HashSet<>());
                graph.get(from).add(to);
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            int[] time = new int[101];
            Arrays.fill(time, -1);
            time[start] = 0;

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (int next: graph.getOrDefault(curr, new HashSet<>())) {
                    if (time[next] == -1) {
                        time[next] = time[curr] + 1;
                        queue.add(next);
                    }
                }
            }

            int max = -1;
            int ans =start;
            for (int i=1; i<=100; i++) {
                if (max <= time[i]) {
                    max = time[i];
                    ans = i;
                }
            }

            System.out.println("#"+ testCase + " " + ans);
        }
    }
}
