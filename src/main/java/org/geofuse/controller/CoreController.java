/**
 * パッケージ名：org.geofuse.controller
 * ファイル名  ：CoreController.java
 * 
 * @author mbasa
 * @since Aug 26, 2022
 */
package org.geofuse.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.geofuse.bean.WmsParamBean;
import org.geofuse.sld.GenerateSLD;
import org.geofuse.util.ConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 * 説明：
 *
 */

@RestController
@RequestMapping("/core")
public class CoreController {

    @Autowired
    ConfigProperties configProp;

    @Autowired
    GenerateSLD gsld;

    Logger logger = LoggerFactory.getLogger(getClass());

    private String geoserverURL = new String();
    private String[] colorNames = {};
    private String[] colorVals = {};
    
    private Map<String, String[]> colorMap = new HashMap<String, String[]>();

    /**
     * コンストラクタ
     *
     */
    public CoreController() {
    }

    @PostConstruct
    public void init() {
        geoserverURL = configProp.getConfigValue("GEOSERVER.BASE.URL");
        colorNames = configProp.getConfigValue("THEMATIC.COLOR.NAMES").split(",");
        colorVals = configProp.getConfigValue("THEMATIC.COLORS").split(",");
         
        if (colorNames.length == colorVals.length) {
            for (int i = 0; i < colorNames.length; i++) {
                try {
                    byte[] byteData = colorNames[i].getBytes("ISO_8859_1");
                    colorNames[i] = new String(byteData, "UTF-8");
                } catch (Exception e) {
                    System.out.println(e);
                }
                this.colorMap.put(colorNames[i], colorVals[i].split("-"));
            }
        }
        logger.info("*** Core PostConstruct *** " + this.geoserverURL);
    }

    @Operation(summary = "Generate Dynamic SLD for Thematic Maps.")
    @RequestMapping(value = "/generateSld", method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_XML_VALUE })
    public String generateSld(
            @Parameter(allowEmptyValue = false, description = "Name of pre-defined WMS/WFS Layer to be used i.e. geofuse:geolink") @RequestParam(defaultValue = "geofuse:geolink",required = true) String typename,
            @Parameter(allowEmptyValue = false, description = "Pre-defined Range of Colors i.e YellowToRed,YellowToGreen,RedToMagenta,Red,Green,Blue,Brown", example="YellowToRed") @RequestParam(value="color",required = false,defaultValue = "YellowToRed") String color,            
            @Parameter(allowEmptyValue = false, description = "Geographic Type of Layer i.e. polygon,line,point", example = "polygon") @RequestParam(value="geotype",required = false, defaultValue="polygon") String geotype,
            @Parameter(allowEmptyValue = false, description = "Property(Field) Name to be used as Metric") @RequestParam(value="propname",required = false) String propname,
            @Parameter(allowEmptyValue = false, description = "Range Type Distribution i.e. EQRange,EQCount,Geometric,Natural,Standard", example = "EQRange") @RequestParam(value="typerange",required = false,defaultValue="EQRange") String typerange,
            @Parameter(allowEmptyValue = false, description = "Map Scale when Labels will display", example = "5000") @RequestParam(value="labscale",required = false) String labscale,
            @Parameter(allowEmptyValue = true,  description = "CQL Query to Filter Map") @RequestParam(value="cql",required = false) String cql,
            @Parameter(allowEmptyValue = true,  description = "Parametric SQL values") @RequestParam(value="viewparams",required = false) String viewparmas,
            @Parameter(allowEmptyValue = false, description = "Number of Thematic Ranges to Create", example = "6") @RequestParam(value="numrange",required = false, defaultValue="6") int numrange,
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        
        gsld.initColorMap();
        
        gsld.setTypeName(typename);
        gsld.setPropName(propname);
        gsld.setColor(color);
        gsld.setGeoType(geotype);
        gsld.setTypeRange(typerange);
        gsld.setLabScale(labscale);
        gsld.setCqlString(cql);
        gsld.setViewParams(viewparmas);
        gsld.setNumRange(numrange);
        
        String sld = gsld.getSLD();
        
     // ************************************
     // * Writing to Session
     // ************************************
        HttpSession session = req.getSession();
        session.setAttribute(typename, sld);
        
        return sld;
    }

    @Operation(summary = "WMS Proxy Service. Accepts GeoServer WMS Parameter Requests.")
    @RequestMapping(value = "/wms", method = { RequestMethod.POST, RequestMethod.GET })
    public void wmsProxy(HttpServletRequest req,
            HttpServletResponse res) throws Exception {

        OutputStreamWriter wr = null;
        InputStream in = null;
        OutputStream out = null;

        try {
            Map<String, Object> reqMap = new HashMap<String, Object>();

            Enumeration<?> en = req.getParameterNames();
            String key = new String();

            /**
             * Converting all Map Keys into Upper Case
             **/
            while (en.hasMoreElements()) {
                key = (String) en.nextElement();
                reqMap.put(key.toUpperCase(), req.getParameter(key));
            }

            WmsParamBean wmsBean = new WmsParamBean();
            BeanUtils.populate(wmsBean, reqMap);

            if (wmsBean.getSLD_BODY().isEmpty()) {
                HttpSession session = req.getSession(true);

                /**
                 * Reading the saved SLD
                 **/
                String sessionName = wmsBean.getLAYER();

                if (sessionName.length() < 1)
                    sessionName = wmsBean.getLAYERS();

                if (session.getAttribute(sessionName) != null) {

                    wmsBean.setSLD_BODY(
                            (String) session.getAttribute(
                                    sessionName));
                    wmsBean.setSLD("");
                    wmsBean.setSTYLES("");
                }
            }
            /**
             * Generating Map from GeoServer
             **/
            URL geoURL = new URL(this.geoserverURL + "/wms");

            URLConnection geoConn = geoURL.openConnection();
            geoConn.setDoOutput(true);

            wr = new OutputStreamWriter(geoConn.getOutputStream(), "UTF-8");
            wr.write(wmsBean.getURL_PARAM());
            wr.flush();

            in = geoConn.getInputStream();
            out = res.getOutputStream();

            res.setContentType(wmsBean.getFORMAT());

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (wr != null) {
                wr.close();
            }
        }
    }
}
