package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Alphabet {

    /*
    0행 0열부터 시작해 중복되지 않는 알파벳을 계속 붙일경우 얼마까지가 정답일까?
    set로 탐색하면서 살펴본 알파벳을 넣는다
    DFS로 탐색한다
     */

    static int max = 1;//정답이 될 변수
    static char[][] alphabet;//입력받은 알파벳을 담는 2차원 배열
    static HashSet<Character> set = new HashSet<>();//알파벳을 집어넣는 Hashset

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        alphabet = new char[r][c];

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            for(int j = 0; j < c; j++){
                alphabet[i][j] = s.charAt(j);
            }
        }

        dfs(0,0, 1);
        System.out.println(max);
    }

    public static void dfs(int x, int y, int dist){
        if(!set.contains(alphabet[x][y])){//해당 알파벳이 없는 경우에만 탐색을 한다
            max = Math.max(max, dist);

            if(max == 26){//최대 26자까지 나올 수 있기 때문에 26이면 반환한다
                return;
            }

            set.add(alphabet[x][y]);//중복되지 않은 알파벳을 set에 삽입한다
            if(x > 0){//위쪽
                dfs(x - 1, y, dist + 1);
            }

            if(x < alphabet.length - 1){//아래쪽
                dfs(x + 1, y, dist + 1);
            }

            if(y > 0){//왼쪽
                dfs(x, y - 1, dist + 1);
            }

            if(y < alphabet[0].length - 1){//오른쪽
                dfs(x, y + 1, dist + 1);
            }

            set.remove(alphabet[x][y]);//모든 지역을 다 확인했으면 해당 위치의 알파벳을 삭제한다
        }
    }

}
