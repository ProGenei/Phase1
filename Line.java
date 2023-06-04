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

import GraphFramework.Vertex;
import GraphFramework.Edge;

//subclass of edge
public class Line extends Edge{

    private Integer ILength; //attribute length which will be multiplied with 5

    //constructors
    public Line() {
    }

    public Line(Vertex source, Vertex target, int weight) {
        super(source, target, weight);
    }

    //setter and getter methods
    public Integer getILength() {
        return ILength;
    }

    public void setILength(Integer ILength) {
        this.ILength = ILength * 5;
    }

    @Override
    public void displayInfo(){
        System.out.println(" : line length: " + ILength);
    }
}
