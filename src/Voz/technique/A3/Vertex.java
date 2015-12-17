package Voz.technique.A3;
public class Vertex implements Comparable<Vertex> {
    public String name;

    public int distance;
    public Vertex predecessor; // previous vertex
    public double centrality;
    public static final int INFINITY = Integer.MAX_VALUE;

    public Vertex(String v)
    {
        name = v;
        distance = INFINITY; // start as infinity away
        predecessor = null;
        centrality = 0.0;
    }

    public String toString()
    {
        return name;
    }

    public int compareTo(Vertex other)
    {
        int diff = distance - other.distance;
        if (diff != 0)
            return diff;
        else
            return name.compareTo(other.name);
    }
}