
package com.bitnine.tadpole.graph.core.editor.extension.sigma;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class JSONUtils {

    @SerializedName("edges")
    @Expose
    private List<GEdge> edges = new ArrayList<GEdge>();
    @SerializedName("nodes")
    @Expose
    private List<Node> nodes = new ArrayList<Node>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public JSONUtils() {
    }

    /**
     * 
     * @param edges
     * @param nodes
     */
    public JSONUtils(List<GEdge> edges, List<Node> nodes) {
        this.edges = edges;
        this.nodes = nodes;
    }

    /**
     * 
     * @return
     *     The edges
     */
    public List<GEdge> getEdges() {
        return edges;
    }

    /**
     * 
     * @param edges
     *     The edges
     */
    public void setEdges(List<GEdge> edges) {
        this.edges = edges;
    }

    /**
     * 
     * @return
     *     The nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * 
     * @param nodes
     *     The nodes
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(edges).append(nodes).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof JSONUtils) == false) {
            return false;
        }
        JSONUtils rhs = ((JSONUtils) other);
        return new EqualsBuilder().append(edges, rhs.edges).append(nodes, rhs.nodes).isEquals();
    }

}
