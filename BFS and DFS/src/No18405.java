package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
18405번 문제
바이러스가 전염된 후 s초가 지나 x행 y열의 바이러스 번호는 무엇인가
리스트에 최근에 전염된 바이러스를 삽입하고 번호순대로 정렬 후 전염을 시킨다
 */

public class No18405 {

    //상하좌우로 확인해보기 위한 배열
    static int[] x_Arr = {-1,1,0,0};
    static int[] y_Arr = {0,0,-1,1};
    static int[][] map;//바이러스 분포 배열
    static LinkedList<Virus18405> list = new LinkedList<>();//최근에 생긴 바이러스를 담는 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0){//초기에 바이러스가 있는 곳은 리스트에 담는다
                    list.add(new Virus18405(i,j,map[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        for(int i = 0; i < s; i++){
            move_Virus();
        }

        System.out.println(map[x - 1][y - 1]);
    }

    //바이러스를 전염시키는 메소드
    static void move_Virus(){
        //바이러스 번호가 낮은 데이터를 기준으로 오름차순 정렬
        Collections.sort(list, new Comparator<Virus18405>() {
            @Override
            public int compare(Virus18405 o1, Virus18405 o2) {
                return o1.num - o2.num;
            }
        });

        int max = list.size();//최근에 생긴 바이러스 수 만큼 실행하기 위해 변수 선언
        int cnt = 0;
        while(cnt < max){
            //리스트에 있는 바이러스를 하나씩 삭제하는 과정
            Virus18405 v = list.get(0);
            list.remove(0);

            int x = v.x;
            int y = v.y;
            int num = v.num;

            //상하좌우 살펴보는 for문
            for(int i = 0; i < 4; i++){
                int nx = x + x_Arr[i];
                int ny = y + y_Arr[i];

                if(nx > -1 && nx < map.length){//nx값이 배열 범위내 확인할 수 있다면
                    if(ny > -1 && ny < map.length){//ny값이 배열 범위 내 확인할 수 있다면
                        if(map[nx][ny] == 0){//아직 전염이 안 된 구간이라면 바이러스를 전염시킨다
                            map[nx][ny] = num;
                            list.add(new Virus18405(nx, ny, map[nx][ny]));//리스크에 새로운 바이러스를 추가
                        }
                    }
                }
            }

            cnt++;
        }
    }
}

class Virus18405{
    int x;
    int y;
    int num;

    public Virus18405(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
}
