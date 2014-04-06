/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class ListGraph
        extends AbstractGraph {

    private ArrayList<ArrayList<Vertex>> adjacencyList;

    ListGraph(String numeFisierCitire) {
        setVertexNumber(numeFisierCitire);

        adjacencyList = new ArrayList<>(vertexNumber);
        int it;
        for (it = 0; it < vertexNumber; it++) {
            adjacencyList.add(new ArrayList<Vertex>());
        }

        read(numeFisierCitire);
    }

    @Override
    public boolean hasEdge(Edge edge) {
        for (Vertex it : adjacencyList.get(edge.getVertex1() - 1)) {
            if (it.getID() == edge.getVertex2()) {
                return true;
            }
        }

        for (Vertex it : adjacencyList.get(edge.getVertex2() - 1)) {
            if (it.getID() == edge.getVertex1()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(Edge edge) {
        getAdjacencyList().get(edge.getVertex1() - 1).add(vertexes.get(edge.getVertex2() - 1));
        getAdjacencyList().get(edge.getVertex2() - 1).add(vertexes.get(edge.getVertex1() - 1));
    }

    @Override
    public void deleteEdge(Edge edge) {
        if (hasEdge(edge)) {
            ArrayList<Vertex> listaDeParcurs = new ArrayList<>(adjacencyList.get(edge.getVertex1() - 1));
            for (Vertex it : listaDeParcurs) {
                if (it.getID() == edge.getVertex2()) {
                    adjacencyList.get(edge.getVertex1() - 1).remove(it);
                }
            }


            listaDeParcurs = new ArrayList(adjacencyList.get(edge.getVertex2() - 1));
            for (Vertex it : listaDeParcurs) {
                if (it.getID() == edge.getVertex1()) {
                    adjacencyList.get(edge.getVertex2() - 1).remove(it);
                }
            }

            ArrayList<Edge> backupEdgeLabels = new ArrayList<>(edges);
            for (Edge it : backupEdgeLabels) {
                if ((it.getVertex1()==edge.getVertex1() && it.getVertex2()==edge.getVertex2())
                        ||(it.getVertex2()==edge.getVertex1() && it.getVertex1()==edge.getVertex2())) {
                    edges.remove(edges.indexOf(it));
                }
            }
        }
    }

    @Override
    public void addVertex(String label) {
        int it;
        vertexNumber++;
        vertexes.add(new Vertex(vertexNumber, label));

        getAdjacencyList().add(new ArrayList<Vertex>());
    }

    @Override
    public void deleteVertex(int vertex) {
        int it;
        for (it = 1; it <= vertexNumber; it++) {
            if (it != vertex) {
                if (hasEdge(new Edge(vertex, it, ""))) {
                    deleteEdge(new Edge(vertex, it, ""));
                }
            }
        }

        vertexNumber--;

        Stack<String> labelStack = new Stack<String>();
        int contorVertex = vertex;
        int auxVertexNumber = vertexNumber;
        while (auxVertexNumber >= vertex) {
            labelStack.push(vertexes.get(auxVertexNumber).getLabel());
            vertexes.remove(auxVertexNumber--);
        }
        vertexes.remove(auxVertexNumber);
        while (contorVertex <= vertexNumber) {
            vertexes.add(new Vertex(contorVertex++, labelStack.pop()));
        }
    }

    @Override
    public void write(String numeFisierScriere) {
        FileWriter fout = null;
        try {
            fout = new FileWriter(numeFisierScriere);
            for (Vertex it : vertexes) {
                String line = new String(it.getID() + " " + it.getLabel() + System.getProperty("line.separator").toString());
                fout.write(line);
            }
            fout.write("#" + System.getProperty("line.separator").toString());

            int vertex1;
            int vertex2;
            for (vertex1 = 0; vertex1 < vertexNumber; vertex1++) {
                for (vertex2 = vertex1 + 1; vertex2 < vertexNumber; vertex2++) {
                    if (hasEdge(new Edge(vertex1 + 1, vertex2 + 1, ""))) {
                        String writeLine;
                        String edgeLabel = getEdgeLabel(new Edge(vertex1 + 1, vertex2 + 1, ""));
                        if (edgeLabel != null) {
                            writeLine = new String((vertex1 + 1) + " " + (vertex2 + 1) + " " + edgeLabel + System.getProperty("line.separator").toString());
                        } else {
                            writeLine = new String((vertex1 + 1) + " " + (vertex2 + 1) + System.getProperty("line.separator").toString());
                        }
                        fout.write(writeLine);
                    }
                }
            }
        } catch (IOException ex) {
            System.err.println("Nu se poate folosi \"" + numeFisierScriere + "\" pentru scriere!");
            Logger
                    .getLogger(MatrixGraph.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.err.println("Ceva nu e ok!");
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException ex) {
                System.err.println("Nu se poate inchide \"" + numeFisierScriere + "\"!");
                Logger
                        .getLogger(MatrixGraph.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the adjacencyList
     */
    public ArrayList<ArrayList<Vertex>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * @param adjacencyList the adjacencyList to set
     */
    public void setAdjacencyList(ArrayList<ArrayList<Vertex>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    @Override
    public ArrayList<Vertex> getNeighbourhood(int vertex) {
        return adjacencyList.get(vertex - 1);
    }
}
