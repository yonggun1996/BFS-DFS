import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MazeSearchBuffer {
	
	static String[] map;
	static int[][] distance;
	static Queue<ND> queue = new LinkedList<ND>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		map = new String[m];
		distance = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			map[i] = br.readLine();
		}
		
		ND nd = new ND(0,0);
		distance[0][0] = 1;
		queue.offer(nd);
		BFS(m,n);
		bw.write(distance[m - 1][n - 1]);
		bw.flush();
		bw.close();
	}
	
	public static void BFS(int m, int n) {
		while(!queue.isEmpty()) {
			ND nd = queue.poll();
			
			int x = nd.x;
			int y = nd.y;
			int num = distance[x][y];
			
			if(x == m - 1 && y == n - 1) {
				break;
			}
			
			if(x > 0 && distance[x - 1][y] == 0 && map[x - 1].charAt(y) == '1') {
				queue.offer(new ND(x - 1, y));
				distance[x - 1][y] = num + 1;
			}
			
			if(x < m - 1 && distance[x + 1][y] == 0 && map[x + 1].charAt(y) == '1') {
				queue.offer(new ND(x + 1, y));
				distance[x + 1][y] = num + 1;
			}
			
			if(y > 0 && distance[x][y - 1] == 0 && map[x].charAt(y - 1) == '1') {
				queue.offer(new ND(x, y - 1));
				distance[x][y - 1] = num + 1;
			}
			
			if(y < n - 1 && distance[x][y + 1] == 0 && map[x].charAt(y + 1) == '1') {
				queue.offer(new ND(x, y + 1));
				distance[x][y + 1] = num + 1;
			}

		}
		
	}

}

/*class ND{
	int x;
	int y;
	
	public ND(int x, int y) {
		this.x = x;
		this.y = y;
	}
}*/