/**
 * パッケージ名：org.geofuse.servlet
 * ファイル名  ：WmsProxyServlet.java
 * 
 * @author mbasa
 * @since Aug 24, 2022
 */
package org.geofuse.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.geofuse.bean.WmsParamBean;
import org.geofuse.util.ConfigProperties;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 説明：
 *
 */

@WebServlet(urlPatterns = "/wms/*", loadOnStartup = 1)
public class WmsProxyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ConfigProperties configProp;

    private String geoserverURL = new String();

    /**
     * コンストラクタ
     *
     */
    public WmsProxyServlet() {
    }
/*
    @Override
    public void init() throws ServletException {

        String url = configProp.getConfigValue("GEOSERVER.BASE.URL");

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        this.geoserverURL = url + "/wms";

        super.init();
        logger.info("*** Starting WMS Servlet. GeoServer URL "
                + geoserverURL + " ***");
    }
*/
    /*
     * (非 Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        this.geoserverURL = "http://localhost:8080/geoserver/wms";
        
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
            URL geoURL = new URL(this.geoserverURL);

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
