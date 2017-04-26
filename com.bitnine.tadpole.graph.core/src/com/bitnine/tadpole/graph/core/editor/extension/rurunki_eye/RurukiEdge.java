
package com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye;

import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RurukiEdge {

    @SerializedName("head_id")
    @Expose
    private String headId;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("properties")
    @Expose
    private Map properties;
    @SerializedName("tail_id")
    @Expose
    private String tailId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RurukiEdge() {
    }

    /**
     * 
     * @param id
     * @param tailId
     * @param headId
     * @param label
     * @param properties
     * @param metadata
     */
    public RurukiEdge(String headId, String id, String label, Metadata metadata, Map properties, String tailId) {
        this.headId = headId;
        this.id = id;
        this.label = label;
        this.metadata = metadata;
        this.properties = properties;
        this.tailId = tailId;
    }

    /**
     * 
     * @return
     *     The headId
     */
    public String getHeadId() {
        return headId;
    }

    /**
     * 
     * @param headId
     *     The head_id
     */
    public void setHeadId(String headId) {
        this.headId = headId;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * 
     * @param metadata
     *     The metadata
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * 
     * @return
     *     The properties
     */
    public Map getProperties() {
        return properties;
    }

    /**
     * 
     * @param properties
     *     The properties
     */
    public void setProperties(Map properties) {
        this.properties = properties;
    }

    /**
     * 
     * @return
     *     The tailId
     */
    public String getTailId() {
        return tailId;
    }

    /**
     * 
     * @param tailId
     *     The tail_id
     */
    public void setTailId(String tailId) {
        this.tailId = tailId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(headId).append(id).append(label).append(metadata).append(properties).append(tailId).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RurukiEdge) == false) {
            return false;
        }
        RurukiEdge rhs = ((RurukiEdge) other);
        return new EqualsBuilder().append(headId, rhs.headId).append(id, rhs.id).append(label, rhs.label).append(metadata, rhs.metadata).append(properties, rhs.properties).append(tailId, rhs.tailId).isEquals();
    }

}
