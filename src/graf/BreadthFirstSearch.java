/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

import java.util.ArrayList;

/**
 *
 * @author Robert
 */
public class BreadthFirstSearch
        implements Parcurgere {

    @Override
    public void parcurge(Vertex startVertex, int[] visited, AbstractGraph graph, Actiune actiune) {
        ArrayList<Vertex> coada = new ArrayList<>();
        coada.add(startVertex);
        actiune.executa(startVertex);
        visited[startVertex.getID() - 1] = 1;

        Vertex bfsVertex;
        while (!coada.isEmpty()) {
            bfsVertex = coada.get(0);
            coada.remove(0);

            ArrayList<Vertex> neighbourhood = graph.getNeighbourhood(bfsVertex.getID());

            if (neighbourhood != null) {
                for (Vertex it : neighbourhood) {
                    if (visited[it.getID() - 1] == 0) {
                        actiune.executa(it);
                        visited[it.getID() - 1] = 1;
                        coada.add(it);
                    }
                }
            }
        }
    }
}
