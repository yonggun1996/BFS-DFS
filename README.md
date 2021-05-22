# BFS & DFS

### BFS(Breadth-First-Search) : 영문 그대로 직역하면 너비 우선 탐색입니다. 어떠한 정점을 방문 후 그 정점에 인접한 모든 정점을 우선적으로 방문하는 방식입니다.

### 트리로 알아보는 BFS

예를 들어 이러한 트리가 있다고 가정합니다

![탐색1](https://user-images.githubusercontent.com/68115246/119229464-2ad1bc00-bb53-11eb-8147-ecd63cb8340d.png)

1. 먼저 맨 위에있는 노드부터 탐색합니다.

![탐색2](https://user-images.githubusercontent.com/68115246/119229609-ce22d100-bb53-11eb-94a2-e1b79162f799.png)

2. 탐색한 노드의 자식노드를 확인합니다. 자식노드를 탐색하는데 순서는 왼족에서 오른쪽으로 한다고 가정하며 순서대로 탐색하면 다음과 같습니다.

![탐색3](https://user-images.githubusercontent.com/68115246/119229617-daa72980-bb53-11eb-823b-46b500457db8.png)

![탐색4](https://user-images.githubusercontent.com/68115246/119229619-dda21a00-bb53-11eb-83a7-7a7ad1c82fc9.png)

![탐색5](https://user-images.githubusercontent.com/68115246/119229621-df6bdd80-bb53-11eb-838a-e9024b35d27e.png)

2, 3, 4 순서대로 탐색을 합니다.

3. 그 하위노드도 차례대로 살핍니다. 2번의 하위노드 5, 3번의 하위노드 6 그리고 7(왼쪽부터 순서대로 확인하기로 했기 때문에 6부터 확인합니다.), 4번의 하위노드 8을 순서대로 확인합니다.

![탐색6](https://user-images.githubusercontent.com/68115246/119229627-e4309180-bb53-11eb-9bf2-fe94a0c83e33.png)

![탐색7](https://user-images.githubusercontent.com/68115246/119229632-e692eb80-bb53-11eb-9162-b018e9279780.png)

![탐색8](https://user-images.githubusercontent.com/68115246/119229634-e8f54580-bb53-11eb-889e-7426c7b88705.png)

이 트리를 BFS로 탐색하게 되면 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 순으로 확인할 수 있습니다.
