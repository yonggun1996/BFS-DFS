import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class CabbageBuffer {
	
	static int[][] field;
	static boolean[][] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < testcase; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			
			field = new int[height][width];
			visit = new boolean[height][width];
			
			int count = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < count; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
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
			
			//System.out.println(answer);
			bw.write(String.valueOf(answer));
			bw.newLine();
		}
		bw.flush();
		bw.close();
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
