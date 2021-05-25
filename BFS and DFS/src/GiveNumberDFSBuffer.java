import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GiveNumberDFSBuffer {

	static String[] arr;
	static boolean[][] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		arr = new String[n];
		visit = new boolean[n][n];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = st.nextToken();
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			String s = arr[i];
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) == '1' && !visit[i][j]) {
					visit[i][j] = true;
					int count = search(i,j);
					list.add(count);
				}
			}
		}
		
		Collections.sort(list);
		bw.write(String.valueOf(list.size()));
		bw.newLine();
		//System.out.println(list.size());
		
		for(int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i));
			bw.write(String.valueOf(list.get(i)));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
	
	public static int search(int i, int j) {
		int count = 1;
		
		//위 데이터 확인
		if(i > 0 && arr[i - 1].charAt(j) == '1' && !visit[i - 1][j]) {
			visit[i - 1][j] = true;
			count += search(i - 1, j);
		}
		
		//아래 데이터 확인
		if(i < arr.length - 1 && arr[i + 1].charAt(j) == '1' && !visit[i + 1][j]) {
			visit[i + 1][j] = true;
			count += search(i + 1, j);
		}
		
		//오른쪽 데이터 확인
		if(j > 0 && arr[i].charAt(j - 1) == '1' && !visit[i][j - 1]) {
			visit[i][j - 1] = true;
			count += search(i, j - 1);
		}
		
		//왼쪽 데이터 확인
		if(j < arr.length - 1 && arr[i].charAt(j + 1) == '1' && !visit[i][j + 1]) {
			visit[i][j + 1] = true;
			count += search(i, j + 1);
		}
		
		return count;
	}

}
