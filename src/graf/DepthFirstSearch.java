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
public class DepthFirstSearch
        implements Parcurgere {

    private DFSType caz;

    DepthFirstSearch() {
        caz = DFSType.pre;
    }

    @Override
    public void parcurge(Vertex startVertex, int[] visited, AbstractGraph graph, Actiune actiune) {
        ArrayList<Vertex> neighbourhood = graph.getNeighbourhood(startVertex.getID());
        switch (caz) {
            case pre:
                if (startVertex != null && visited[startVertex.getID() - 1] == 0) {
                    actiune.executa(startVertex);
                    visited[startVertex.getID() - 1] = 1;

                    if (neighbourhood != null) {
                        for (Vertex it : neighbourhood) {
                            parcurge(it, visited, graph, actiune);
                        }
                    }
                }
                break;
            case in:
                if (startVertex != null && visited[startVertex.getID() - 1] == 0) {
                    visited[startVertex.getID() - 1] = 1;
                    if (neighbourhood != null) {
                        Vertex firstSon = neighbourhood.get(0);
                        neighbourhood.remove(0);
                        parcurge(firstSon, visited, graph, actiune);

                        actiune.executa(startVertex);
                        visited[startVertex.getID() - 1] = 1;

                        if (neighbourhood != null) {
                            for (Vertex it : neighbourhood) {
                                parcurge(it, visited, graph, actiune);
                            }
                        }
                    }
                }
                break;
            case post:

                if (startVertex != null && visited[startVertex.getID() - 1] == 0) {
                    visited[startVertex.getID() - 1] = 1;

                    if (neighbourhood != null) {
                        for (Vertex it : neighbourhood) {
                            parcurge(it, visited, graph, actiune);
                        }
                    }

                    actiune.executa(startVertex);
                }
                break;
        }
    }

    /**
     * @return the caz
     */
    public DFSType getCaz() {
        return caz;
    }

    /**
     * @param caz the caz to set
     */
    public void setCaz(DFSType caz) {
        this.caz = caz;
    }
}
