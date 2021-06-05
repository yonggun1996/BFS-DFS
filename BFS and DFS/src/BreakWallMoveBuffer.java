import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakWallMoveBuffer {
	
	static String[] map;
	static int[][] visit;
	static Queue<BWMBufferNode> queue = new LinkedList<BWMBufferNode>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BreakWallMoveBuffer b = new BreakWallMoveBuffer();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		map = new String[height];
		visit = new int[height][width];//���� ���� Ƚ���� ��´�
		for(int i = 0; i < height; i++) {
			map[i] = br.readLine();
			for(int j = 0; j < width; j++) {
				visit[i][j] = Integer.MAX_VALUE;
			}
		}
		
		BWMBufferNode node = new BWMBufferNode(0, 0, 0, 1);
		queue.offer(node);
		bw.write(String.valueOf(b.BFS()));
		bw.flush();
		bw.close();
		
	}
	
	public int BFS() {
		while(!queue.isEmpty()) {
			BWMBufferNode node = queue.poll();
			
			int y = node.y;
			int x = node.x;
			int wall = node.wall;//���� ���� Ƚ��
			int load = node.load;//���� ����� �Ÿ�
			
			if(y == map.length - 1 && x == map[0].length() - 1) {//��ǥ�������� �Դٸ� ����� �Ÿ��� ��ȯ
				return load;
			}
			
			//��
			if(y > 0) {
				if(visit[y - 1][x] > wall) {//���� ���� �Ⱥμ� ��찡 �ִٸ� �ٽ� ����� �Ѵ�
					if(map[y - 1].charAt(x) == '1') {//���� �����ٸ�
						if(wall == 0) {//���� �ν����� ���ٸ�
							queue.offer(new BWMBufferNode(y - 1, x, wall + 1, load + 1));//���� �ν� Ƚ���� �߰�
							visit[y - 1][x] = wall + 1;
						}
					}else {//���� ������ �ʾҴٸ�
						queue.offer(new BWMBufferNode(y - 1, x, wall, load + 1));
						visit[y - 1][x] = wall;
					}
				}
				
			}
			
			//�Ʒ�
			if(y < map.length - 1) {
				if(visit[y + 1][x] > wall) {//���� ���� �Ⱥμ� ��찡 �ִٸ� �ٽ� ����� �Ѵ�
					if(map[y + 1].charAt(x) == '1') {
						if(wall == 0) {//���� �ν����� ���ٸ�
							queue.offer(new BWMBufferNode(y + 1, x, wall + 1, load + 1));//���� �ν� Ƚ���� �߰�
							visit[y + 1][x] = wall + 1;
						}
					}else {
						queue.offer(new BWMBufferNode(y + 1, x, wall, load + 1));
						visit[y + 1][x] = wall;
					}
				}
			}
			
			//����
			if(x > 0) {
				if(visit[y][x - 1] > wall) {//���� ���� �Ⱥμ� ��찡 �ִٸ� �ٽ� ����� �Ѵ�
					if(map[y].charAt(x - 1) == '1') {
						if(wall == 0) {//���� �ν����� ���ٸ�
							queue.offer(new BWMBufferNode(y, x - 1, wall + 1, load + 1));//���� �ν� Ƚ���� �߰�
							visit[y][x - 1] = wall + 1;
						}
					}else {
						queue.offer(new BWMBufferNode(y, x - 1, wall, load + 1));
						visit[y][x - 1] = wall;
					}
				}
			}
			
			//������
			if(x < map[0].length() - 1) {
				if(visit[y][x + 1] > wall) {//���� ���� �Ⱥμ� ��찡 �ִٸ� �ٽ� ����� �Ѵ�
					if(map[y].charAt(x + 1) == '1') {
						if(wall == 0) {//���� �ν����� ���ٸ�
							queue.offer(new BWMBufferNode(y, x + 1, wall + 1, load + 1));//���� �ν� Ƚ���� �߰�
							visit[y][x + 1] = wall + 1;
						}
					}else {
						queue.offer(new BWMBufferNode(y, x + 1, wall, load + 1));
						visit[y][x + 1] = wall;
					}
				}
			}
			
		}
		return -1;//��ǥ�������� ���� ���
	}

}

class BWMBufferNode{
	int x;
	int y;
	int wall;//���� ���� Ƚ��
	int load;//�Ÿ�
	
	public BWMBufferNode(int y, int x, int wall, int load) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.load = load;
	}
}
