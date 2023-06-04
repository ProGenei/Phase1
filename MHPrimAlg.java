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

//Min Heap Prim class using an adjacency list graph
public class MHPrimAlg extends MSTAlgorithm {
    //attributes
    int capacity; //capacity of heap
    int currentSize; //current size of heap 
    Edge [] MinHeapArr; //minHeap Array of Edges
    int [] positions; //position of heap element in the Heap
    int cost; //cost of minimum spanning tree
    Graph graph;
    
    //constructors
    public MHPrimAlg() {   
    }

    public MHPrimAlg(int verticesNo, Graph graph){
        this.graph = graph;
        capacity =  verticesNo;
        MinHeapArr = new Edge[capacity + 1]; //initialise with new edges
        positions = new int[capacity]; 
        //first element isn't used
        MinHeapArr[0] = graph.createEdge(graph.createVertex(), graph.createVertex(),Integer.MIN_VALUE); 
        MinHeapArr[0].source.setLabel(-1);
        currentSize = 0; 
      
    }

    //method to find MST
    public void PrimMH(Graph graph){
        MSTResultList= new Edge[capacity];

        //initialize edges of heap 
        for (int i = 0; i < graph.verticesNo; i++) {
            //initialize heap nodes 
            MSTResultList[i] = graph.createEdge(graph.createVertex(), graph.createVertex(),Integer.MIN_VALUE);
            MSTResultList[i].setParent(graph.createVertex());
            MSTResultList[i].getSource().setLabel(i);
            MSTResultList[i].getParent().setLabel(-1);
            MSTResultList[i].setWeight(Integer.MAX_VALUE); //max weight 
           
        }
        //first node weight is 0  
        MSTResultList[0].weight = 0;

        //insert vertices to the minHeap 
        for (int i = 0; i < graph.verticesNo; i++) {
            insert(MSTResultList[i]);
        }

        //while the heap node is not empty here
        while (!isEmpty()) {
            //extract the min node and add weight to cost
            Edge extractedMinNode = extractMin();
            cost+= extractedMinNode.getWeight();
            int extractedVertex = extractedMinNode.getSource().getLabel();
            //flag extracted vertex as visited vertex
            MSTResultList[extractedVertex].getSource().isVisited = true; 

            //initialise edge list with adj list 
            LinkedList<Edge> list = graph.vertices[extractedVertex].adjList;
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //if edge destination is present in heap and is not visited
                if (!MSTResultList[edge.getTarget().getLabel()].getSource().isVisited) {
                    //add to destination and weight
                    int dest = edge.getTarget().getLabel();
                    int newWeight = edge.getWeight();
                    //check if updated key < existing key, if yes, update if
                    if (MSTResultList[dest].getWeight() > newWeight) {
                        decreaseKey(newWeight, dest);//change thee wight of the node 
                        //update the wight and the parent
                        MSTResultList[dest].getParent().label = "O"+extractedVertex;
                        MSTResultList[dest].weight = newWeight;
                       
                        
                    }
                }
            }   
        }   
    }

    //method to put the new value added to the correct position
    public void percolateUp(int Number) {
      
        int parentIndex = Number / 2; //find parent of node 
        int currentIndex = Number;
        //while parent is bigger than child 
        //bubble the child up
        while (currentIndex > 0 && MinHeapArr[parentIndex].weight > MinHeapArr[currentIndex].weight) {
           
            Edge currentNode = MinHeapArr[currentIndex];
            Edge parentNode = MinHeapArr[parentIndex];
            //swap the positions
            positions[currentNode.getSource().getLabel()] = parentIndex;
            positions[parentNode.getSource().getLabel()] = currentIndex;
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    //method to remove the min value from the min heap 
    public void decreaseKey(int newWeight, int vertexKey) {
        //initialise index with vertex key
        int index = positions[vertexKey]; 
        //get the vertex and update its value
        Edge node = MinHeapArr[index];
        node.weight = newWeight; 
        percolateUp(index);
        
    }

    //method to add new node to the minHeap
    public void insert(Edge Node) {
        //increment size of heap and save in index
        currentSize++; 
        int indx = currentSize; 
        //update node in minheap
        MinHeapArr[indx] = Node; 
        positions[Node.getSource().getLabel()] = indx; 
        percolateUp(indx);
        
    }

    //extract the minimum node
    public Edge extractMin() {
        Edge min = MinHeapArr[1]; //root of heap
        Edge lastNode = MinHeapArr[currentSize]; //last node of heap
        //make last element as first
        positions[lastNode.getSource().getLabel()] = 1;
        //replace the first element by the root 
        MinHeapArr[1] = lastNode; 
        //delete the last element
        MinHeapArr[currentSize] = null; 
        sinkDown(1);
        currentSize--;
        return min;  //return the minimum node
    }

    //method to keep heap shape and properties again after deletion 
    public void sinkDown(int num) {
        int smallest = num;
        //initialise left and right child
        int leftIndex = 2 * num;
        int rightIndex = 2 * num + 1;
        //check right and left child
        if (leftIndex < heapSize() && MinHeapArr[smallest].getWeight() > MinHeapArr[leftIndex].getWeight()) {
            smallest = leftIndex;
        }
        if (rightIndex < heapSize() && MinHeapArr[smallest].getWeight() > MinHeapArr[rightIndex].getWeight()) {
            smallest = rightIndex;
        }
        if (smallest != num) {

            Edge smallestNode = MinHeapArr[smallest];
            Edge kNode = MinHeapArr[num];

            //swap the positions of source of smallest node and knode
            positions[smallestNode.getSource().getLabel()] = num;
            positions[kNode.getSource().getLabel()] = smallest;
            swap(num, smallest);
            sinkDown(smallest);
        }
    }

    //method to swap position of two nodes a and b
    public void swap(int a, int b) {
        Edge temp = MinHeapArr[a];
        MinHeapArr[a] = MinHeapArr[b];
        MinHeapArr[b] = temp;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int heapSize() {
            return currentSize;
    }
    
    //method will be overridden
    public Edge[] displayResultingMST() {       
        return MSTResultList;
    }

    public int getCost() {
        return cost;
    }
}




