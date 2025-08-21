import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int T=1; T<=10; T++) {
            br.readLine();

            int[][] map = new int[100][100];
            int sx = 0, sy = 0;

            for (int i=0;i<100;i++){
                String line = br.readLine().trim();
                for(int j=0;j<100;j++){
                    map[i][j] = line.charAt(j) - '0';
                    if (map[i][j] == 2) {
                        sx = i;
                        sy = j;
                    }
                }
            }

            boolean ans = false;
            Queue<int[]> queue = new LinkedList<int[]>();
            queue.add(new int[] {sx, sy});
            map[sx][sy] = 1;
            while(!queue.isEmpty()){
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1];

                for (int i=0; i<4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= 100 || ny >= 100) {
                        continue;
                    }

                    if (map[nx][ny] == 3) {
                        ans = true;
                        break;
                    } else if (map[nx][ny] == 0) {
                        queue.add(new int[] {nx, ny});
                        map[nx][ny] = 1;
                    }
                }
            }

            System.out.println("#" + T + " " + (ans ? 1 : 0));
        }
    }
}
