package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 알고리즘 원판 돌리기(17822번) 문제
어떠한 부분이 인접해 있는지 잘 체크해여하며 문제에서는 맨 위, 맨 아래간에 이동이 불가하지만
맨 왼쪽에서 맨 오른쪽으로 이동하는 것은 가능
덱으로 시계방향, 반시계방향 회전을 구현
BFS를 이용해 모든 구역의 인접한 공간 중 같은 값이 있다면 지우는 과정을 구현
 */

public class No17822 {

    static int n;//circleBoard의 행 길이
    static int[][] circleBoard;//원에 있는 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        circleBoard = new int[n + 1][m];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                circleBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int d1 = Integer.parseInt(st.nextToken());
            int k1 = Integer.parseInt(st.nextToken());

            for(int j = 1; j * x1 <= n; j++){
                rotate(d1, k1, j * x1);//원에 있는 내용을 x1의 배수 원들만 진행
            }

            setting();//인근의 숫자가 있으면 합치고 없으면 평균값과 비교해 값을 수정하는 메소드로 이동
        }

        int answer = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < m; j++){
                answer += circleBoard[i][j];
            }
        }

        System.out.println(answer);
    }

    //인근 숫자와 합치거나 평균값과 비교해 수정하는 메소드
    static void setting(){
        boolean check = false;//인근의 값을 고쳤음을 확인하는 변수

        int end = circleBoard[0].length - 1;
        boolean[][] visit = new boolean[circleBoard.length][circleBoard[0].length];
        for(int i = 1; i < circleBoard.length; i++){
            for(int j = 0; j < circleBoard[0].length; j++){
                if(circleBoard[i][j] == 0) continue;//이미 고쳤으면 그 부분은 탐색하지 않는다

                //BFS를 이용해 인근 지역의 수가 같으면 수를 삭제
                Queue<Area> queue = new LinkedList<>();
                queue.offer(new Area(i,j,circleBoard[i][j]));
                visit[i][j] = true;

                while(!queue.isEmpty()){
                    Area a = queue.poll();
                    int x = a.x;
                    int y = a.y;
                    int count = a.count;

                    //위쪽을 탐색할 수 있는 경우
                    if(x > 1 && !visit[x - 1][y] && circleBoard[x - 1][y] == count){
                        visit[x - 1][y] = true;
                        circleBoard[x][y] = 0;
                        circleBoard[x - 1][y] = 0;
                        queue.offer(new Area(x - 1, y, count));
                        check = true;
                    }

                    //아래쪽을 탐색할 수 있는 경우
                    if(x < n && !visit[x + 1][y] && circleBoard[x + 1][y] == count){
                        visit[x + 1][y] = true;
                        circleBoard[x][y] = 0;
                        circleBoard[x + 1][y] = 0;
                        queue.offer(new Area(x + 1, y, count));
                        check = true;
                    }

                    //왼쪽을 탐색할 수 있는 경우
                    if(y > 0 && !visit[x][y - 1] && circleBoard[x][y - 1] == count){
                        visit[x][y - 1] = true;
                        circleBoard[x][y] = 0;
                        circleBoard[x][y - 1] = 0;
                        queue.offer(new Area(x, y - 1, count));
                        check = true;
                    }else if(y == 0 && !visit[x][end] && circleBoard[x][end] == count){//맨 왼족에서 맨 오른쪽으로 갈 수 있는 경우
                        visit[x][end] = true;
                        circleBoard[x][y] = 0;
                        circleBoard[x][end] = 0;
                        queue.offer(new Area(x, end, count));
                        check = true;
                    }

                    //오른쪽으로 탐색할 수 있는 경우
                    if(y < end && !visit[x][y + 1] && circleBoard[x][y + 1] == count){
                        visit[x][y + 1] = true;
                        circleBoard[x][y] = 0;
                        circleBoard[x][y + 1] = 0;
                        queue.offer(new Area(x, y + 1, count));
                        check = true;
                    }else if(y == end && !visit[x][0] && circleBoard[x][0] == count){//맨 오른쪽에서 맨 왼쪽으로 탐색할 수 있는 경우
                        visit[x][0] = true;
                        circleBoard[x][y] = 0;
                        circleBoard[x][0] = 0;
                        queue.offer(new Area(x, 0, count));
                        check = true;
                    }
                }
            }
        }

        //인접구역을 못찾으면 평균을 구해 평균보다 큰 값은 1 빼고 작은 값은 1 더하는 과정
        if(!check){
            int count = 0;
            int sum = 0;

            for(int i = 1; i <= n; i++){
                for(int j = 0; j < circleBoard[0].length; j++){
                    if(circleBoard[i][j] > 0){//이미 지운 구간은 포함하지 않는다
                        count++;
                        sum += circleBoard[i][j];
                    }
                }
            }

            double avg = (double)sum / (double)count;//평균은 double로 구한다

            for(int i = 1; i <= n; i++){
                for(int j = 0; j < circleBoard[0].length; j++){
                    if(circleBoard[i][j] > 0 && circleBoard[i][j] > avg){//형균보다 크면 1 감소
                        circleBoard[i][j] -= 1;
                    }else if(circleBoard[i][j] > 0 && circleBoard[i][j] < avg){//평균보다 작으면 1 증가
                        circleBoard[i][j] += 1;
                    }
                }
            }
        }
    }

    //회전을 하는 메소드
    //덱을 이용해 시계방향이면 pollFirst -> offerLast
    //반시계 방향이면 pollLast -> offerFirst
    static void rotate(int d, int count, int index){
        int[] array = circleBoard[index];
        Deque<Integer> deque = new LinkedList<>();

        for(int i = 0; i < array.length; i++){
            deque.offerFirst(array[i]);
        }

        if(d == 0){
            for(int i = 0; i < count; i++){
                deque.offerLast(deque.pollFirst());
            }
        }else if(d == 1){
            for(int i = 0; i < count; i++){
                deque.offerFirst(deque.pollLast());
            }
        }

        for(int i = 0; i < array.length; i++){
            array[i] = deque.pollLast();
        }

        //회전 마무리 후 배열에 담는다
        circleBoard[index] = array;
    }
}

class Area{
    int x;
    int y;
    int count;

    public Area(int x, int y, int count){
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
