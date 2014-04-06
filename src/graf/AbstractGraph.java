/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public abstract class AbstractGraph
        implements Graph {

    ArrayList<Vertex> vertexes;
    ArrayList<Edge> edges;
    protected int vertexNumber = 0;
    protected int edgeNumber = 0;

    public AbstractGraph() {
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
        vertexNumber = 0;
        edgeNumber = 0;
    }

    public AbstractGraph(String numeFisierCitire) {
        setVertexNumber(numeFisierCitire);
        read(numeFisierCitire);
    }

    public void setVertexNumber(String numeFisierCitire) {
        FileReader fin = null;
        vertexNumber = 0;

        try {
            Scanner sc = null;
            String readLine = "";

            fin = new FileReader(numeFisierCitire);
            sc = new Scanner(fin);

            while (sc.hasNext() && readLine.compareTo("#".toString()) != 0) {
                readLine = new String(sc.nextLine());
                if (readLine.compareTo("#".toString()) != 0) {
                    vertexNumber++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Nu exista fisierul!");
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ex) {
                System.err.println("Nu se poate inchide fisierul!");
            }
        }
    }

    @Override
    public abstract void write(String numeFisierScriere);

    @Override
    public void read(String numeFisierCitire) {
        FileReader fin = null;
        vertexes.clear();
        edges.clear();
        vertexNumber = 0;
        edgeNumber = 0;

        try {
            Scanner sc = null;
            String readLine = "";

            fin = new FileReader(numeFisierCitire);
            sc = new Scanner(fin);

            while (sc.hasNext() && readLine.compareTo("#".toString()) != 0) {
                Integer key = null;
                String label = null;
                readLine = new String(sc.nextLine());
                if (readLine.compareTo("#".toString()) != 0) {
                    StringTokenizer tokenizer = new StringTokenizer(readLine, " ");

                    if (tokenizer.hasMoreTokens()) {
                        key = new Integer(Integer.parseInt(tokenizer.nextToken()));
                    }

                    if (tokenizer.hasMoreTokens()) {
                        label = new String(tokenizer.nextToken());
                    }
                    vertexNumber++;
                    vertexes.add(new Vertex(key, label));
                }
            }

            if (readLine.compareTo("#".toString()) == 0) {
                while (sc.hasNext()) {
                    int vertex1 = 0;
                    int vertex2 = 0;
                    String label = null;
                    Edge newEdge = null;

                    readLine = new String(sc.nextLine());
                    StringTokenizer tokenizer = new StringTokenizer(readLine, " ");

                    if (tokenizer.hasMoreTokens()) {
                        vertex1 = Integer.parseInt(tokenizer.nextToken());
                    }
                    if (tokenizer.hasMoreTokens()) {
                        vertex2 = Integer.parseInt(tokenizer.nextToken());
                    }
                    newEdge = new Edge(vertex1, vertex2, "");
                    edges.add(newEdge);
                    addEdge(newEdge);

                    if (tokenizer.hasMoreTokens()) {
                        label = new String(tokenizer.nextElement().toString());
                        setEdgeLabel(newEdge, label);
                    }
                    edgeNumber++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Nu se poate folosi \"" + numeFisierCitire + "\" pentru citire!");
            Logger
                    .getLogger(MatrixGraph.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.err.println("Ceva nu e ok!");
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ex) {
                System.err.println("Nu se poate inchide \"" + numeFisierCitire + "\"!");
                Logger
                        .getLogger(MatrixGraph.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean hasVertex(int vertex) {
        if (vertex <= vertexNumber) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public abstract boolean hasEdge(Edge edge);

    @Override
    public abstract void addVertex(String label);

    @Override
    public abstract void addEdge(Edge edge);

    @Override
    public abstract void deleteVertex(int vertex);

    @Override
    public abstract void deleteEdge(Edge edge);

    @Override
    public boolean hasVertexLabel(String label) {
        for (Vertex it : vertexes) {
            if (it.getLabel().compareTo(label) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteVertexLabel(String label) {
        for (Vertex it : vertexes) {
            if (it.getLabel().compareTo(label) == 0) {
                it.setLabel("");
            }
        }
    }

    @Override
    public boolean hasEdgeLabel(String label) {
        for (Edge it : edges) {
            if (it.getLabel() != null && it.getLabel().compareTo(label) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteEdgeLabel(String label) {
        for (Edge it : edges) {
            if (it.getLabel().compareTo(label) == 0) {
                it.setLabel("");
            }
        }
    }

    @Override
    public void explore(Vertex startVertex, Parcurgere parcurgere, Actiune actiune) {
        int visited[] = new int[this.getVertexNumber()];
        for (int it = 0; it < visited.length; it++) {
            visited[it] = 0;
        }
        parcurgere.parcurge(startVertex, visited, this, actiune);
    }

    /**
     * @return the vertexLabels
     */
    protected ArrayList<Vertex> getVertexes() {
        return vertexes;
    }

    /**
     * @param vertexLabels the vertexLabels to set
     */
    protected void setVertexLabels(ArrayList<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    /**
     * @return the vertexNumber
     */
    public int getVertexNumber() {
        return vertexNumber;
    }

    /**
     * @param vertexNumber the vertexNumber to set
     */
    public void setVertexNumber(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    /**
     * @return the edgeNumber
     */
    public int getEdgeNumber() {
        return edgeNumber;
    }

    /**
     * @param edgeNumber the edgeNumber to set
     */
    protected void setEdgeNumber(int edgeNumber) {
        this.edgeNumber = edgeNumber;
    }

    public String getEdgeLabel(Edge edge) {
        int vertex1 = edge.getVertex1();
        int vertex2 = edge.getVertex2();

        for (Edge it : edges) {
            if ((it.getVertex1() == vertex1 && it.getVertex2() == vertex2) || (it.getVertex1() == vertex2 && it.getVertex2() == vertex1)) {
                return it.getLabel();
            }
        }
        return "";
    }

    public void setEdgeLabel(Edge edge, String label) {

        int vertex1 = edge.getVertex1();
        int vertex2 = edge.getVertex2();

        for (Edge it : edges) {
            if ((it.getVertex1() == vertex1 && it.getVertex2() == vertex2) || (it.getVertex1() == vertex2 && it.getVertex2() == vertex1)) {
                it.setLabel(label);
            }
        }
    }

    public String getVertexLabel(int vertex) {
        for (Vertex it : vertexes) {
            if (it.getID() == vertex) {
                return it.getLabel();
            }
        }
        return "";
    }

    public abstract ArrayList<Vertex> getNeighbourhood(int vertex);

    public void deleteVertex(String label) {
        for (Vertex it : vertexes) {
            if (it.getLabel().compareTo(label) == 0) {
                deleteVertex(it.getID());
            }
        }
    }

    public void deleteEdge(String label) {
        ArrayList<Edge> backupEdgeLabels = new ArrayList<>(edges);
        for(Edge it : backupEdgeLabels) {
            if (it.getLabel().compareTo(label) == 0) {
                deleteEdge(it);
            }
        }
    }
}