/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

/**
 *
 * @author Robert
 */
public class Edge {
    protected int vertex1=0;
    protected int vertex2=0;
    protected String label;

    Edge(int vertex1, int vertex2) {
        this.vertex1=vertex1;
        this.vertex2=vertex2;
        label = "";
    }
    
    Edge (int vertex1, int vertex2, String label) {
        setEdge(vertex1, vertex2);
    }
    
    /**
     * @return the vertex1
     */
    public int getVertex1() {
        return vertex1;
    }

    /**
     * @param vertex1 the vertex1 to set
     */
    public void setVertex1(int vertex1) {
        this.vertex1 = vertex1;
    }

    /**
     * @return the vertex2
     */
    public int getVertex2() {
        return vertex2;
    }

    /**
     * @param vertex2 the vertex2 to set
     */
    public void setVertex2(int vertex2) {
        this.vertex2 = vertex2;
    }
    
    /**
     * 
     * @param vertex1
     * @param vertex2 
     */
    public void setEdge(int vertex1, int vertex2) {
        this.vertex1=vertex1;
        this.vertex2=vertex2;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
