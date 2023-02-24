/**
 * パッケージ名：org.geofuse.bean
 * ファイル名  ：WmsParamBean.java
 * 
 * @author mbasa
 * @since Feb 9, 2023
 */
package org.geofuse.bean;

/**
 * 説明：
 *
 */
public class WmsParamBean {

    private String BBOX           = "";   
    private String CQL_FILTER     = "";
    private String EXCEPTIONS     = "";
    private String FORMAT         = "";
    private String HEIGHT         = "";
    private String LAYERS         = "";
    private String LAYER          = "";
    private String REQUEST        = "";
    private String SERVICE        = "";
    private String SLD            = "";
    private String SLD_BODY       = "";
    private String SRS            = "";
    private String STYLES         = "";
    private String TILED          = "";
    private String TILESORIGIN    = "";
    private String TRANSPARENT    = "";
    private String VERSION        = "1.0.0";
    private String VIEWPARAMS     = "";
    private String WIDTH          = "";
    private String PDF_TITLE      = "";
    private String PDF_NOTE       = "";
    private String LEGEND_OPTIONS = "";

    /**
     * コンストラクタ
     *
     */
    public WmsParamBean() {
    }

    /**
     * @return bBOX を取得する
     */
    public String getBBOX() {
        return BBOX;
    }

    /**
     * @param bBOX bBOX を設定する
     */
    public void setBBOX(String bBOX) {
        BBOX = bBOX;
    }

    /**
     * @return cQL_FILTER を取得する
     */
    public String getCQL_FILTER() {
        return CQL_FILTER;
    }

    /**
     * @param cQL_FILTER cQL_FILTER を設定する
     */
    public void setCQL_FILTER(String cQL_FILTER) {
        CQL_FILTER = cQL_FILTER;
    }

    /**
     * @return eXCEPTIONS を取得する
     */
    public String getEXCEPTIONS() {
        return EXCEPTIONS;
    }

    /**
     * @param eXCEPTIONS eXCEPTIONS を設定する
     */
    public void setEXCEPTIONS(String eXCEPTIONS) {
        EXCEPTIONS = eXCEPTIONS;
    }

    /**
     * @return fORMAT を取得する
     */
    public String getFORMAT() {
        return FORMAT;
    }

    /**
     * @param fORMAT fORMAT を設定する
     */
    public void setFORMAT(String fORMAT) {
        FORMAT = fORMAT;
    }

    /**
     * @return hEIGHT を取得する
     */
    public String getHEIGHT() {
        return HEIGHT;
    }

    /**
     * @param hEIGHT hEIGHT を設定する
     */
    public void setHEIGHT(String hEIGHT) {
        HEIGHT = hEIGHT;
    }

    /**
     * @return lAYERS を取得する
     */
    public String getLAYERS() {
        return LAYERS;
    }

    /**
     * @param lAYERS lAYERS を設定する
     */
    public void setLAYERS(String lAYERS) {
        LAYERS = lAYERS;
    }

    /**
     * @return lAYER を取得する
     */
    public String getLAYER() {
        return LAYER;
    }

    /**
     * @param lAYER lAYER を設定する
     */
    public void setLAYER(String lAYER) {
        LAYER = lAYER;
    }

    /**
     * @return rEQUEST を取得する
     */
    public String getREQUEST() {
        return REQUEST;
    }

    /**
     * @param rEQUEST rEQUEST を設定する
     */
    public void setREQUEST(String rEQUEST) {
        REQUEST = rEQUEST;
    }

    /**
     * @return sERVICE を取得する
     */
    public String getSERVICE() {
        return SERVICE;
    }

    /**
     * @param sERVICE sERVICE を設定する
     */
    public void setSERVICE(String sERVICE) {
        SERVICE = sERVICE;
    }

    /**
     * @return sLD を取得する
     */
    public String getSLD() {
        return SLD;
    }

    /**
     * @param sLD sLD を設定する
     */
    public void setSLD(String sLD) {
        SLD = sLD;
    }

    /**
     * @return sLD_BODY を取得する
     */
    public String getSLD_BODY() {
        return SLD_BODY;
    }

    /**
     * @param sLD_BODY sLD_BODY を設定する
     */
    public void setSLD_BODY(String sLD_BODY) {
        SLD_BODY = sLD_BODY;
    }

    /**
     * @return sRS を取得する
     */
    public String getSRS() {
        return SRS;
    }

    /**
     * @param sRS sRS を設定する
     */
    public void setSRS(String sRS) {
        SRS = sRS;
    }

    /**
     * @return sTYLES を取得する
     */
    public String getSTYLES() {
        return STYLES;
    }

    /**
     * @param sTYLES sTYLES を設定する
     */
    public void setSTYLES(String sTYLES) {
        STYLES = sTYLES;
    }

    /**
     * @return tILED を取得する
     */
    public String getTILED() {
        return TILED;
    }

    /**
     * @param tILED tILED を設定する
     */
    public void setTILED(String tILED) {
        TILED = tILED;
    }

    /**
     * @return tILESORIGIN を取得する
     */
    public String getTILESORIGIN() {
        return TILESORIGIN;
    }

