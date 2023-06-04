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

import java.util.LinkedList;

//vertex class
public abstract class Vertex {
   //attributes
    public String label;
    boolean isVisited;
    protected LinkedList<Edge> adjList; //each vertex will have list of edges for it

    //constructors
    public Vertex() {
        adjList = new LinkedList<Edge>() ;
    }

    public Vertex(int label) {
        this.label = "O" + label;
        adjList = new LinkedList<Edge>() ;     
    }

    //setter and getter methods
    public abstract void setLabel(int label) ;
   
    public abstract int getLabel();
    
    //display method which can be overriden by the subclass
    public void displayInfo(int label){
        
    }
 }
