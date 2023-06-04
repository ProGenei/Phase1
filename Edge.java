/*
CPCS 324 : Algorithms & Data Structures 2 : Group Project Phase1
Phone Network Design App (Usage of Prim & Kruskal Algorithms)
---------------
Ayatun Ara - 2110295 - B0B
Rayana Bander Aljuaid - 2105972 - B0A
Samaher Ismael Fattani - 1905374 - B0A
Ghadeer Mohammed Nooh - 2006705 - B0A
---------------
*/

package GraphFramework;

//edge of graph which implement the Comparable interface 
public class Edge implements Comparable<Edge> {
    //attributes
    protected Vertex source;
    protected Vertex target;
    protected Vertex parent;
    protected int weight;

    //constructors
    public Edge() {
    }

    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    //compare the edges weight
    @Override
    public int compareTo(Edge o) {
        if (this.weight > o.weight) {
            return 1;
        } else if (this.weight == o.weight) {
            return 0;
        } else {
            return -1;
        }

    }

    //setter and getter methods
    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    //display method which can be overriden by the subclass
    public void displayInfo() {

    }
}
