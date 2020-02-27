import java.util.List;

import graph.*;
import org.junit.Assert;
import org.junit.Test;
public class test {

    @Test
    public void testGetPathUndirectedGraph() {
        Graph<String> graph = createUndirectedGraph();
        String from = "A";
        String to = "G";
        List<Graph.Edge> path = graph.getPath(from, to);
        List<Graph.Edge> pathExpected = getSimpleGraph("A", "B", "G").getPath("A", "G");

        Assert.assertEquals("From " + from + " to " + to, pathExpected, path);
    }

    @Test
    public void testGetPathDirectedGraph() {
        Graph<String> graph = createDirectedGraph();
        String from = "A";
        String to = "G";
        List<Graph.Edge> path = graph.getPath(from, to);
        List<Graph.Edge> pathExpected = getSimpleGraph("A", "D", "G").getPath(from, to);

        Assert.assertEquals("From " + from + " to " + to, pathExpected, path);
    }


    private Graph<String> getSimpleGraph(String... vertex) {
        DirectedGraph<String> graphRes = new DirectedGraph<>();
        for (int i = 0; i < vertex.length - 1; i++) {
            graphRes.addEdge(vertex[i], vertex[i + 1]);
        }
        return graphRes;
    }

    private Graph<String> createDirectedGraph() {
        return addEdgesToGraph(new DirectedGraph<>());
    }

    private Graph<String> createUndirectedGraph() {
        return addEdgesToGraph(new UndirectedGraph<>());
    }

    private Graph<String> addEdgesToGraph(Graph<String> graph) {
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("B", "D");
        graph.addEdge("A", "C");
        graph.addEdge("C", "E");
        graph.addEdge("D", "F");
        graph.addEdge("D", "G");
        graph.addEdge("D", "D");
        graph.addVertex("K");
        return graph;
    }
}
