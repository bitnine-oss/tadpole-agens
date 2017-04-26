
package com.bitnine.tadpole.graph.core.editor.extension.sigma;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Node {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("x")
    @Expose
    private double x;
    @SerializedName("y")
    @Expose
    private double y;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("borderColor")
    @Expose
    private String borderColor;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("size")
    @Expose
    private double size;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Node() {
    }

    /**
     * 
     * @param id
     * @param borderColor
     * @param color
     * @param label
     * @param type
     * @param y
     * @param size
     * @param x
     */
    public Node(String label, double x, double y, String id, String color, String borderColor, String type, double size) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.id = id;
        this.color = color;
        this.borderColor = borderColor;
        this.type = type;
        this.size = size;
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
     *     The x
     */
    public double getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * 
     * @return
     *     The y
     */
    public double getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The y
     */
    public void setY(double y) {
        this.y = y;
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
     *     The borderColor
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * 
     * @param borderColor
     *     The borderColor
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(label).append(x).append(y).append(id).append(color).append(borderColor).append(type).append(size).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Node) == false) {
            return false;
        }
        Node rhs = ((Node) other);
        return new EqualsBuilder().append(label, rhs.label).append(x, rhs.x).append(y, rhs.y).append(id, rhs.id).append(color, rhs.color).append(borderColor, rhs.borderColor).append(type, rhs.type).append(size, rhs.size).isEquals();
    }

}
