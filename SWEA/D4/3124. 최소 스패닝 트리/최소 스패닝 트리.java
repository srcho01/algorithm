import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int v, e;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for (int i=0; i<e; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                list.add(new int[] {x, y, z});
            }
            list.sort(Comparator.comparingInt(x -> x[2]));

            System.out.println("#" + tc + " " + go());
        }
    }

    static long go() {
        UnionFind uf = new UnionFind(v);
        int cnt = 0;
        int idx = 0;

        long total = 0;
        while (cnt < v-1) {
            int[] edge = list.get(idx++);
            if (uf.union(edge[0], edge[1])) {
                total += edge[2];
                cnt++;
            }
        }

        return total;
    }

    static class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            this.parent = new int[size+1];
            for (int i = 0; i <= size; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }

            return x;
        }

        public boolean union(int u, int v) {
            int uRoot = find(u);
            int vRoot = find(v);

            if (uRoot == vRoot) {
                return false;
            } else {
                if (uRoot < vRoot) {
                    parent[vRoot] = uRoot;
                } else {
                    parent[uRoot] = vRoot;
                }
            }

            return true;
        }
    }
}
