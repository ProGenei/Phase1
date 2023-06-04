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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//kruskal algorithm class using an adjacency list graph as an input
public class KruskalAlg extends MSTAlgorithm {
    int cost; //cost of MST
    Graph graph;

    public void Kurskal(Graph graph) {
        this.graph = graph;
        int verticesNo = graph.verticesNo;//take the number of vertices from the graph 
        MSTResultList = new Edge[verticesNo - 1];//we already know that we need V-1 edges to fine the MST 

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.verticesNo; i++) {
            Vertex v = graph.vertices[i];
            // Loop through adjacent list of this vertex
            for (int j = 0; j < v.adjList.size(); j++) {
                edges.add(v.adjList.get(j));
            }
        }

        Collections.sort(edges);

        //create an array for subsets 
        Edge[] subsets = new Edge[verticesNo];
        makeSet(subsets);//make sets of each vertex in the graph 
        int MSTedges = 0;//to indicate the number of edges in the graph Et

        while (MSTedges < verticesNo - 1) {
            //  Edge edge = pq.remove(); //dequeue the edge which has the most priority (who has minimum weight )
            Edge edge = edges.remove(0);
            int source_subset = find(subsets, edge.source.getLabel()); //find the subset of the source 
            int target_subset = find(subsets, edge.target.getLabel()); //find the subset of the targe 
            //if they form diffrent subsets that imples this edge will not create a cycle 
            if (source_subset != target_subset) {
                MSTResultList[MSTedges] = edge;//add the edge to the edges result
                cost += edge.weight; //add the cost two 

                MSTedges++;//increment number of edges Et
                union(subsets, source_subset, target_subset);// union the disjoint subset 

            }
        }
    }

    //method used to create singleton set for all the vertices in the graph
    public void makeSet(Edge[] edges) {
        //for all the graph vertices
        //create new set 
        for (int i = 0; i < edges.length; i++) {
            //set the parent and source as a i 
            edges[i] = graph.createEdge(graph.createVertex(),graph.createVertex(),0);
            edges[i].setParent(graph.createVertex());
            edges[i].source.setLabel(i);
            edges[i].parent.setLabel( i);

        }
    }

    //this method used to find the subset containing fixed value
    public int find(Edge[] edges, int vertex) {
        //vertex that need to find its parent(subset)
        //if the parent of the vertic != the same label of vertex 
        if (edges[vertex].parent.getLabel() != vertex) {
            //recursivly call find the set of its parent 
            return find(edges, edges[vertex].parent.getLabel());
        }
        //return the set  
        return vertex; //parent represent the representatives of the subsets
    }

    //union the disjoint subset of source_vertex and target_vertex
    public void union(Edge[] edges, int source_vertex, int target_vertex) {
        //first find the subset of source_vertex and subset of target_vertex 
        int x_set_parent = find(edges, source_vertex);
        int y_set_parent = find(edges, target_vertex);
        //make source_vertex as the target_vertex subset  
        edges[y_set_parent].parent.setLabel(x_set_parent);
    }

    public Edge[] displayResultingMST() {
        return MSTResultList;

    }

    public int getCost() {
        return cost;
    }
}
