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
public interface Graph {

    public void write(String numeFisierScriere);

    public void read(String numeFisierCitire);

    public boolean hasVertex(int vertex);

    public boolean hasEdge(Edge edge);

    public void addVertex(String label);

    public void addEdge(Edge edge);

    public void deleteVertex(int vertex);

    public void deleteVertex(String label);

    public void deleteEdge(Edge edge);

    public void deleteEdge(String label);

    public boolean hasVertexLabel(String label);

    public void deleteVertexLabel(String label);

    public boolean hasEdgeLabel(String label);

    public void deleteEdgeLabel(String label);
    
     public  void setEdgeLabel(Edge edge, String label);
    
    public  String getEdgeLabel(Edge edge);
    
    public String getVertexLabel(int vertex);
        
    public ArrayList<Vertex> getNeighbourhood(int vertex);
    
    public int getVertexNumber();
    
    public int getEdgeNumber();

    public void explore(Vertex startVertex, Parcurgere parcurgere, Actiune actiune);
}
