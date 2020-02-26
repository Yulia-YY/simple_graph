import java.util.List;

import graph.Graph;
import org.junit.Assert;
import org.junit.Test;
public class test {

    @Test
    public void test() {
        Graph<String> graph = createGraph();
        List<Graph<String>.Edge> path = graph.getPath("A", "G");
        String from = "A";
        String to = "G";
        Assert.assertEquals("From " + from + " to " + to, null, path);
    }

    private Graph<String> createGraph() {
        Graph<String> graph = new Graph<>();
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
