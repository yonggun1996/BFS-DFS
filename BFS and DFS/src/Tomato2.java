import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato2 {
	
	static int max = 0;
	static int[][][] arr;
	static Queue<TomatoNode2> queue = new LinkedList<TomatoNode2>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int x = in.nextInt();//5
		int y = in.nextInt();//3
		int z = in.nextInt();//2
		
		//높이 : z, 세로 : y, 가로 : x
		arr = new int[z][y][x];
		
		for(int i = 0; i < z; i++) {
			for(int j = 0; j < y; j++) {
				for(int k = 0; k < x; k++) {
					arr[i][j][k] = in.nextInt();
					
					if(arr[i][j][k] == 1) {
						max = Math.max(max, arr[i][j][k]);
						TomatoNode2 t = new TomatoNode2(k, j, i);
						queue.offer(t);
					}
				}
			}
		}
		
		BFS();
		
		boolean flag = true;
		for(int i = 0; i < z; i++) {
			for(int j = 0; j < y; j++) {
				for(int k = 0; k < x; k++) {
					if(arr[i][j][k] == 0) {
						flag = false;
					}
				}
			}
		}
		
		if(flag) {
			System.out.println(max - 1);
		}else {
			System.out.println(-1);
		}
		
	}
	
	public static void BFS() {
		while(!queue.isEmpty()) {
			TomatoNode2 tn2 = queue.poll();
			
			int x = tn2.x;//가로
			int y = tn2.y;//세로
			int z = tn2.z;//높이
			
			int num = arr[z][y][x];
			max = Math.max(max, num);
			
			if(x > 0 && arr[z][y][x - 1] == 0) {
				queue.offer(new TomatoNode2(x - 1, y, z));
				arr[z][y][x - 1] = num + 1;
			}
			
			if(x < arr[0][0].length - 1 && arr[z][y][x + 1] == 0) {
				queue.offer(new TomatoNode2(x + 1, y, z));
				arr[z][y][x + 1] = num + 1;
			}
			
			if(y > 0 && arr[z][y - 1][x] == 0) {
				queue.offer(new TomatoNode2(x, y - 1, z));
				arr[z][y - 1][x] = num + 1;
			}
			
			if(y < arr[0].length - 1 && arr[z][y + 1][x] == 0) {
				queue.offer(new TomatoNode2(x, y + 1, z));
				arr[z][y + 1][x] = num + 1;
			}
			
			if(z > 0 && arr[z - 1][y][x] == 0) {
				queue.offer(new TomatoNode2(x, y, z - 1));
				arr[z - 1][y][x] = num + 1;
			}
			
			if(z < arr.length - 1 && arr[z + 1][y][x] == 0) {
				queue.offer(new TomatoNode2(x, y, z + 1));
				arr[z + 1][y][x] = num + 1;
			}
		}
	}

}


class TomatoNode2{
	int x;
	int y;
	int z;
	
	public TomatoNode2(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
