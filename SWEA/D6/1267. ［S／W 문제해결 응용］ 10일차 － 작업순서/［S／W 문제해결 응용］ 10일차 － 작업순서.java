import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int testCase = 1; testCase <= 10; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numV =  Integer.parseInt(st.nextToken());
            int numE = Integer.parseInt(st.nextToken());

            List<Set<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= numV; i++) {
                graph.add(new HashSet<>());
            }

            int[] indegree = new int[numV+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < numE; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph.get(u).add(v);
                indegree[v]++;
            }

            System.out.print("#" + testCase + " ");
            Queue<Integer> queue = new LinkedList<>();
            for (int i=1; i <= numV; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            while(!queue.isEmpty()) {
                int curr = queue.poll();
                System.out.print(curr + " ");

                for (int next: graph.get(curr)) {
                    indegree[next]--;
                    if(indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            System.out.println();
        }
    }
}
