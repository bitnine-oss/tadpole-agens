
package com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class RurunkiEye {

    @SerializedName("edges")
    @Expose
    private List<RurukiEdge> rurukiEdges = new ArrayList<RurukiEdge>();
    @SerializedName("vertices")
    @Expose
    private List<RurukiNode> vertices = new ArrayList<RurukiNode>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RurunkiEye() {
    }

    /**
     * 
     * @param rurukiEdges
     * @param vertices
     */
    public RurunkiEye(List<RurukiEdge> rurukiEdges, List<RurukiNode> vertices) {
        this.rurukiEdges = rurukiEdges;
        this.vertices = vertices;
    }

    /**
     * 
     * @return
     *     The rurukiEdges
     */
    public List<RurukiEdge> getEdges() {
        return rurukiEdges;
    }

    /**
     * 
     * @param rurukiEdges
     *     The rurukiEdges
     */
    public void setEdges(List<RurukiEdge> rurukiEdges) {
        this.rurukiEdges = rurukiEdges;
    }

    /**
     * 
     * @return
     *     The vertices
     */
    public List<RurukiNode> getVertices() {
        return vertices;
    }

    /**
     * 
     * @param vertices
     *     The vertices
     */
    public void setVertices(List<RurukiNode> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(rurukiEdges).append(vertices).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RurunkiEye) == false) {
            return false;
        }
        RurunkiEye rhs = ((RurunkiEye) other);
        return new EqualsBuilder().append(rurukiEdges, rhs.rurukiEdges).append(vertices, rhs.vertices).isEquals();
    }

}
