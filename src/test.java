import java.util.LinkedList;
import java.util.List;

import graph.*;
import org.junit.Assert;
import org.junit.Test;
public class test {

    @Test
    public void testGetPathUndirectedGraph() {
        testGetPath(addEdgesToGraph(new UndirectedGraph<>()));
    }

    @Test
    public void testGetPathDirectedGraph() {
        testGetPath(addEdgesToGraph(new DirectedGraph<>()));
    }

    private void testGetPath(Graph graph) {
        String from = "A";
        String to = "G";
        List<Graph.Edge> pathExpected = new LinkedList<>();
        pathExpected.add(new Graph.Edge("A", "B"));
        pathExpected.add(new Graph.Edge("B", "G"));
        Assert.assertEquals("From " + from + " to " + to, pathExpected, graph.getPath(from, to));
    }

    private void addEdges(Graph<String> graph, String... vertex) {
        for (int i = 0; i < vertex.length - 1; i++) {
            graph.addEdge(vertex[i], vertex[i + 1]);
        }
    }

    private Graph<String> addEdgesToGraph(Graph<String> graph) {
        addEdges(graph, "A", "C", "E");
        addEdges(graph, "A", "B", "D");
        addEdges(graph, "A", "D");
        addEdges(graph, "D", "F");
        addEdges(graph, "D", "G");
        addEdges(graph, "D", "D");
        graph.addVertex("K");
        return graph;
    }
}
