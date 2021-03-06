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

### DFS(Depth-First-Search) : 직역하자면 너비우선 탐색입니다. 어떠한 정점을 방문한 후 그 정점과 연결된 정점을 하나씩 탐색하는 방법입니다. 탐색한 후 연결된 정점이 없으면 이전에 출발했던 정점으로 돌아가 다른 정점에 연결이 되어있다면 그 정점을 살펴보고 마찬가지로 연결된 정점이 없으면 이전에 출발했던 정점으로 돌아가고 이 과정을 다 탐색할때까지 반복합니다.

아까 BFS를 예시로 들었던 트리를 그대로 예로 들겠습니다. 

1. 이번에도 맨 위 노드를 탐색합니다.

![탐색1](https://user-images.githubusercontent.com/68115246/119842839-fe96b080-bf41-11eb-91c5-b3014e2b39f0.png)

2. 이번에도 같은 레벨에 있는 노드중 왼쪽에 있는 노드를 먼저 확인합니다. 어기서는 2를 먼저 확인합니다.

![탐색2](https://user-images.githubusercontent.com/68115246/119842846-00607400-bf42-11eb-870f-53742f44c175.png)

3. 2의 자식노드인 5를 확인합니다.

![탐색3](https://user-images.githubusercontent.com/68115246/119842851-022a3780-bf42-11eb-8817-a4aa2e10d950.png)

4. 5의 자식노드는 없습니다. 다시 노드 2로 돌아옵니다. 또 다른 자식노드가 없습니다. 같은 레벨의 오른쪽에있는 3을 탐색합니다.

![탐색4](https://user-images.githubusercontent.com/68115246/119842862-035b6480-bf42-11eb-816c-e999710cd7b6.png)

5. 3의 자식노드를 확인합니다. 2개의 노드 중 왼쪽에 있는 6을 확인합니다.

![탐색5](https://user-images.githubusercontent.com/68115246/119842872-05252800-bf42-11eb-83e4-f248b6813d30.png)

6. 6의 자식노드는 없습니다. 다시 노드 3으로 돌아옵니다. 이번엔 또 다른 자식노드 7을 탐색합니다.

![탐색6](https://user-images.githubusercontent.com/68115246/119842886-06eeeb80-bf42-11eb-8a5e-00d29510ab7a.png)

7. 7의 자식노드는 없습니다. 다시 노드 3으로 돌아옵니다. 노드 3의 자식노드는 다 확인했고 이번에는 같은 레벨에 확인하지 못한 4를 탐색합니다.

![탐색7](https://user-images.githubusercontent.com/68115246/119842891-08201880-bf42-11eb-8686-03fa62f4f4f0.png)

8. 4의 자식노드인 8을 확인합니다.

![탐색8](https://user-images.githubusercontent.com/68115246/119842903-09514580-bf42-11eb-9d27-33bf5ed94ece.png)

이렇게 DFS 방식으로 트리를 탐색했습니다. 순서는 1 - 2 - 5 - 3 - 6 - 7 - 4 - 8 순으로 확인할 수 있습니다.
