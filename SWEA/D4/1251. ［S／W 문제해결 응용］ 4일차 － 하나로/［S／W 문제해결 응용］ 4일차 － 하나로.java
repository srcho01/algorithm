import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static int n;
    static double e;
    static int[][] islands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=T; tc++) {
            n = Integer.parseInt(br.readLine());

            islands = new int[n][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                islands[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                islands[i][1] = Integer.parseInt(st.nextToken());
            }

            e = Double.parseDouble(br.readLine());

            System.out.println("#" + tc + " " + go());
        }
    }

    static long go() {
        List<Node> list = edgeList();
        UnionFind uf = new UnionFind(n);
        double total = 0;
        int cnt = 0;
        int idx = 0;
        while (cnt < n-1) {
            Node line = list.get(idx++);
            if (uf.union(line.x, line.y)) {
                total += line.l * line.l;
                cnt++;
            }
        }

        return Math.round(total * e);
    }

    static List<Node> edgeList() {
        List<Node> dist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                double l = Math.sqrt(Math.pow(islands[i][0] - islands[j][0], 2) +  Math.pow(islands[i][1] - islands[j][1], 2));
                dist.add(new Node(i, j, l));
            }
        }

        dist.sort(Comparator.comparing(x -> x.l));
        return dist;
    }
}

class Node {
    int x, y;
    double l;

    public Node(int x, int y, double l) {
        this.x = x;
        this.y = y;
        this.l = l;
    }
}

class UnionFind {
    int[] parent;

    public UnionFind(int size) {
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
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
