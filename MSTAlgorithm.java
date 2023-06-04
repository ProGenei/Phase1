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

//superclass representing the general characteristics of algorithms for minimum spanning tree
public abstract class MSTAlgorithm {
    Graph graph;
    Edge [] MSTResultList; //result of the MST algorithms 

    //abstract function implemented by the subclasses’ polymorphic functions
    public abstract Edge[] displayResultingMST();
    
}
