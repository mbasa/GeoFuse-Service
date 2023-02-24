/**
 * パッケージ名：org.geofuse.util
 * ファイル名  ：ConfigProperties.java
 * 
 * @author mbasa
 * @since Aug 25, 2022
 */
package org.geofuse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 説明：
 *
 */

@Component
@PropertySource("classpath:application.properties")
public class ConfigProperties {

    @Autowired
    private Environment env;

    public String getConfigValue(String configKey){
        return env.getProperty(configKey);
    }

}
