package graph;

import java.util.*;

public class DirectedGraph<T> implements Graph<T> {

    Map<T, Set<Edge>> edgesFromVertex = new HashMap<>();

    @Override
    public void addVertex(T vertex) {
        checkVertexNotNull(vertex);
        if (!edgesFromVertex.containsKey(vertex)) {
            edgesFromVertex.put(vertex, new HashSet<>());
        }
    }

    @Override
    public void addEdge(T vertexFrom, T vertexTo) {
        addVertex(vertexFrom);
        addVertex(vertexTo);
        edgesFromVertex.get(vertexFrom).add(new Edge<>(vertexFrom, vertexTo));
    }

    public List<Edge> getPath(T vertexFrom, T vertexTo) {
        checkVertex(vertexFrom);
        checkVertex(vertexTo);

        List<Edge> result = new LinkedList<>();
        List<Edge> visited = new ArrayList<>();

        canReach(vertexFrom, vertexTo, result, visited);
        return result;
    }

    private void checkVertexNotNull(T vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex cannot be null");
        }
    }

    private boolean canReach(T currentVertex, T aimVertex, List<Edge> path, List<Edge> visited) {
        for (Edge edge : edgesFromVertex.get(currentVertex)) {
            if (visited.contains(edge)) {
                continue;
            }
            visited.add(edge);
            final T nextVertex = (T) getNextVertex(currentVertex, edge);
            if (nextVertex.equals(aimVertex) || canReach(nextVertex, aimVertex, path, visited)) {
                path.add(0, edge);
                return true;
            }
        }
        return false;
    }

    protected T getNextVertex(T currentVertex, Edge<T> edge) {
        return edge.getVertexTo();
    }

    private void checkVertex(T vertex) {
        checkVertexNotNull(vertex);
        if (!edgesFromVertex.containsKey(vertex)) {
            throw new IllegalArgumentException("The graph doesn't contain vertex: " + vertex.toString());
        }
    }

    protected String graphType() {
        return "Directed graph";
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                edgesFromVertex.isEmpty() ? "Empty " + graphType() : graphType() + " of: "
        );
        Set<T> vertices = new HashSet<>(edgesFromVertex.keySet());
        Set<Edge> edges = new HashSet<>();
        for (Set<Edge> edgesFromVertex : edgesFromVertex.values()) {
            edges.addAll(edgesFromVertex);
            for (Edge edge : edgesFromVertex) {
                vertices.remove(edge.getVertexTo());
                vertices.remove(edge.getVertexFrom());
            }
        }
        builder.append(edges);
        builder.append(vertices);
        return builder.toString();
    }
}
