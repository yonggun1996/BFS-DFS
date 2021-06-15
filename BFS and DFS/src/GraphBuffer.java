import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GraphBuffer {
	
	static char[] color;
	static HashMap<Integer, ArrayList<Integer>> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testcase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < testcase; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			color = new char[v];
			
			map = new HashMap<Integer, ArrayList<Integer>>();
			for(int j = 0; j < v; j++) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				map.put(j + 1, list);
			}
			
			StringTokenizer st2;
			for(int j = 0; j < e; j++) {
				st2 = new StringTokenizer(br.readLine());
				int point1 = Integer.parseInt(st2.nextToken());
				int point2 = Integer.parseInt(st2.nextToken());
				
				ArrayList<Integer> list1 = map.get(point1);
				list1.add(point2);
				map.put(point1, list1);
				
				ArrayList<Integer> list2 = map.get(point2);
				list2.add(point1);
				map.put(point2, list2);
			}
			
			String answer = "YES";
			color[0] = 'B';
			for(int j = 0; j < v; j++) {
				if(color[j] != 'B' && color[j] != 'R') {
					color[j] = 'B';
				}
				
				if(!DFS(j + 1)) {
					answer = "NO";
					break;
				}
			}
			
			//System.out.println(answer);
			bw.write(answer);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	public static boolean DFS(int key) {
		boolean flag = true;
		ArrayList<Integer> list = map.get(key);
		char c = color[key - 1];
		
		for(int i = 0; i < list.size() && flag; i++) {
			int idx = list.get(i);
			
			if(color[idx - 1] != 'B' && color[idx - 1] != 'R') {
				if(c == 'B') {
					color[idx - 1] = 'R';
				}else if(c == 'R') {
					color[idx - 1] = 'B';
				}
				
				flag = DFS(idx);
			}else if(c == color[idx - 1]) {
				return false;
			}
		}
		
		return flag;
	}

}
