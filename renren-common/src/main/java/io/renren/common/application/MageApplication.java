package io.renren.common.application;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/1 <br>
 * @see io.renren.common.application <br>
 * @since R9.0<br>
 */
public class MageApplication extends SpringBootServletInitializer {

    private static final String HOME_KEY = "MAGE_HOME";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass()).bannerMode(Banner.Mode.OFF).properties("spring.config.name:application-additional-location", getAdditionalLocation());
    }

    private static String getAdditionalLocation() {
        //		如果file: 与resource目录相同优先使用file:
        String mageHome = System.getenv(HOME_KEY);
        String addClassPath = "spring.config.additional-location:" +
                "file:./config/,file:./," +
                "classpath:/config/," +
                "classpath:/," +
                "file:" + mageHome + File.separator + "etc" + File.separator + ",";
        return addClassPath;
    }
}
