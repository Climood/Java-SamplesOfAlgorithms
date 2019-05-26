public class runMe {
    public static void main(String...args){
        orientedWeightGraph graph = new orientedWeightGraph();
        graph.addVertex("A",0);
        graph.addVertex("B",1);
        graph.addVertex("C",2);
        graph.addVertex("D",3);
        graph.addVertex("E",4);
        graph.addEdge(0,1,50);
        graph.addEdge(0,3,80);
        graph.addEdge(1,3,90);
        graph.addEdge(1,2,60);
        graph.addEdge(3,2,20);
        graph.addEdge(3,4,70);
        graph.addEdge(2,4,40);
        graph.addEdge(4,1,50);
        graph.showSmezhMatrix();
        graph.deikstraAlgorithm(0);
    }
}
