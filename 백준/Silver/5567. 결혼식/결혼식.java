import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            list.get(u).add(v);
            list.get(v).add(u);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        int ans = 0;

        visited[1] = true;
        for (int u: list.get(1)) {
            queue.add(u);
            visited[u] = true;
            ans++;
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next: list.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

}
