package graph;

public class UndirectedGraph<T> extends DirectedGraph<T> implements Graph<T> {

    @Override
    public void addEdge(T vertexFrom, T vertexTo) {
        addVertex(vertexFrom);
        addVertex(vertexTo);
        Edge edge = new Edge<>(vertexFrom, vertexTo);
        edgesFromVertex.get(vertexFrom).add(edge);
        edgesFromVertex.get(vertexTo).add(edge);
    }
}
