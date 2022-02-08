package Avatar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
소가 다리를 통해서 만나지 않는 경우의 수를 구하는 문제
전체 소가 쌍으로 이뤄지는 개수를 구한 다음
BFS를 통해서 다리를 건너지 않고 소를 만나면 그 수를 1씩 차감하면서 답을 구한다.
 */

public class No14466 {
    static int count;
    static int n;
    static int[] width = {0,0,-1,1};
    static int[] height = {-1,1,0,0};
    static boolean[][] cow_Arr;
    static boolean[][][] load;
    static ArrayList<Cow> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        count = k * (k - 1) / 2;//각 소들이 몇 쌍이 있는지
        load = new boolean[n + 1][n + 1][4];//목초지에 따라 다리가 어떻게 놓여져 있는지 3중 배열로 표현
        cow_Arr = new boolean[n + 1][n + 1];//소가 해당 목초지에 있는지 확인

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            if(r1 > r2){
                //위로 갈 경우
                //아래부분과 연결
                load[r1][c1][0] = true;
                load[r2][c2][1] = true;
            }else if(r1 < r2){
                //아래로 갈 경우
                //윗 부분과 연결
                load[r1][c1][1] = true;
                load[r2][c2][0] = true;
            }else if(c1 > c2){
                //왼쪽으로 갈 경우
                //오른쪽 부분과 연결
                load[r1][c1][2] = true;
                load[r2][c2][3] = true;
            }else if(c1 < c2){
                //오른쪽으로 갈 경우
                //왼쪽부분과 연결
                load[r1][c1][3] = true;
                load[r2][c2][2] = true;
            }
        }

        //소의 위치를 리스트에 담는 for문
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Cow(x, y));
            cow_Arr[x][y] = true;
        }

        for(int i = 0; i < list.size(); i++){
            counting(list.get(i).x, list.get(i).y);
            cow_Arr[list.get(i).x][list.get(i).y] = false;
        }

        System.out.println(count);
    }

    //현재 소의 위치로 BFS를 통해 연결되는 소를 알아보는 메서드
    static void counting(int x, int y){
        boolean[][] visit = new boolean[cow_Arr.length][cow_Arr.length];
        visit[x][y] = true;//현재 소의 위치는 먼저 방문처리

        Queue<Cow> queue = new LinkedList<>();
        queue.offer(new Cow(x, y));
        while(!queue.isEmpty()){
            Cow c = queue.poll();

            //현재 소의 위치
            int cx = c.x;
            int cy = c.y;

            //다리가 놓여진 배열
            boolean[] load_Arr = load[cx][cy];

            for(int i = 0; i < 4; i++){
                int nx = cx + height[i];
                int ny = cy + width[i];

                if(nx >= 1 && nx <= n && ny >= 1 && ny <= n){
                    //다리가 안놓여지고, 방문도 안하고, 소가 있다면 count를 차감한다
                    if(!load_Arr[i] && !visit[nx][ny]){
                        if(cow_Arr[nx][ny]){
                            count--;
                        }

                        visit[nx][ny] = true;
                        queue.offer(new Cow(nx, ny));
                    }
                }
            }
        }
    }
}

class Cow{
    int x;
    int y;

    public Cow(int x, int y){
        this.x = x;
        this.y = y;
    }
}
