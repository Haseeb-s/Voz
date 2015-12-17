package Voz.technique.A3;
import java.util.*;


public class WeightedGraph<T> {
    public static final int NULL_EDGE = 0;
    private static final int DEFCAP = 50;  // default capacity
    private int numVertices;
    private int maxVertices;
    private T[] vertices;
    private double[][] edges;
    private boolean[] marks;  // marks[i] is mark for vertices[i]

    public WeightedGraph()
    {
        numVertices = 0;
        maxVertices = DEFCAP;
        vertices = (T[]) new Object[DEFCAP];
        marks = new boolean[DEFCAP];
        edges = new double[DEFCAP][DEFCAP];
    }

    public WeightedGraph(int maxV)
    {
        numVertices = 0;
        maxVertices = maxV;
        vertices = (T[]) new Object[maxV];
        marks = new boolean[maxV];
        edges = new double[maxV][maxV];
    }

    public void addVertex(T vertex)
    {
        vertices[numVertices] = vertex;
        for (int index = 0; index < numVertices; index++) {
            edges[numVertices][index] = NULL_EDGE;
            edges[index][numVertices] = NULL_EDGE;
        }
        numVertices++;
    }

    private int indexIs(T vertex)
    // Returns the index of vertex in vertices.
    {
        int index = 0;
        while (!vertex.equals(vertices[index]))
            index++;
        return index;
    }

    public void addEdge(T fromVertex, T toVertex, double weight)
    {
        int row;
        int column;

        row = indexIs(fromVertex);
        column = indexIs(toVertex);
        edges[row][column] = weight;
    }

    public double weightIs(T fromVertex, T toVertex)
    {
        int row;
        int column;

        row = indexIs(fromVertex);
        column = indexIs(toVertex);
        return edges[row][column];
    }

    public Queue<T> getToVertices(T vertex)
    // Returns a queue of the vertices that are adjacent from vertex.
    {
        Queue<T> adjVertices = new LinkedList<>();
        int fromIndex;
        int toIndex;
        fromIndex = indexIs(vertex);
        for (toIndex = 0; toIndex < numVertices; toIndex++)
            if (edges[fromIndex][toIndex] != NULL_EDGE)
                adjVertices.add(vertices[toIndex]);
        return adjVertices;
    }

    public void markVertex(T vertex)
    // Sets mark for vertex to true.
    {
        int index;
        index = indexIs(vertex);
        marks[index] = true;
    }

    public boolean isMarked(T vertex) {
        int index;
        index = indexIs(vertex);
        return (marks[index]);
    }

    public void clearMarks() {
        for (int i = 0; i < this.numVertices; i++) {
            marks[i] = false;
        }
    }

    public static boolean isPath(WeightedGraph<String> graph, String startVertex, String endVertex) {
        Deque<String> stack = new ArrayDeque<String>();
        Queue<String> vertexQueue = new LinkedList<String>();

        boolean found = false;
        String vertex;
        String item;
        graph.clearMarks();
        stack.push(startVertex);
        do {
            vertex = stack.peek();
            stack.pop();
            if (vertex == endVertex)
                found = true;
            else {
                if (!graph.isMarked(vertex)) {
                    graph.markVertex(vertex);
                    vertexQueue = graph.getToVertices(vertex);

                    while (!vertexQueue.isEmpty()) {
                        item = vertexQueue.poll();
                        if (!graph.isMarked(item))
                            stack.push(item);
                    }
                }
            }
        } while (!stack.isEmpty() && !found);
        return found;
    }
}
