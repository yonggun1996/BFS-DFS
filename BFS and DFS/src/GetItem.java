package Programmers;

import java.util.LinkedList;
import java.util.Queue;

/*
문제 푸는데 도움이 된 블로그 : https://arinnh.tistory.com/88?category=934581
각 꼭짓점이 주어지면서 테두리는 1을 그렇지 않은 부분은 2로 설정
추후에 테두리를 그릴 때 이미 테두리가 아닌 부분을 만나면 2로 설정
크기를 51 x 51로 할 경우 1이 사방으로 모두 연결될 수 있기 때문에 101 x 101로 배열을 설정
테두리를 설정한 후 BFS로 가장 짧은 거리 탐색
 */

public class GetItem {
    public static void main(String[] args) {
        GetItem gi = new GetItem();
        int[][] rectangle = {{48,48,50,50},{46,49,50,50}};
        int characterX = 48;
        int characterY = 48;
        int itemX = 50;
        int itemY = 50;

        System.out.println(gi.solution(rectangle, characterX, characterY, itemX, itemY));
    }

    static int[][] point_Arr = new int[101][101];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int i = 0; i < rectangle.length; i++){//테두리를 설정
            int x1 = rectangle[i][0] * 2;//x축 시작
            int x2 = rectangle[i][2] * 2;//x축 끝
            int y1 = rectangle[i][1] * 2;//y축 시작
            int y2 = rectangle[i][3] * 2;//y축 끝

            for(int j = y1; j <= y2; j++){
                for(int k = x1; k <= x2; k++){
                    if(j == y1 || j == y2 || k == x1 || k == x2){//테두리를 그려야할 부분리아면
                        if(point_Arr[j][k] != 2){//이미 테두리가 아닌 부분으로 설정되지 않았다면 테두리를 설정
                            point_Arr[j][k] = 1;
                        }
                    }else{//테두리가 아닌 부분은 2로 설정
                        point_Arr[j][k] = 2;
                    }
                }
            }
        }

        int dist = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
        return dist / 2;
    }

    //BFS로 목적지까지 최단거리를 구하는 방법
    static int bfs(int start_X, int start_Y, int finish_X, int finish_Y){
        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(start_X, start_Y, 0));
        boolean[][] visit = new boolean[101][101];
        visit[start_Y][start_X] = true;
        int answer = Integer.MAX_VALUE;

        while(!queue.isEmpty()){
            Location l = queue.poll();
            int x = l.x;
            int y = l.y;
            int dist = l.dist;

            if(x == finish_X && y == finish_Y){//해당 목적지 도착하면 answer를 짧은 거리로 초기화
                answer = Math.min(answer, dist);
            }

            //윗 부분이 방문할 수 있는지 확인
            if(y + 1 <= 100 && point_Arr[y + 1][x] == 1 && !visit[y + 1][x]){
                queue.offer(new Location(x, y + 1, dist + 1));
                visit[y + 1][x] = true;
            }

            //아랫부분이 방문할 수 있는 구역인지 확인
            if(y - 1 >= 0 && point_Arr[y - 1][x] == 1 && !visit[y - 1][x]){
                queue.offer(new Location(x, y - 1, dist + 1));
                visit[y - 1][x] = true;
            }

            //오른쪽 부분이 방문할 수 있는지 확인
            if(x + 1 <= 100 && point_Arr[y][x + 1] == 1 && !visit[y][x + 1]){
                queue.offer(new Location(x + 1, y, dist + 1));
                visit[y][x + 1] = true;
            }

            //왼쪽 부분이 방문할 수 있는지 확인
            if(x - 1 >= 0 && point_Arr[y][x - 1] == 1 && !visit[y][x - 1]){
                queue.offer(new Location(x - 1, y, dist + 1));
                visit[y][x - 1] = true;
            }
        }

        return answer;
    }
}

//좌표를 표현한 클래스
class Location{
    int x;
    int y;
    int dist;

    public Location(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}
