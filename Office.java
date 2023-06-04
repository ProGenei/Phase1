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
import GraphFramework.Vertex;

//subclass of vertex
public class Office extends Vertex {

    private String vertexName; //attribute name of vertex

    //constructors
    public Office() {
    }

    public Office(int label) {
        super(label);

    }

    //setter and getter methods
    public void setLabel(int label) {
        super.label = "O" +label;
    }
   
    public int getLabel(){
        return Integer.parseInt(this.label.substring(1));
    }
    
    @Override
    public void displayInfo(int label) {
        vertexName = (char) (label + 65) + "";
        System.out.print("Office No. " + vertexName);
    }
}