    /**
     * @param tILESORIGIN tILESORIGIN を設定する
     */
    public void setTILESORIGIN(String tILESORIGIN) {
        TILESORIGIN = tILESORIGIN;
    }

    /**
     * @return tRANSPARENT を取得する
     */
    public String getTRANSPARENT() {
        return TRANSPARENT;
    }

    /**
     * @param tRANSPARENT tRANSPARENT を設定する
     */
    public void setTRANSPARENT(String tRANSPARENT) {
        TRANSPARENT = tRANSPARENT;
    }

    /**
     * @return vERSION を取得する
     */
    public String getVERSION() {
        return VERSION;
    }

    /**
     * @param vERSION vERSION を設定する
     */
    public void setVERSION(String vERSION) {
        VERSION = vERSION;
    }

    /**
     * @return vIEWPARAMS を取得する
     */
    public String getVIEWPARAMS() {
        return VIEWPARAMS;
    }

    /**
     * @param vIEWPARAMS vIEWPARAMS を設定する
     */
    public void setVIEWPARAMS(String vIEWPARAMS) {
        VIEWPARAMS = vIEWPARAMS;
    }

    /**
     * @return wIDTH を取得する
     */
    public String getWIDTH() {
        return WIDTH;
    }

    /**
     * @param wIDTH wIDTH を設定する
     */
    public void setWIDTH(String wIDTH) {
        WIDTH = wIDTH;
    }

    /**
     * @return pDF_TITLE を取得する
     */
    public String getPDF_TITLE() {
        return PDF_TITLE;
    }

    /**
     * @param pDF_TITLE pDF_TITLE を設定する
     */
    public void setPDF_TITLE(String pDF_TITLE) {
        PDF_TITLE = pDF_TITLE;
    }

    /**
     * @return pDF_NOTE を取得する
     */
    public String getPDF_NOTE() {
        return PDF_NOTE;
    }

    /**
     * @param pDF_NOTE pDF_NOTE を設定する
     */
    public void setPDF_NOTE(String pDF_NOTE) {
        PDF_NOTE = pDF_NOTE;
    }

    /**
     * @return lEGEND_OPTIONS を取得する
     */
    public String getLEGEND_OPTIONS() {
        return LEGEND_OPTIONS;
    }

    /**
     * @param lEGEND_OPTIONS lEGEND_OPTIONS を設定する
     */
    public void setLEGEND_OPTIONS(String lEGEND_OPTIONS) {
        LEGEND_OPTIONS = lEGEND_OPTIONS;
    }
    
    public String getURL_PARAM() {
        StringBuffer sb = new StringBuffer();
        
        sb.append("REQUEST=").append(this.getREQUEST());
        sb.append("&FORMAT=").append(this.getFORMAT());
        
        if( this.getCQL_FILTER() != "" ) {
            sb.append("&CQL_FILTER=").append(this.getCQL_FILTER());
        }
        if( this.getSRS() != "" ) {
            sb.append("&SRS=").append(this.getSRS());                
        }
        if( this.getTRANSPARENT() != "" ) {
            sb.append("&TRANSPARENT=").append(this.getTRANSPARENT());                
        }
        if( this.getBBOX() != "" ) {
            sb.append("&BBOX=").append(this.getBBOX());
        }
        if( this.getEXCEPTIONS() != "" ) {
            sb.append("&EXCEPTIONS=").append(this.getEXCEPTIONS());
        }
        if( this.getSERVICE() != "" ) {
            sb.append("&SERVICE=").append(this.getSERVICE());   
        }
        if(this.getLAYER() != "") {
            sb.append("&LAYER=").append(this.getLAYER());
        }
        if(this.getLAYERS() != "") {
            sb.append("&LAYERS=").append(this.getLAYERS());
        }
        if(this.getWIDTH() != "" && this.getHEIGHT() != "") {
            sb.append("&HEIGHT=").append(this.getHEIGHT());
            sb.append("&WIDTH=").append(this.getWIDTH());
        }
        if(this.getVERSION() != "") {
            sb.append("&VERSION=").append(this.getVERSION());    
        }
        if(this.getTILESORIGIN() != "" && this.getTILED() != "") {
            sb.append("&TILED=").append(this.getTILED());
            sb.append("&TILESORIGIN=").append(this.getTILESORIGIN());
        } 
        if( this.getSLD() != "") {
            sb.append("&SLD=").append(this.getSLD());
        }
        if( this.getSTYLES() != "" ){
            sb.append("&STYLES=").append(this.getSTYLES());
        }
        if( this.getSLD_BODY() != "" ){
            sb.append("&SLD_BODY=").append(this.getSLD_BODY());
        }
        if( this.getVIEWPARAMS() != "" ) {
            sb.append("&VIEWPARAMS=").append(this.getVIEWPARAMS());
        }
        if( this.getLEGEND_OPTIONS() != "" ) {
            sb.append("&LEGEND_OPTIONS=").append(this.getLEGEND_OPTIONS());
        }
        return sb.toString();
    }
}
