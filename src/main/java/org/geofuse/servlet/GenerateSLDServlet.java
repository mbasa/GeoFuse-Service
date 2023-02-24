/**
 * パッケージ名：org.geofuse.servlet
 * ファイル名  ：GenerateSLDServlet.java
 * 
 * @author mbasa
 * @since Aug 25, 2022
 */
package org.geofuse.servlet;

import java.awt.Color;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.geofuse.color.RangeColor;
import org.geofuse.sld.ThematicSLD;
import org.geofuse.util.ConfigProperties;
import org.geofuse.wfsUtil.WfsRangeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 説明：
 *
 */
@WebServlet(urlPatterns = "/generateSld/*", loadOnStartup = 1)
public class GenerateSLDServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private String geoserverURL = new String();
    private Map<String, String[]> colorMap = new HashMap<String, String[]>();

    @Autowired
    ConfigProperties configProp;
    
    /**
     * コンストラクタ
     *
     */
    public GenerateSLDServlet() {
        super();
    }
/*
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.geoserverURL = configProp.getConfigValue("GEOSERVER.BASE.URL");

        String[] colorNames = 
                configProp.getConfigValue("THEMATIC.COLOR.NAMES").split(",");
        String[] colorVals = 
                configProp.getConfigValue("THEMATIC.COLORS").split(",");

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
        
        logger.info("*** Starting GenerateSLDServlet Servlet. GeoServer URL "
                + geoserverURL + " ***");
    }
*/
    /*
     * (非 Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Color colorFrom = Color.YELLOW;
        Color colorTo = Color.RED;
        String typeName = new String();
        String geoType = new String();
        String propName = new String();
        String typeRange = "EQRange";
        String cqlString = "";
        String viewParams = "";
        String labScale = "";
        int numRange = 6;

        // ************************************
        // * Getting Request Parameters
        // ************************************
        if (request.getParameter("typename") != null) {
            typeName = request.getParameter("typename");
        }
        if (request.getParameter("geotype") != null) {
            geoType = request.getParameter("geotype");
        }
        if (request.getParameter("propname") != null) {
            propName = request.getParameter("propname");
            try {
                byte[] byteData = propName.getBytes("ISO_8859_1");
                propName = new String(byteData, "UTF-8");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (request.getParameter("typerange") != null) {
            typeRange = request.getParameter("typerange");
        }
        if (request.getParameter("labscale") != null) {
            labScale = request.getParameter("labscale");
        }
        if (request.getParameter("cql") != null) {
            cqlString = request.getParameter("cql");
            try {
                byte[] byteData = cqlString.getBytes("ISO_8859_1");
                cqlString = new String(byteData, "UTF-8");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (request.getParameter("viewparams") != null) {
            viewParams = request.getParameter("viewparams");
            try {
                byte[] byteData = viewParams.getBytes("ISO_8859_1");
                viewParams = new String(byteData, "UTF-8");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (request.getParameter("numrange") != null) {
            numRange = Integer.parseInt(request.getParameter("numrange"));
        }
        if (request.getParameter("color") != null) {
            String theme = request.getParameter("color");

            try {
                byte[] byteData = theme.getBytes("ISO_8859_1");
                theme = new String(byteData, "UTF-8");
            } catch (Exception e) {
                System.out.println(e);
            }

            if (this.colorMap.containsKey(theme)) {
                String[] colorVal = this.colorMap.get(theme);
                colorFrom = Color.decode(colorVal[0]);
                colorTo = Color.decode(colorVal[1]);
            }
        }

        // ************************************
        // * Setting Range Values
        // ************************************
        ArrayList<Double> ranges = new ArrayList<Double>();

        ranges = WfsRangeList.createRangeList(
                this.geoserverURL,
                typeName, propName, cqlString,
                viewParams, typeRange, numRange);

        // ************************************
        // * Setting Range Colors
        // ************************************
        ArrayList<String> colors = RangeColor.createRangeColors(
                colorFrom, colorTo, ranges.size());

        // ************************************
        // * Creating the SLD
        // ************************************

        ThematicSLD sld = new ThematicSLD();
        StringBuffer sldBuff = sld.addHeader(typeName);

        int i;

        int ptRng = (int) Math.ceil((19 - 4) / (ranges.size() - 1));
        int ptSize = 5 + ptRng; // min size is 5

        for (i = 0; i < (ranges.size() - 2); i++) {
            if (geoType.compareToIgnoreCase("point") == 0) {

                sld.setPointSize(ptSize);
                ptSize += ptRng;

                if (ptSize > 17)
                    ptSize = 17;
            }
            sldBuff = sld.addRule(sldBuff, propName,
                    ranges.get(i).doubleValue(),
                    ranges.get(i + 1).doubleValue(),
                    colors.get(i), false, geoType);
        }

        if (geoType.compareToIgnoreCase("point") == 0) {
            if (ptSize < 17)
                ptSize = 17;

            sld.setPointSize(ptSize);
        }
        sldBuff = sld.addRule(sldBuff, propName,
                ranges.get(i).doubleValue(),
                ranges.get(i + 1).doubleValue(),
                colors.get(i), true, geoType);

        if (labScale.length() > 0) {
            sldBuff = sld.addLabelRule(sldBuff, labScale, propName);
        }
        sldBuff = sld.addFooter(sldBuff);

        // ************************************
        // * Writing to Session
        // ************************************
        String tSld = sldBuff.toString();

        HttpSession session = request.getSession();
        session.setAttribute(typeName, tSld);

        response.setContentType("text/text");
        response.getWriter().write(session.getId());
    }
}
