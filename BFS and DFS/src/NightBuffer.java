import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NightBuffer {

	static Queue<NightBufferNode> queue = new LinkedList<NightBufferNode>();
	static int[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		NightBuffer n = new NightBuffer();

		int testcase = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < testcase; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			visit = new int[size][size];

			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());

			int finish_x = Integer.parseInt(st.nextToken());
			int finish_y = Integer.parseInt(st.nextToken());
			queue.offer(new NightBufferNode(start_x, start_y));
			visit[start_y][start_x] = 0;
			n.BFS(finish_x, finish_y, size);
			queue.clear();
		}
	}

	public void BFS(int finish_x, int finish_y, int size) {
		while (!queue.isEmpty()) {
			NightBufferNode nn = queue.poll();

			int x = nn.x;
			int y = nn.y;
			int count = visit[y][x];
			//System.out.println("x : " + x + " y: " + y);

			if (x == finish_x && y == finish_y) {
				System.out.println(count);
				break;
			}

			if (y - 1 >= 0 && x - 2 >= 0 && visit[y - 1][x - 2] == 0) {
				queue.offer(new NightBufferNode(x - 2, y - 1));
				visit[y - 1][x - 2] = count + 1;
			}

			if (y - 2 >= 0 && x - 1 >= 0 && visit[y - 2][x - 1] == 0) {
				queue.offer(new NightBufferNode(x - 1, y - 2));
				visit[y - 2][x - 1] = count + 1;
			}

			if (y - 2 >= 0 && x + 1 < size && visit[y - 2][x + 1] == 0) {
				queue.offer(new NightBufferNode(x + 1, y - 2));
				visit[y - 2][x + 1] = count + 1;
			}

			if (y - 1 >= 0 && x + 2 < size && visit[y - 1][x + 2] == 0) {
				queue.offer(new NightBufferNode(x + 2, y - 1));
				visit[y - 1][x + 2] = count + 1;
			}

			if (y + 1 < size && x + 2 < size && visit[y + 1][x + 2] == 0) {
				queue.offer(new NightBufferNode(x + 2, y + 1));
				visit[y + 1][x + 2] = count + 1;
			}

			if (y + 2 < size && x + 1 < size && visit[y + 2][x + 1] == 0) {
				queue.offer(new NightBufferNode(x + 1, y + 2));
				visit[y + 2][x + 1] = count + 1;
			}

			if (y + 2 < size && x - 1 >= 0 && visit[y + 2][x - 1] == 0) {
				queue.offer(new NightBufferNode(x - 1, y + 2));
				visit[y + 2][x - 1] = count + 1;
			}

			if (y + 1 < size && x - 2 >= 0 && visit[y + 1][x - 2] == 0) {
				queue.offer(new NightBufferNode(x - 2, y + 1));
				visit[y + 1][x - 2] = count + 1;
			}

		}
	}

}

class NightBufferNode {
	int x;// 가로
	int y;// 세로

	public NightBufferNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
