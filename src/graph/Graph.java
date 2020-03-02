package graph;

import java.util.List;

/**
 * An object that represents a graph. Contains vertices and edges that connect then.
 *
 * @param <T> type of vertices of a graph
 */
public interface Graph<T> {
    /**
     * Adds a vertex to a graph.
     * Returns {@code true} if a vertex is added to the graph and
     * {@code false} if it already exists and graph stayed unchanged.
     *
     * @param vertex to be added
     * @return {@code true} if a vertex is added to the graph
     * @throws IllegalArgumentException if vertex is {@code null}
     */
    boolean addVertex(T vertex);

    /**
     * Adds an edge to a graph. If any of vertices is not in the graph, it is added.
     * Returns {@code true} if an edge is added to the graph and
     * {@code false} if it already exists and graph stayed unchanged.
     *
     * @param vertexFrom vertex to be added
     * @param vertexTo to be added
     * @return {@code true} if an edge is added to the graph
     * @throws IllegalArgumentException if any of param is {@code null}
     */
    boolean addEdge(T vertexFrom, T vertexTo);

    /**
     * Returns first found path between the specified vertices.
     *
     * @param vertexFrom vertex from which the path is searched
     * @param vertexTo vertex to which the path is searched
     * @return list of edges that connects specified vertices
     * @throws IllegalArgumentException if any of specified vertices is not in the graph or {@code null}
     */
    List<Graph.Edge> getPath(T vertexFrom, T vertexTo);

    /**
     * Represents an edge of a graph.
     *
     * @param <T>
     */
    class Edge<T> {
        private final T vertexFrom;
        private final T vertexTo;

        /**
         * gets the first vertex of the edge.
         * @return vertex
         */
        public T getVertexFrom() {
            return vertexFrom;
        }

        /**
         * gets the second vertex of the edge.
         * @return vertex
         */
        public T getVertexTo() {
            return vertexTo;
        }

        /**
         * Creates an edge.
         *
         * @param vertexFrom first vertex
         * @param vertexTo second vertex
         * @throws IllegalArgumentException if any of params id {@code null}
         */
        public Edge(T vertexFrom, T vertexTo) {
            checkVertexNotNull(vertexFrom);
            checkVertexNotNull(vertexTo);
            this.vertexFrom = vertexFrom;
            this.vertexTo = vertexTo;
        }

        private void checkVertexNotNull(T vertex) {
            if (vertex == null) {
                throw new IllegalArgumentException("Vertex cannot be null");
            }
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
            if (object instanceof DirectedGraph.Edge && object.hashCode() == hashCode()) {
                Graph.Edge other = (Graph.Edge) object;
                return this.vertexFrom.equals(other.vertexFrom) && this.vertexTo.equals(other.vertexTo);
            }
            return false;
        }
    }
}
