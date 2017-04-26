
package com.bitnine.tadpole.graph.core.editor.extension.sigma;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class GEdge {

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("size")
    @Expose
    private double size;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("hover_color")
    @Expose
    private String hoverColor;
    @SerializedName("count")
    @Expose
    private double count;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GEdge() {
    }

    /**
     * 
     * @param id
     * @param count
     * @param source
     * @param color
     * @param target
     * @param label
     * @param type
     * @param hoverColor
     * @param size
     */
    public GEdge(String source, String target, String id, String label, double size, String type, String color, String hoverColor, double count) {
        this.source = source;
        this.target = target;
        this.id = id;
        this.label = label;
        this.size = size;
        this.type = type;
        this.color = color;
        this.hoverColor = hoverColor;
        this.count = count;
    }

    /**
     * 
     * @return
     *     The source
     */
    public String getSource() {
        return source;
    }

    /**
     * 
     * @param source
     *     The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 
     * @return
     *     The target
     */
    public String getTarget() {
        return target;
    }

    /**
     * 
     * @param target
     *     The target
     */
    public void setTarget(String target) {
        this.target = target;
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
     *     The size
     */
    public double getSize() {
        return size;
    }

    /**
     * 
     * @param size
     *     The size
     */
    public void setSize(double size) {
        this.size = size;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The color
     */
    public String getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 
     * @return
     *     The hoverColor
     */
    public String getHoverColor() {
        return hoverColor;
    }

    /**
     * 
     * @param hoverColor
     *     The hover_color
     */
    public void setHoverColor(String hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * 
     * @return
     *     The count
     */
    public double getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(source).append(target).append(id).append(label).append(size).append(type).append(color).append(hoverColor).append(count).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GEdge) == false) {
            return false;
        }
        GEdge rhs = ((GEdge) other);
        return new EqualsBuilder().append(source, rhs.source).append(target, rhs.target).append(id, rhs.id).append(label, rhs.label).append(size, rhs.size).append(type, rhs.type).append(color, rhs.color).append(hoverColor, rhs.hoverColor).append(count, rhs.count).isEquals();
    }

}
