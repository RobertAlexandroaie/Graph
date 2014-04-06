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
public class MatrixGraph
        extends AbstractGraph {

    protected String[][] adjacencyMatrix;

    public MatrixGraph(String numeFisierCitire) {
        setVertexNumber(numeFisierCitire);
        adjacencyMatrix = new String[vertexNumber][vertexNumber];

        int line;
        int row;
        for (line = 0; line < vertexNumber; line++) {
            for (row = 0; row < vertexNumber; row++) {
                adjacencyMatrix[line][row] = "";
            }
        }
        read(numeFisierCitire);
    }

    /**
     *
     * @param edge the value of edge
     * @return the boolean
     */
    @Override
    public boolean hasEdge(Edge edge) {
        if (getAdjacencyMatrix()[edge.getVertex1() - 1][edge.getVertex2() - 1].compareTo("") != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param edge the value of edge
     */
    @Override
    public void addEdge(Edge edge) {
        String actualLabel;

        if (edge.getVertex1() - 1 < vertexNumber && edge.getVertex2() - 1 < vertexNumber) {
            getAdjacencyMatrix()[edge.getVertex1() - 1][edge.getVertex2() - 1] = "*";
            getAdjacencyMatrix()[edge.getVertex2() - 1][edge.getVertex1() - 1] = "*";
        }
    }

    @Override
    public void deleteEdge(Edge edge) {
        getAdjacencyMatrix()[edge.getVertex1() - 1][edge.getVertex2() - 1] = "";
        getAdjacencyMatrix()[edge.getVertex2() - 1][edge.getVertex1() - 1] = "";

        ArrayList<Edge> backupEdges = new ArrayList<>(edges);
        for (Edge it : backupEdges) {
            if ((it.getVertex1() == edge.getVertex1() && it.getVertex2() == edge.getVertex2())
                    || (it.getVertex1() == edge.getVertex2() && it.getVertex2() == edge.getVertex1())) {
                edges.remove(backupEdges.indexOf(it));
            }
        }
    }

    @Override
    public void addVertex(String label) {
        vertexNumber++;
        vertexes.add(new Vertex(vertexNumber, label));

        String newAdjacencyMatrix[][] = new String[vertexNumber][vertexNumber];
        int line;
        int row;
        for (line = 0; line < vertexNumber; line++) {
            newAdjacencyMatrix[line] = new String[vertexNumber];
        }
        for (line = 0; line < vertexNumber; line++) {
            for (row = 0; row < vertexNumber; row++) {
                newAdjacencyMatrix[line][row] = "";
            }
        }

        for (line = 0; line < vertexNumber - 1; line++) {
            for (row = 0; row < vertexNumber - 1; row++) {
                newAdjacencyMatrix[line][row] = adjacencyMatrix[line][row];
            }
        }

        setAdjacencyMatrix(newAdjacencyMatrix);
    }

    @Override
    public void deleteVertex(int vertex) {
        
        ArrayList<Edge> backupEdgeLabels = new ArrayList<>(edges);
        for (Edge e : backupEdgeLabels) {
            if (e.getVertex1() == vertex || e.getVertex2() == vertex) {
                edges.remove(backupEdgeLabels.indexOf(e));
            }
        }

        String newAdjacencyMatrix[][] = new String[vertexNumber - 1][vertexNumber - 1];

        int line;
        int row;
        for (line = 0; line < vertexNumber - 1; line++) {
            newAdjacencyMatrix[line] = new String[vertexNumber - 1];
        }
        for (line = 0; line < vertexNumber - 1; line++) {
            for (row = 0; row < vertexNumber - 1; row++) {
                newAdjacencyMatrix[line][row] = "";
            }
        }

        int lineOfNew = 0;
        int rowOfNew = 0;
        for (line = 0; line < vertexNumber; line++) {
            if (line != vertex - 1) {
                for (row = 0; row < vertexNumber; row++) {
                    if (row != vertex - 1) {
                        newAdjacencyMatrix[lineOfNew][rowOfNew++] = adjacencyMatrix[line][row];
                    }
                }
                lineOfNew++;
                rowOfNew = 0;
            }
        }

        vertexNumber--;
        setAdjacencyMatrix(newAdjacencyMatrix);

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

            int line;
            int row;
            for (line = 0; line < vertexNumber; line++) {
                for (row = line + 1; row < vertexNumber; row++) {
                    if (getAdjacencyMatrix()[line][row].compareTo("*") == 0) {
                        String writeLine;
                        String edgeLabel = getEdgeLabel(new Edge(line + 1, row + 1, ""));
                        if (edgeLabel != null) {
                            writeLine = new String((line + 1) + " " + (row + 1) + " " + edgeLabel + System.getProperty("line.separator").toString());
                        } else {
                            writeLine = new String((line + 1) + " " + (row + 1) + System.getProperty("line.separator").toString());
                        }
                        fout.write(writeLine);
                    }
                }
            }

        } catch (IOException ex) {
            System.err.println("Nu se poate folosi \"" + numeFisierScriere + "\" pentru scriere!");
            Logger.getLogger(MatrixGraph.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException ex) {
                System.err.println("Nu se poate inchide \"" + numeFisierScriere + "\"!");
                Logger.getLogger(MatrixGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the adjacencyMatrix
     */
    public String[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * @param adjacencyMatrix the adjacencyMatrix to set
     */
    public void setAdjacencyMatrix(String[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    @Override
    public ArrayList<Vertex> getNeighbourhood(int vertex) {
        ArrayList<Vertex> neighbourhood = new ArrayList<>();

        int row;
        for (row = 0; row < vertexNumber; row++) {
            if (adjacencyMatrix[vertex - 1][row].compareTo("*") == 0) {
                neighbourhood.add(new Vertex(row + 1, getVertexLabel(row + 1)));
            }
        }

        return neighbourhood;
    }
}
