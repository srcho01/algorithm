import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ");

            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (cmd == 0) {
                    uf.union(a, b);
                } else {
                    sb.append(uf.isConnected(a, b));
                }
            }

            System.out.println(sb);
        }
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int size) {
        parent = new int[size+1];
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

    public void union(int u, int v) {
        int uRoot = find(u);
        int vRoot = find(v);

        if (uRoot <=  vRoot) {
            parent[vRoot] = uRoot;
        } else {
            parent[uRoot] = vRoot;
        }
    }

    public int isConnected(int u, int v) {
        return (find(u) == find(v)) ? 1 : 0;
    }
}
