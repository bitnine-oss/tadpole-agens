
package com.bitnine.tadpole.graph.core.editor.extension.rurunki_eye;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import net.bitnine.agensgraph.graph.Vertex;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Metadata {

    @SerializedName("in_edge_count")
    @Expose
    private int inEdgeCount;
    @SerializedName("out_edge_count")
    @Expose
    private int outEdgeCount;
    @SerializedName("name_key")
    @Expose
    private String name_key;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Metadata() {
    }

    /**
     * 
     * @param outEdgeCount
     * @param inEdgeCount
     */
    public Metadata(int inEdgeCount, int outEdgeCount, Vertex vertex) {
        this.inEdgeCount = inEdgeCount;
        this.outEdgeCount = outEdgeCount;
        
        // 노드의 기본레이블은 Provety명이 'name'을 사용하도록한다.
        // name이 없을경우 "name"을 포함하는 명칭을 사용하거나 첫번째 문자열 속성값을 사용하도록 한다.
        this.name_key = "name";
		if (!vertex.getProperty().toMap().containsKey("name")){
			for(String key : vertex.getProperty().toMap().keySet()){
				if (StringUtils.containsIgnoreCase(key, "name") ){
					this.name_key = key;
					break;
				}else if (vertex.getProperty().toMap().get(key) instanceof String){
					this.name_key = key;
					break;
				}
			}
		}
		
    }

    /**
     * 
     * @return
     *     The inEdgeCount
     */
    public int getInEdgeCount() {
        return inEdgeCount;
    }

    /**
     * 
     * @param inEdgeCount
     *     The in_edge_count
     */
    public void setInEdgeCount(int inEdgeCount) {
        this.inEdgeCount = inEdgeCount;
    }

    /**
     * 
     * @return
     *     The outEdgeCount
     */
    public int getOutEdgeCount() {
        return outEdgeCount;
    }

    /**
     * 
     * @param outEdgeCount
     *     The out_edge_count
     */
    public void setOutEdgeCount(int outEdgeCount) {
        this.outEdgeCount = outEdgeCount;
    }

    /**
     * 
     * @return
     */
    public String getName_key() {
		return name_key;
	}

    /**
     * 
     * @param name_key
     */
	public void setName_key(String name_key) {
		this.name_key = name_key;
	}

	/**
	 * 
	 */
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	/**
	 * 
	 */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(inEdgeCount).append(outEdgeCount).toHashCode();
    }

    /**
     * 
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metadata) == false) {
            return false;
        }
        Metadata rhs = ((Metadata) other);
        return new EqualsBuilder().append(inEdgeCount, rhs.inEdgeCount).append(outEdgeCount, rhs.outEdgeCount).isEquals();
    }

}
