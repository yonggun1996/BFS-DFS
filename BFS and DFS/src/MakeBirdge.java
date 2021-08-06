

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBirdge {
	
	static int count = 1;//�� ��ȣ�� ���� ����
	static int answer = Integer.MAX_VALUE;//������ �� ����
	static boolean[][] visit;//���� �湮 ����
	static int[][] map;
	static Queue<NodeData> queue = new LinkedList<NodeData>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visit = new boolean[n][n];
		
		//���� �Է�
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//������ ������ �ִ� �� ��ȣ�� ���̴� ����
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1 && !visit[i][j]) {
					queue.offer(new NodeData(j, i, 0));
					visit[i][j] = true;//ť�� ������ �湮���θ� üũ�ؾ� �޸� �ʰ��� �Ͼ�� �ʴ´�
					setting_island();
					count++;
				}
			}
		}
		
		//�������ִ� ���� �����ϴ� ����
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 0) {
					visit = new boolean[n][n];
					visit[i][j] = true;//ť�� ������ �湮���θ� üũ�ؾ� �޸� �ʰ��� �Ͼ�� �ʴ´�
					queue.offer(new NodeData(j, i, 0));
					searchLoad(map[i][j]);
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	//���� �ٸ� ������ ����
	public static void setting_island() {
		while(!queue.isEmpty()) {
			NodeData nd = queue.poll();
			int width = nd.width;
			int height = nd.height;
			
			map[height][width] = count;
			
			//���� �� �� ������, �� ���� �湮���� �ʾҰ� ���� �����̶�� Ž�� ������ ������ ���� ť�� �ִ´�
			if(height > 0 && !visit[height - 1][width] && map[height - 1][width] == 1) {
				queue.offer(new NodeData(width, height - 1 , 0));
				visit[height - 1][width] = true;
			}
			
			//�Ʒ��� �� �� ������, �Ʒ� ���� �湮���� �ʾҰ� ���� �����̶�� Ž�� ������ ������ ���� ť�� �ִ´�
			if(height < map.length - 1 && !visit[height + 1][width] && map[height + 1][width] == 1) {
				queue.offer(new NodeData(width, height + 1, 0));
				visit[height + 1][width] = true;
			}
			
			//�������� �� �� ������, ���� ���� �湮���� �ʾҰ� ���� �����̶�� Ž�� ������ ������ ���� ť�� �ִ´�
			if(width > 0 && !visit[height][width - 1] && map[height][width - 1] == 1) {
				queue.offer(new NodeData(width - 1, height, 0));
				visit[height][width - 1] = true;
			}
			
			//������ �� �� ������, ������ ���� �湮���� �ʾҰ� ���� �����̶�� Ž�� ������ ������ ���� ť�� �ִ´�
			if(width < map.length - 1 && !visit[height][width + 1] && map[height][width + 1] == 1) {
				queue.offer(new NodeData(width + 1, height, 0));
				visit[height][width + 1] = true;
			}
		}
	}
	
	//�ش� ��ġ���� ������ �� �ִ� �ٸ� ���� ã�� �޼���
	public static void searchLoad(int start) {
		while(!queue.isEmpty()) {
			NodeData nd = queue.poll();
			int width = nd.width;
			int height = nd.height;
			int dist = nd.dist;
			
			//���� ���� �� ��  �ִ� ���
			if(height > 0 && !visit[height - 1][width]) {
				if(map[height - 1][width] == 0) {//�ٴ������̶�� �湮üũ �� ť�� �ִ´�
					visit[height - 1][width] = true;
					queue.offer(new NodeData(width, height - 1 , dist + 1));
				}else if(dist > 0 && map[height - 1][width] != start){//�� �� ���� �ٸ� ���̶�� �ּڰ��� �����Ѵ�.
					answer = Math.min(answer, dist);
				}
			}
			
			if(height < map.length - 1 && !visit[height + 1][width]) {
				if(map[height + 1][width] == 0) {//�ٴ������̶�� �湮üũ �� ť�� �ִ´�
					visit[height + 1][width] = true;
					queue.offer(new NodeData(width, height + 1 , dist + 1));
				}else if(dist > 0 && map[height + 1][width] != start){//�Ʒ� �� ���� �ٸ� ���̶�� �ּڰ��� �����Ѵ�.
					answer = Math.min(answer, dist);
				}
			}
			
			if(width > 0 && !visit[height][width - 1]) {
				if(map[height][width - 1] == 0) {//�ٴ������̶�� �湮üũ �� ť�� �ִ´�
					visit[height][width - 1] = true;
					queue.offer(new NodeData(width - 1, height , dist + 1));
				}else if(dist > 0 && map[height][width - 1] != start){//���� ���� �ٸ� ���̶�� �ּڰ��� �����Ѵ�.
					answer = Math.min(answer, dist);
				}
			}
			
			if(width < map.length - 1 && !visit[height][width + 1]) {
				if(map[height][width + 1] == 0) {//�ٴ������̶�� �湮üũ �� ť�� �ִ´�
					visit[height][width + 1] = true;
					queue.offer(new NodeData(width + 1, height , dist + 1));
				}else if(dist > 0 && map[height][width + 1] != start){//���� ���� �ٸ� ���̶�� �ּڰ��� �����Ѵ�.
					answer = Math.min(answer, dist);
				}
			}
		}
	}

}

class NodeData{
	int width;//����
	int height;//����
	int dist;//�Ÿ�
	
	public NodeData(int width, int height, int dist) {
		this.width = width;
		this.height = height;
		this.dist = dist;
	}
}
