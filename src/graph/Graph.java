package graph;

import java.util.List;

public interface Graph<T> {
     void addVertex(T vertex);

     void addEdge(T vertexFrom, T vertexTo);

     List<Graph.Edge> getPath(T vertexFrom, T vertexTo);

     class Edge<T> {
        private final T vertexFrom;
        private final T vertexTo;

        T getVertexFrom() {
            return vertexFrom;
        }

        T getVertexTo() {
            return vertexTo;
        }

        Edge(T vertexFrom, T vertexTo) {
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
