package graph;

public class UndirectedGraph<T> extends DirectedGraph<T> implements Graph<T> {

    protected String graphType() {
        return "Undirected graph";
    }

    protected T getNextVertex(T currentVertex, Edge<T> edge) {
        return currentVertex.equals(edge.getVertexFrom()) ? edge.getVertexTo() : edge.getVertexFrom();
    }

    @Override
    public boolean addEdge(T vertexFrom, T vertexTo) {
        addVertex(vertexFrom);
        addVertex(vertexTo);
        Edge edge = new Edge<>(vertexFrom, vertexTo);
        return edgesFromVertex.get(vertexFrom).add(edge) || edgesFromVertex.get(vertexTo).add(edge);
    }
}
