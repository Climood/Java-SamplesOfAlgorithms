public class runMe {
    public static void main(String...args){
        orientedGraph graph = new orientedGraph();
        graph.addVertex("A",0);
        graph.addVertex("B",1);
        graph.addVertex("C",2);
        graph.addVertex("D",3);
        graph.addVertex("E",4);
        graph.addEdge(1,0);
        graph.addEdge(1,4);
        graph.addEdge(4,2);
        graph.addEdge(3,4);
        graph.addEdge(0,2);
        graph.getTableOfSvyznosti();
        graph.UorhsellAlgorithm(); //чтобы понять, что возможен переход за O(1) просто смотрим есть ли ребро от начальной вершины до конечной в модифицированной матрице
    }
}
