import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Night {

	static Queue<NightNode> queue = new LinkedList<NightNode>();
	static int[][] visit;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Night n = new Night();

		int testcase = in.nextInt();

		for (int i = 0; i < testcase; i++) {
			int size = in.nextInt();
			visit = new int[size][size];

			int start_x = in.nextInt();
			int start_y = in.nextInt();

			int finish_x = in.nextInt();
			int finish_y = in.nextInt();
			queue.offer(new NightNode(start_x, start_y));
			visit[start_y][start_x] = 0;
			n.BFS(finish_x, finish_y, size);
			queue.clear();
		}
	}

	public void BFS(int finish_x, int finish_y, int size) {
		while (!queue.isEmpty()) {
			NightNode nn = queue.poll();

			int x = nn.x;
			int y = nn.y;
			int count = visit[y][x];
			//System.out.println("x : " + x + " y: " + y);

			if (x == finish_x && y == finish_y) {
				System.out.println(count);
				break;
			}

			if (y - 1 >= 0 && x - 2 >= 0 && visit[y - 1][x - 2] == 0) {
				queue.offer(new NightNode(x - 2, y - 1));
				visit[y - 1][x - 2] = count + 1;
			}

			if (y - 2 >= 0 && x - 1 >= 0 && visit[y - 2][x - 1] == 0) {
				queue.offer(new NightNode(x - 1, y - 2));
				visit[y - 2][x - 1] = count + 1;
			}

			if (y - 2 >= 0 && x + 1 < size && visit[y - 2][x + 1] == 0) {
				queue.offer(new NightNode(x + 1, y - 2));
				visit[y - 2][x + 1] = count + 1;
			}

			if (y - 1 >= 0 && x + 2 < size && visit[y - 1][x + 2] == 0) {
				queue.offer(new NightNode(x + 2, y - 1));
				visit[y - 1][x + 2] = count + 1;
			}

			if (y + 1 < size && x + 2 < size && visit[y + 1][x + 2] == 0) {
				queue.offer(new NightNode(x + 2, y + 1));
				visit[y + 1][x + 2] = count + 1;
			}

			if (y + 2 < size && x + 1 < size && visit[y + 2][x + 1] == 0) {
				queue.offer(new NightNode(x + 1, y + 2));
				visit[y + 2][x + 1] = count + 1;
			}

			if (y + 2 < size && x - 1 >= 0 && visit[y + 2][x - 1] == 0) {
				queue.offer(new NightNode(x - 1, y + 2));
				visit[y + 2][x - 1] = count + 1;
			}

			if (y + 1 < size && x - 2 >= 0 && visit[y + 1][x - 2] == 0) {
				queue.offer(new NightNode(x - 2, y + 1));
				visit[y + 1][x - 2] = count + 1;
			}

		}
	}

}

class NightNode {
	int x;// 가로
	int y;// 세로

	public NightNode(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
