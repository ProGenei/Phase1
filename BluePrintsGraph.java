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
package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.Edge;
import GraphFramework.Vertex;

//subclass of graph which is used by the main
public class BluePrintsGraph extends Graph {

    //constructors
    public BluePrintsGraph() {
    }

    public BluePrintsGraph(int verticesNo, int edgeNo, boolean isDigraph) {
        super(verticesNo, edgeNo, isDigraph);
    }

    public void displayResultingKruskal(Edge[] MSTResultList) {

        for (int i = 0; i < MSTResultList.length; i++) {
            Line line = (Line) MSTResultList[i];
            line.setILength(line.getWeight());
            Office office1 = (Office) line.getSource();
            office1.displayInfo(line.getSource().getLabel());
            System.out.print(" - ");
            Office office2 = (Office) line.getTarget();
            office2.displayInfo(line.getTarget().getLabel());
            line.displayInfo();

        }
    }

    public void displayResultingMHPrim(Edge[] MSTResultList) {
        for (int i = 0; i < MSTResultList.length; i++) {
            if (MSTResultList[i].getParent().getLabel() != -1) {
                Line line = (Line) MSTResultList[i];
                line.setILength(line.getWeight());
                System.out.println("Office No. " + (char) (MSTResultList[i].getParent().getLabel() + 65)
                        + " - Office No. " + (char) (MSTResultList[i].getSource().getLabel() + 65) + " : line length: "
                        + line.getILength());
            }

        }
    }

    @Override
    public Line createEdge(Vertex source, Vertex target, int weight) {
        return new Line(source, target, weight);
    }

    @Override
    public Office createVertex() {
        return new Office();
    }

}
