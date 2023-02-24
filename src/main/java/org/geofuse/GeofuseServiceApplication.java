package org.geofuse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@ServletComponentScan
@SpringBootApplication
public class GeofuseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeofuseServiceApplication.class, args);
	}
	
	@Bean
    public OpenAPI customOpenAPI(@Value("1.0.0") String appVersion) {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("GeofuseService API").version(appVersion)
                        .description("Dynamic SLD Generator for Thematic Maps")
                        .license(new License().name("GNU Public License v3.0")
                        .url("https://www.gnu.org/licenses/gpl-3.0.html")));
    }


}
