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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//class to create a graph as an adjacency list
public abstract class Graph {
    //create variables
    int verticesNo; //number of vertices of the graph
    int edgeNo; //number of edges of the graph
    boolean isDigraph;
    Vertex[] vertices; // list/vector representing the list of vertices of a graph

    public Graph() {
    }

    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        vertices = new Vertex[verticesNo];
    }

    //method to make randomised graph given number of vertex and edge
    public void makeGraph() {

        //create vertex object for all vertices in the graph
        for (int i = 0; i < verticesNo; i++) {
            vertices[i] = this.createVertex();
            vertices[i].setLabel(i);
        }

        //for connected graph
        for (int i = 1; i < verticesNo; i++) {
            int randomWeight = (int) (1 + Math.random() * 100); //first generate random wight to assign it to the edge 
            addEdge(vertices[i - 1], vertices[i], randomWeight); //then add that edge graph
        }

        int totalNumberEdge = edgeNo - (verticesNo - 1);
        
        //create edges 
        int i = 0; //edges counter
        while (i < totalNumberEdge) {
            //randomly define a source 
            int sourceLabel = (int) (Math.random() * (verticesNo));
            //randomly define a target 
            int targetLabel = (int) (Math.random() * (verticesNo));
            //if source and target label are equal then continue
            if (sourceLabel == targetLabel) {
                continue;
            }
            Vertex sourceV = vertices[sourceLabel];
            Vertex sourceT = vertices[targetLabel];
            //if undirected check only source because target will have the same edge
            if (isRepeated(sourceLabel, targetLabel)) { 
                continue;
            }
            //if directed check for both target and source 
            if (isDigraph && isRepeated(targetLabel, sourceLabel)) { 
                continue;                                            
            }

            int randomWeight = (int) (1 + Math.random() * 100);
            addEdge(sourceV, sourceT, randomWeight); //add the edge to the graph 
            i++; //increment counter of edges 

        }
    }

    //method to add edge to graph
    public void addEdge(Vertex v, Vertex u, int weight) {
        Edge newEdge1 = this.createEdge(v, u, weight);
        newEdge1.setParent(this.createVertex()); //set the parent with new vertex
        v.adjList.add(newEdge1); //add to adjacency list
        //if it is not a digraph then create another new edge with opposite direction
        if (!isDigraph) {
            Edge newEdge2 = this.createEdge(u, v, weight);
            newEdge2.setParent(this.createVertex());
            u.adjList.add(newEdge2);
            this.edgeNo++; //increment number of edges 
        }
    }

    //method to check if is repeated
    public boolean isRepeated(int SourceId, int targetId) {
        for (int i = 0; i < vertices[SourceId].adjList.size(); i++) {
            if ((vertices[SourceId].adjList.get(i).target.getLabel() == targetId)) {
                return true;
            }
        }
        return false;
    }

    //method to read the edges and vertices from the text file 
    public void readGraphFromFile(File InputFile) throws FileNotFoundException {
        Scanner input1 = new Scanner(InputFile);
        input1.next(); //skip first word
        //read the inputs and save to attributes
        int digraph = input1.nextInt();
        this.isDigraph = (digraph == 1);
        this.verticesNo = input1.nextInt();
        this.edgeNo = input1.nextInt();
        int lineNo = edgeNo;
        vertices = new Vertex[verticesNo];

        for (int i = 0; i < (verticesNo); i++) {
            vertices[i] = this.createVertex();
            vertices[i].setLabel(i);
        }

        for (int i = 0; i < lineNo; i++) {
            char sVertex = input1.next().charAt(0);
            char tVertex = input1.next().charAt(0);
            int edgeW = input1.nextInt();
            addEdge(vertices[sVertex - 65], vertices[tVertex - 65], edgeW); //add edge
        }
    }

    //getter method
    public int getVerticesNo() {
        return verticesNo;
    }

    //abstract methods to create edge and vertex in the subclasses
    public abstract Edge createEdge(Vertex source, Vertex target, int weight);

    public abstract Vertex createVertex();

}
