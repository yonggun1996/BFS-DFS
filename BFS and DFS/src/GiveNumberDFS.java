import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GiveNumberDFS {

	static String[] arr;
	static boolean[][] visit;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = Integer.parseInt(in.next());
		arr = new String[n];
		visit = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = in.next();
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
		System.out.println(list.size());
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
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
