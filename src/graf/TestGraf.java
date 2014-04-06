/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

/**
 *
 * @author Robert
 */
public class TestGraf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        

        Graph grafTest = new MatrixGraph("graf1.tgf");
        grafTest.addVertex("5");
        grafTest.deleteVertex(2);
        grafTest.deleteEdge(new Edge(1, 2));
        grafTest.write("test.tgf");


        Graph grafTest2 = new ListGraph("graf2.tgf");
        /*
         if (grafTest2.hasEdgeLabel("fdg")) {
            System.out.println("Are");
        } else {
            System.out.println("N-are");
        }
        */
        //grafTest2.deleteEdge("fdg");
        //grafTest2.deleteEdge(new Edge(1, 3));
        //grafTest2.deleteVertex(3);
        grafTest2.write("test2.tgf");

        Graph grafTest3 = new ListGraph("graf2.tgf");
        grafTest3.explore(new Vertex(1, "1"), new BreadthFirstSearch(), new Afiseaza());
        System.out.println();
        DepthFirstSearch parcurgere = new DepthFirstSearch();
        parcurgere.setCaz(DFSType.pre);
        grafTest3.explore(new Vertex(1, "1"), parcurgere, new Afiseaza());
        grafTest3.write("test3.tgf");
    }
}
