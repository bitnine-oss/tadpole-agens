
package com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import net.bitnine.agensgraph.graph.property.JsonArray;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class RurukiNode {

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

    /**
     * No args constructor for use in serialization
     * 
     */
    public RurukiNode() {
    }

    /**
     * 
     * @param id
     * @param label
     * @param properties
     * @param metadata
     */
    public RurukiNode(String id, String label, Metadata metadata, Map properties) {
        this.id = id;
        this.label = label;
        this.metadata = metadata;
        this.properties = properties;
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
    public void setProperties(Map<String,Object> properties) {
    	
    	for (String key: properties.keySet()){
			if ((properties.get(key) instanceof JsonArray)){
				
				JsonArray arr = (JsonArray)properties.get(key);
				List<String> list = new ArrayList<String>();
				for(int i = 0; i < arr.size() ; i++){
				    list.add(arr.getString(i));
				}
				
				properties.put(key, list.toString());
			}
		}
    	
        this.properties = properties;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(label).append(metadata).append(properties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RurukiNode) == false) {
            return false;
        }
        RurukiNode rhs = ((RurukiNode) other);
        return new EqualsBuilder().append(id, rhs.id).append(label, rhs.label).append(metadata, rhs.metadata).append(properties, rhs.properties).isEquals();
    }

}
