/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

/**
 *
 * @author Robert
 */
public interface Parcurgere {
    public void parcurge(Vertex startVertex, int[] visited, AbstractGraph graph, Actiune actiune);
}
