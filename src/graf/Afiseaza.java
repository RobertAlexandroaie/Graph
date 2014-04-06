/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

/**
 *
 * @author Robert
 */
public class Afiseaza
        implements Actiune {

    @Override
    public void executa(Vertex vertex) {
        System.out.println("("+vertex.getID()+", "+vertex.getLabel()+")");
    }
}
