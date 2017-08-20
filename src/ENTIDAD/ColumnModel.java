/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTIDAD;

import java.util.Date;

/**
 *
 * @author Diseño
 */
public class ColumnModel {

    private String header;
    private String property;
    private Date property_date;
    private int index;

    public ColumnModel() {
    }

    public ColumnModel(String header, String property) {
        this.header = header;
        this.property = property;
    }
    
     public ColumnModel(String header, Date property_date) {
        this.header = header;
        this.property_date = property_date;
    }

    public ColumnModel(String header, String property, int index) {
        this.header = header;
        this.property = property;
        this.index = index;
    }

    /**
     * @return the header
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header the header to set
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the property_date
     */
    public Date getProperty_date() {
        return property_date;
    }

    /**
     * @param property_date the property_date to set
     */
    public void setProperty_date(Date property_date) {
        this.property_date = property_date;
    }

}
