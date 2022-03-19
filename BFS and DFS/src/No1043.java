package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class No1043 {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();//그래프 연결
    static HashSet<Integer> set = new HashSet<>();//진실을 아는 사람들을 담은 set
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++){
            map.put(i, new ArrayList<>());
        }

        //진실을 아는 사람들을 담은 리스트와 주어진 문자열을 배열로 나누는 작업
        ArrayList<Integer> know_list = new ArrayList<>();
        String[] know_Arr = br.readLine().split(" ");
        int know_len = Integer.parseInt(know_Arr[0]);
        for(int i = 1; i <= know_len; i++){
            know_list.add(Integer.parseInt(know_Arr[i]));
        }

        ArrayList<String[]> m_List = new ArrayList<>();//입력값에 대한 문자열 배열을 담은 리스트
        for(int i = 0; i < m; i++){
            String[] str_Arr = br.readLine().split(" ");
            set_Map(str_Arr);
            m_List.add(str_Arr);
        }

        //진실을 아는 사람을 토대로 주변에 진실을 아는 사람이 얼마나 있는지 확인하는 for문
        for(int i = 0; i < know_len; i++){
            search(know_list.get(i));
        }

        int answer = 0;
        for(int i = 0; i < m; i++){
            String[] str_Arr = m_List.get(i);
            boolean check = true;//거짓을 이야기 할 수 있는지
            int str_len = Integer.parseInt(str_Arr[0]);

            for(int j = 1; j <= str_len; j++){
                //진실을 아는 사람이 있어 거짓을 이야기 할 수 없는 경우 탐색을 멈춘다
                if(set.contains(Integer.parseInt(str_Arr[j]))){
                    check = false;
                    break;
                }
            }

            if(check) answer++;//거짓을 말할 수 있는 파티 그룹이면 answer를 늘린다
        }

        System.out.println(answer);
    }

    //BFS를 통해서 입력받은 진실을 아는 사람들로 시작해 진실을 알게 되는 사람들을 파악한다.
    //set를 이용해서 진실을 알고 있는 사람을 담는다
    static void search(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        set.add(start);

        while(!queue.isEmpty()){
            int key = queue.poll();
            ArrayList<Integer> list = map.get(key);
            for(int i = 0; i < list.size(); i++){
                int next = list.get(i);
                if(!set.contains(next)){
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
    }

    //그래프를 만드는 메서드
    //입력받은 문자열의 배열이 매개변수가 된다
    static void set_Map(String[] str_Arr){
        int len = Integer.parseInt(str_Arr[0]);

        //이중 for문을 사용해서 그래프를 구성하는 코드
        for(int i = 1; i <= len; i++){
            for(int j = i + 1; j <= len; j++){
                ArrayList<Integer> list1 = map.get(Integer.parseInt(str_Arr[i]));
                list1.add(Integer.parseInt(str_Arr[j]));
                map.put(Integer.parseInt(str_Arr[i]), list1);

                ArrayList<Integer> list2 = map.get(Integer.parseInt(str_Arr[j]));
                list2.add(Integer.parseInt(str_Arr[i]));
                map.put(Integer.parseInt(str_Arr[j]), list2);
            }
        }
    }
}
