import java.util.Scanner;

public class Cabbage {
	
	static int[][] field;
	static boolean[][] visit;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int testcase = in.nextInt();
		for(int i = 0; i < testcase; i++) {
			int height = in.nextInt();
			int width = in.nextInt();
			
			field = new int[height][width];
			visit = new boolean[height][width];
			
			int count = in.nextInt();
			
			for(int j = 0; j < count; j++) {
				int x = in.nextInt();
				int y = in.nextInt();
				
				field[x][y] = 1;
			}
			
			int answer = 0;
			for(int j = 0; j < height; j++) {
				for(int k = 0; k < width; k++) {
					if(field[j][k] == 1 && !visit[j][k]) {
						visit[j][k] = true;
						search(j, k);
						answer++;
					}
				}
			}
			
			System.out.println(answer);
			
		}
	}
	
	public static void search(int x, int y) {
		if(x > 0 && field[x - 1][y] == 1 && !visit[x - 1][y]) {
			visit[x - 1][y] = true;
			search(x - 1, y);
		}
		
		if(x < field.length - 1 && field[x + 1][y] == 1 && !visit[x + 1][y]) {
			visit[x + 1][y] = true;
			search(x + 1, y);
		}
		
		if(y > 0 && field[x][y - 1] == 1 && !visit[x][y]) {
			visit[x][y - 1] = true;
			search(x, y - 1);
		}
		
		if(y < field[0].length - 1 && field[x][y + 1] == 1 && !visit[x][y + 1]) {
			visit[x][y + 1] = true;
			search(x, y + 1);
		}
	}

}
