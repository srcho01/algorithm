import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(b).add(a);
        }

        List<BitSet> hacked = new ArrayList<>();
        for (int i=0; i<=n; i++) {
            hacked.add(new BitSet());
        }

        int max = -1;
        for (int start=1; start<=n; start++) {
            Queue<Integer> queue = new LinkedList<>();
            BitSet thisSet = hacked.get(start);

            queue.add(start);
            thisSet.set(start);

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (int next: list.get(curr)) {
                    if (!thisSet.get(next)) {
                        if (next < start) {
                            thisSet.or(hacked.get(next));
                        } else {
                            thisSet.set(next);
                            queue.add(next);
                        }
                    }
                }
            }

            max = Math.max(max, thisSet.cardinality());
        }

        for (int i = 1; i <= n; i++) {
            if (hacked.get(i).cardinality() == max) {
                System.out.print(i + " ");
            }
        }
    }
}
