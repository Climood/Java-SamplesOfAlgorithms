public class orientedWeightGraph { //Пример взвешенного, но НЕнаправленного графа
    private final int MAX_VERTEXS = 100;
    private final int INFINITE = 9999999; //для инициализации путей, цена которых еще не известна :) с Integer.MAX_VALUE что то пошло не так
    private int numVertex;
    private int[][] smezhMatrix;
    private Vertex[] vertexList;

    orientedWeightGraph() {
        vertexList = new Vertex[MAX_VERTEXS];
        smezhMatrix = new int[MAX_VERTEXS][MAX_VERTEXS];
        numVertex = 0;
        for (int i = 0; i < MAX_VERTEXS; i++) {
            for (int j = 0; j < MAX_VERTEXS; j++) {
                smezhMatrix[i][j] = INFINITE;
            }
        }
    }

    //--------------------------------------------------------------------------------------
    void addVertex(String StringValue, int intValue) {
        vertexList[numVertex] = new Vertex(StringValue, intValue);
        vertexList[numVertex].index = numVertex;
        numVertex++;
    }

    //--------------------------------------------------------------------------------------
    void deleteVertex(int index) { //Метод для удаления вершины из графа ( можно использовать при топологической сортировке)
        for (int i = 0; i < numVertex; i++) { //Удаляем все возможные ребра, связанные с этой вершиной
            smezhMatrix[i][index] = INFINITE;
            smezhMatrix[index][i] = INFINITE;
        }
        while (index != numVertex - 1) {
            vertexList[index] = vertexList[index + 1];
            index++;
        }
        numVertex--;
    }

    //--------------------------------------------------------------------------------------
    void addEdge(int from, int to, int edgeWeight) {
        smezhMatrix[from][to] = edgeWeight;
    }

    void showVertex(int index) {
        vertexList[index].show();
    }

    //--------------------------------------------------------------------------------------
    void showSmezhMatrix() {
        for (int j = 0; j < numVertex; j++) {
            System.out.print("   " + vertexList[j].getStringValue());
        }
        System.out.println();
        for (int i = 0; i < numVertex; i++) {
            System.out.print(vertexList[i].getStringValue() + "  ");
            for (int j = 0; j < numVertex; j++) {
                System.out.print(smezhMatrix[i][j] + " - ");
            }
            System.out.println();
        }
    }

    //--------------------------------------------------------------------------------------
    void DFS(int startVertexIndex) { //Обход графа в глубину с выбранной вершины
        Stack verStack = new Stack();
        verStack.add(vertexList[startVertexIndex]);
        vertexList[startVertexIndex].wasVisited = true;
        System.out.println("зашли в вершину " + vertexList[startVertexIndex].getStringValue() + " с ключом " + vertexList[startVertexIndex].getIntValue());

        while (verStack.numVertexs != 0) {
            int vertexIndex = getNotMeetedVetrexIndex(verStack.peek().index);
            if (vertexIndex == -1) {
                verStack.pop();
            } else {
                vertexList[vertexIndex].wasVisited = true;
                System.out.println("зашли в вершину " + vertexList[vertexIndex].getStringValue() + " с ключом " + vertexList[vertexIndex].getIntValue());
                verStack.add(vertexList[vertexIndex]);
            }
        }
        //После обхода надо обнулить флаги посещения для всех вершин
        for (int i = 0; i < numVertex; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    //--------------------------------------------------------------------------------------
    int getNotMeetedVetrexIndex(int startVertex) {
        for (int i = 0; i < numVertex; i++) { //Ищем смежную с текущей вершиной,  не посещенную ранее вершину
            if (smezhMatrix[startVertex][i] != INFINITE && !vertexList[i].wasVisited) { //Если есть смежная с текущей и не посещенная ранее
                return i;
            }
        }
        return -1; //непосещенных смежных со startVertex вершин не осталось
    }

    //--------------------------------------------------------------------------------------
    void BFS(int startVertexIndex) {
        Queue verQueue = new Queue();
        verQueue.add(vertexList[startVertexIndex]);
        vertexList[startVertexIndex].wasVisited = true;
        System.out.println("зашли в вершину " + vertexList[startVertexIndex].getStringValue() + " с ключом " + vertexList[startVertexIndex].getIntValue());
        int meetedVertexIndex;
        while (verQueue.numVertexs != 0) {
            int vertexIndex = verQueue.pop().index;
            while ((meetedVertexIndex = getNotMeetedVetrexIndex(vertexIndex)) != -1) {
                vertexList[meetedVertexIndex].wasVisited = true;
                System.out.println("зашли в вершину " + vertexList[meetedVertexIndex].getStringValue() + " с ключом " + vertexList[meetedVertexIndex].getIntValue());
                verQueue.add(vertexList[meetedVertexIndex]);
            }
        }
        for (int i = 0; i < numVertex; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    //--------------------------------------------------------------------------------------
    void getMSTTree(int startVertexIndex) { //MST древо во взвешенных ненаправленных графах должно учитывать вес ребер и в итоге сумма веса ребер должна быть минимально возможной
        Stack verStack = new Stack();
        edgePriorityQueue edgeQueue = new edgePriorityQueue();//отсортированная очередь для ребер (в порядке возрастания веса)
        verStack.add(vertexList[startVertexIndex]);
        vertexList[startVertexIndex].isInMSTTree = true;
        System.out.println("Ключили в MST древо вершину " + vertexList[startVertexIndex].getStringValue());
        Vertex currVertex;
        while (verStack.numVertexs < numVertex) { //Пока не включили все вершины в MST древо
            currVertex = verStack.peek();
            int vertexIndex;
            for (int i = 0; i < numVertex; i++) { //вставляем в очередь ребра , смежные с currVert
                //посещаем их и заносим стоимость ребер в очередь
                if (i == currVertex.index) continue;
                if (vertexList[i].isInMSTTree) continue; //пропускаем , если уже в древе
                if (smezhMatrix[currVertex.index][i] < INFINITE) { //если ребро существует, то заносим
                    edgeQueue.insert(currVertex.index, i, smezhMatrix[currVertex.index][i]);
                }
            }
            //на вершине очереди ребро с наименьшей стоимостью, заносим его в MST древо и вершину к которой оно ведет тоже
            verStack.add(vertexList[edgeQueue.peek().toVertexIndex]);
            vertexList[edgeQueue.peek().toVertexIndex].isInMSTTree = true;
            System.out.println("Ключили в MST древо вершину " + vertexList[edgeQueue.peek().toVertexIndex].getStringValue());
            System.out.println("Построено ребро: " + vertexList[currVertex.index].getStringValue() + " ----> " + vertexList[edgeQueue.peek().toVertexIndex].getStringValue() + " вес:" + edgeQueue.peek().edgeWeight);
            //Теперь исключим из очереди все ребра, которые ведут к только что добавленной вершине
            edgeQueue.deleteAllEdgesWithTargetVertex(edgeQueue.peek().toVertexIndex);
        }
        //После построения древа, обнулим флаги
        for (int i = 0; i < numVertex; i++) {
            vertexList[i].isInMSTTree = false;
        }
    }

    //--------------------------------------------------------------------------------------
    void deikstraAlgorithm(int startVertexIndex) {
        _Path[] fastestPaths = new _Path[numVertex];
        for (int i = 0; i < numVertex; i++) { //вначале заносим в каждый путь начальную вершину и помечаем все стоимости как недостижимые
            fastestPaths[i] = new _Path(startVertexIndex, i, INFINITE); //TODO надо убрать(или не исп.) первый путь (от А до А)
        }
        int numVertexInPaths = 1; //количество вершин, уже включенных в массив путей //стартовая уже в нем
        fastestPaths[startVertexIndex].pathCost = 0; //цена этого пути 0 (от А до А)
        vertexList[startVertexIndex].isInFastTree = true; //внесли в массив путей
        while (numVertexInPaths < numVertex) { //пока не включим все вершины в массив путей
            int minVertexIndex = getMinIndex(fastestPaths);//получаем индекс непосещенной вершины до кторой в данный момент самый близкий путь
            int minCost = fastestPaths[minVertexIndex].pathCost;
            if (minCost == INFINITE) { //если нет путей или все вершины уже в древе, мы закончили
                break;
            }
            int startToMinVertexCost = fastestPaths[minVertexIndex].pathCost;
            vertexList[minVertexIndex].isInFastTree = true; //включаем новую посещенную вершину
            numVertexInPaths++;

            //Теперь надо с учетом нововключенной вершины обновить пути до всех вершин и если найдены более короткие, заменить ими старые
            int vertexIndex = 1; //идекс вершин для обновления путей(первая пропущена)
            while (vertexIndex < numVertex) {
                if (vertexList[vertexIndex].isInFastTree) { //если текущая вершина уже посещена и закреплена в массиве минимальных путей, пропускаем
                    vertexIndex++;
                    continue;
                }
                int minToVertexIndexCost = smezhMatrix[minVertexIndex][vertexIndex]; //смотрим вес от добавленной в пути вершины до остальных
                int costToVertexIndex = startToMinVertexCost + minToVertexIndexCost; //суммируем вес пути он начальной вершины до минимальной + вес от минимальной до текущей
                int vertexIndexPathCost = fastestPaths[vertexIndex].pathCost; //смотрим текущий вес всего пути в таблице, если он больше только что найденного => меняем на только что найденный
                if (costToVertexIndex < vertexIndexPathCost) {
                    fastestPaths[vertexIndex].parentVertexIndex = minVertexIndex; //ставим ей в предудыщую вершину только что добавленную в пути
                    fastestPaths[vertexIndex].pathCost = costToVertexIndex; //обновляем стоимость
                }
                vertexIndex++;
            }
        }

        displayFastestPaths(fastestPaths);

        for (int i = 0; i < numVertex; i++) {
            vertexList[i].isInFastTree = false;
        }
    }

    int getMinIndex(_Path[] paths) { //поиск в массиве путей элемента с наименьшим весом
        int minCost = INFINITE;
        int minIndex = 0;
        for (int i = 0; i < numVertex; i++) {
            if (!vertexList[i].isInFastTree && paths[i].pathCost < minCost) {
                minCost = paths[i].pathCost;
                minIndex = i;


            }
        }
        return minIndex;
    }

    void displayFastestPaths(_Path[] fastestsPaths) {
        for (int i = 0; i < numVertex; i++) {
            System.out.println(vertexList[i].getStringValue() + " --> ");
            if (fastestsPaths[i].pathCost == INFINITE) {
                System.out.print("-x-"); //пути нет
            } else {
                System.out.print(fastestsPaths[i].pathCost);
                System.out.print("(" + vertexList[fastestsPaths[i].parentVertexIndex].getStringValue() + ")");
            }
            System.out.println();
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
class _Path{
    int parentVertexIndex; //держит в себе индекс вершины, в которую надо зайти,чтобы по кратчайшему пути попасть в конечную
    int endVertexIndex;
    int pathCost;


    _Path(int parentVertexIndex, int endVertexIndex, int pathCost){
        this.parentVertexIndex = parentVertexIndex;
        this.endVertexIndex = endVertexIndex;
        this.pathCost = pathCost;
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////////////
class Vertex{
    private String StringValue;
    private int intValue;
    int index; //Это поле присваивается самим графом при добавлении каждой вершины
    boolean wasVisited; // используется для обходов
    boolean isInMSTTree; //используется для построения MST древа
    boolean isInFastTree; //для алгоритма дейсктры

    public String getStringValue() {
        return StringValue;
    }

    public void setCharValue(String StringValue) {
        this.StringValue = StringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    Vertex(String StringValue,int intValue){
        this.StringValue = StringValue;
        this.intValue = intValue;
    }

    void show(){
        System.out.print("["+StringValue+","+intValue+"]");
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
class Stack{ //FILO
    int numVertexs;
    int index = -1;
    Vertex []vertexs = new Vertex[30];

    void add(Vertex vertex){
        if(numVertexs == 30){
            return;
        }
        vertexs[++index] = vertex;
        numVertexs++;
    }

    Vertex pop(){
        if(numVertexs == 0){
            return null;
        }
        numVertexs--;
        return vertexs[index--];
    }

    Vertex peek(){
        if(numVertexs == 0){
            return null;
        }
        return vertexs[index];
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////
class Queue{ //FIFO
    int numVertexs;
    int addIndex = -1;
    int popIndex = 0;
    Vertex []vertexs = new Vertex[30];

    void add(Vertex vertex){
        if(addIndex == vertexs.length -1){
            addIndex = -1;
        }
        vertexs[++addIndex] = vertex;
        numVertexs++;
    }

    Vertex pop(){
        Vertex tmp = vertexs[popIndex++];
        if(popIndex == vertexs.length){
            popIndex = 0;
        }
        numVertexs--;
        return tmp;
    }

    Vertex peek(){
        return vertexs[popIndex];
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////
class edgePriorityQueue{ //приоритетная очередь ребер для построения MST древа
    private edgeLink first;

    edgePriorityQueue(){
        first = null;
    }
    //-------------------------------------------------------------------------------------
    edgeLink insert(int fromVertexIndex,int toVertexIndex,int edgeWeight){
        if(first == null){
            first = new edgeLink(fromVertexIndex,toVertexIndex,edgeWeight);
            return first;
        }
        edgeLink prev = null;
        edgeLink curr = first;
        while(curr !=null && edgeWeight > curr.edgeWeight){
            prev = curr;
            curr = curr.next;
        }
        if(prev == null){ //insert in the begining of queue
            first = new edgeLink(fromVertexIndex,toVertexIndex,edgeWeight);
            first.next = curr;
            return first;
        }else{
            prev.next = new edgeLink(fromVertexIndex,toVertexIndex,edgeWeight);
            prev.next.next = curr;
            return prev.next;
        }

    }

    //-------------------------------------------------------------------------------------
    edgeLink pop(){
        if(first == null){
            return null;
        }
        edgeLink tmp = first;
        first=first.next;
        return tmp;
    }

    //-------------------------------------------------------------------------------------
    edgeLink peek(){
        if(first == null){
            return null;
        }
        return first;
    }

    //-------------------------------------------------------------------------------------
    void deleteAllEdgesWithTargetVertex(int targetVertexIndex){
        edgeLink prev = null;
        edgeLink curr = first;
        while(curr != null){
            if(curr.toVertexIndex == targetVertexIndex){ //если нашли ребро, ведущее к выбранной вершине
                if(curr == first){//если удаляем первое ребро в очереди
                    first = curr.next;
                }else //если не первое
                    prev.next = curr.next;
            }
            prev = curr;
            curr = curr.next;
        }
    }
    //-------------------------------------------------------------------------------------
    void showAllElementsInPriorityOrder(){
        edgeLink curr = first;
        while(curr !=null){
            System.out.print("["+curr.fromVertexIndex+","+curr.toVertexIndex+","+curr.edgeWeight+"] ");
            curr=curr.next;
        }
    }


}

//////////////////////////////////////////////////////////////////////////////////
class edgeLink{//использую для помещения ребер в приоритетную очередь при построении MST древа
    int fromVertexIndex;
    int toVertexIndex;
    int edgeWeight;

    edgeLink next;

    edgeLink(int fromVertexIndex,int toVertexIndex,int edgeWeight){
        this.fromVertexIndex = fromVertexIndex;
        this.toVertexIndex = toVertexIndex;
        this.edgeWeight = edgeWeight;
    }
}