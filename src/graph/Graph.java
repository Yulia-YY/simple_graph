package graph;

import java.util.*;

public class Graph <T> {

    private Map<T, Set<Edge>> edgesFromVertex = new HashMap<>();

    public void addVertex(T vertex) {
        checkVertexNotNull(vertex);
        if (!edgesFromVertex.containsKey(vertex)) {
            edgesFromVertex.put(vertex, new HashSet<>());
        }
    }

    public void addEdge(T vertexFrom, T vertexTo) {
        addVertex(vertexFrom);
        addVertex(vertexTo);
        edgesFromVertex.get(vertexFrom).add(new Edge(vertexFrom, vertexTo));
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

    private boolean canReach(T vertexFrom, T vertexTo, List<Edge> path, List<Edge> visited) {
        for (Edge edge : edgesFromVertex.get(vertexFrom)) {
            if (visited.contains(edge)) {
                return false;
            }
            visited.add(edge);
            final T nextVertex = edge.vertexTo;
            if (nextVertex.equals(vertexTo) || canReach(nextVertex, vertexTo, path, visited)) {
                path.add(edge);
                return true;
            }
        }
        return false;
    }

    private void checkVertex(T vertex) {
        checkVertexNotNull(vertex);
        if (!edgesFromVertex.containsKey(vertex)) {
            throw new IllegalArgumentException("The graph doesn't contain vertex: " + vertex.toString());
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(
                edgesFromVertex.isEmpty() ? "Empty graph" : "Graph of: "
        );
        Set<T> vertices = new HashSet<>(edgesFromVertex.keySet());
        Set<Edge> edges = new HashSet<>();
        for (Set<Edge> edgesFromVertex : edgesFromVertex.values()) {
            edges.addAll(edgesFromVertex);
            for (Edge edge : edges) {
                vertices.remove(edge.vertexTo);
                vertices.remove(edge.vertexFrom);
            }
        }
        builder.append(edges);
        builder.append(vertices);
        return builder.toString();
    }


    public class Edge {
        private final T vertexFrom;
        private final T vertexTo;

        Edge(T vertexFrom, T vertexTo) {
            checkVertexNotNull(vertexFrom);
            checkVertexNotNull(vertexTo);
            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
        }

        @Override
        public String toString() {
            return vertexFrom.toString() + " - " + vertexTo.toString();
        }

        @Override
        public int hashCode() {
            return vertexFrom.hashCode() * 32 + vertexTo.hashCode() * 84;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof Graph.Edge && object.hashCode() == hashCode()) {
                Edge other = (Edge) object;
                return this.vertexFrom.equals(other.vertexFrom) && this.vertexTo.equals(other.vertexTo);
            }
            return false;
        }
    }
}
