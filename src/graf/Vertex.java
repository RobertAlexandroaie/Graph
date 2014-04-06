/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;

/**
 *
 * @author Robert
 */
public class Vertex {
    protected int id;
    protected String label;
    
    public Vertex(int newId, String newLabel) {
        id = newId;
        label = new String(newLabel);
    }

    /**
     * @return the id
     */
    public int getID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
