/**
 * パッケージ名：org.geofuse.bean
 * ファイル名  ：SldBean.java
 * 
 * @author mbasa
 * @since Sep 5, 2022
 */
package org.geofuse.bean;

import java.awt.Color;

/**
 * 説明：
 *
 */
public class SldBean {

    private Color colorFrom = Color.YELLOW;
    private Color colorTo = Color.RED;
    private String typeName = new String();
    private String geoType  = new String();
    private String propName = new String();
    private String typeRange = "EQRange";
    private String cqlString = "";
    private String viewParams= "";
    private String labScale  = "";
    private int numRange = 6;

    /**
     * コンストラクタ
     *
     */
    public SldBean() {
    }

    /**
     * @return colorFrom を取得する
     */
    public Color getColorFrom() {
        return colorFrom;
    }

    /**
     * @param colorFrom colorFrom を設定する
     */
    public void setColorFrom(Color colorFrom) {
        this.colorFrom = colorFrom;
    }

    /**
     * @return colorTo を取得する
     */
    public Color getColorTo() {
        return colorTo;
    }

    /**
     * @param colorTo colorTo を設定する
     */
    public void setColorTo(Color colorTo) {
        this.colorTo = colorTo;
    }

    /**
     * @return typeName を取得する
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName typeName を設定する
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return geoType を取得する
     */
    public String getGeoType() {
        return geoType;
    }

    /**
     * @param geoType geoType を設定する
     */
    public void setGeoType(String geoType) {
        this.geoType = geoType;
    }

    /**
     * @return propName を取得する
     */
    public String getPropName() {
        return propName;
    }

    /**
     * @param propName propName を設定する
     */
    public void setPropName(String propName) {
        this.propName = propName;
    }

    /**
     * @return typeRange を取得する
     */
    public String getTypeRange() {
        return typeRange;
    }

    /**
     * @param typeRange typeRange を設定する
     */
    public void setTypeRange(String typeRange) {
        this.typeRange = typeRange;
    }

    /**
     * @return cqlString を取得する
     */
    public String getCqlString() {
        return cqlString;
    }

    /**
     * @param cqlString cqlString を設定する
     */
    public void setCqlString(String cqlString) {
        this.cqlString = cqlString;
    }

    /**
     * @return viewParams を取得する
     */
    public String getViewParams() {
        return viewParams;
    }

    /**
     * @param viewParams viewParams を設定する
     */
    public void setViewParams(String viewParams) {
        this.viewParams = viewParams;
    }

    /**
     * @return labScale を取得する
     */
    public String getLabScale() {
        return labScale;
    }

    /**
     * @param labScale labScale を設定する
     */
    public void setLabScale(String labScale) {
        this.labScale = labScale;
    }

    /**
     * @return numRange を取得する
     */
    public int getNumRange() {
        return numRange;
    }

    /**
     * @param numRange numRange を設定する
     */
    public void setNumRange(int numRange) {
        this.numRange = numRange;
    }

}
