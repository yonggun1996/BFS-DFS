package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No1033 {

    static HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
    static int[][] result_Arr;//0번 재료를 1로 기준치를 잡았을 때 비율을 담은 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        result_Arr = new int[n][2];
        for(int i = 0; i < n; i++){
            map.put(i, new ArrayList<>());
        }

        for(int i = 0; i < n - 1; i++){
            String[] str_Arr = br.readLine().split(" ");
            int nd1 = Integer.parseInt(str_Arr[0]);//재료1
            int nd2 = Integer.parseInt(str_Arr[1]);//재로2
            //재료의 비율을 표현한 정수
            int num1 = Integer.parseInt(str_Arr[2]);
            int num2 = Integer.parseInt(str_Arr[3]);

            ArrayList<int[]> list1 = map.get(nd1);
            int[] arr1 = {nd2, num2, num1};//다음노드, 분자, 분모순
            list1.add(arr1);//위 데이터를 담은 배열을 리스트에 담는다
            map.put(nd1, list1);

            ArrayList<int[]> list2 = map.get(nd2);
            int[] arr2 = {nd1, num1, num2};
            list2.add(arr2);
            map.put(nd2, list2);
        }

        search();

        //최대공약수를 구해서 각 재료의 비율을 최대한 약분하는 과정
        for(int i = 0; i < result_Arr.length; i++){
            int lcm = get_lcm(result_Arr[i][1], result_Arr[i][0]);//최대공약수
            result_Arr[i][0] /= lcm;//최대공약수에 따라 분자를 약분
            result_Arr[i][1] /= lcm;//최대공약수에 따라 분모를 약분
        }

        int[] answer = get_answer();

        //정답을 출력
        for(int i : answer){
            System.out.println(i);
        }
    }

    //최대공약수를 구하는 메서드
    //유클리드 호제법을 토대로 최대공약수를 구한다
    static int get_lcm(int num1, int num2){
        while(true){
            int n = num1 % num2;
            num1 = num2;
            num2 = n;

            if(num2 == 0){
                return num1;
            }
        }
    }

    //답을 담은 배열을 반환하는 메서드
    //각 분모의 최대값을 구한 후 곱해가면서 모든 분모에 나눠지는 수에 따라서 곱해서 값을 매긴다
    static int[] get_answer(){
        int max = 0;//가장 큰 분모를 구하는 과정
        for(int i = 0; i < result_Arr.length; i++){
            max = Math.max(max, result_Arr[i][1]);
        }

        int num = 1;
        while(true){
            boolean check = true;
            int result = num * max;

            for(int i = 0; i < result_Arr.length; i++){
                if(result % result_Arr[i][1] != 0){//수가 나눠지지 않는다면 곱할 값을 늘린다
                    check = false;
                    break;
                }
            }

            if(check) break;
            num++;
        }

        int[] arr = new int[result_Arr.length];
        int result = num * max;
        for(int i = 0; i < result_Arr.length; i++){
            int answer = result_Arr[i][0] * (result / result_Arr[i][1]);//정답요소는 분수의 곱을 통해서 값을 얻는다
            arr[i] = answer;
        }

        return arr;//답은 담은 배열을 반환
    }

    //BFS를 통해서 0번에서 부터 살펴본다
    //0을 1로 기준삼고 이어진 부분들의 비율을 설정한다
    static void search(){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visit = new boolean[result_Arr.length];
        visit[0] = true;
        result_Arr[0][0] = 1; result_Arr[0][1] = 1;

        while(!queue.isEmpty()){
            int nd = queue.poll();
            ArrayList<int[]> list = map.get(nd);

            for(int i = 0; i < list.size(); i++){
                int[] arr = list.get(i);
                int next = arr[0];

                if(!visit[next]){
                    visit[next] = true;//방문처리
                    result_Arr[next][0] = arr[1] * result_Arr[nd][0];//현 재료의 분자와 다음 재료의 분자를 곱해 값을 설정
                    result_Arr[next][1] = arr[2] * result_Arr[nd][1];//현 재료의 분모와 다음 재료의 분모를 곱해 값을 설정
                    queue.offer(next);//큐에 다음 노드를 담는다
                }
            }
        }
    }
}
