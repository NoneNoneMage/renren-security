package io.renren.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> <br>
 *
 * @author mage<br>
 * @version 1.0<br>
 * @CreateDate 2022/5/8 <br>
 * @see io.renren.common.config <br>
 * @since R9.0<br>
 */

@Configuration
@Data
public class ApiProperties {
    @Value("${disk.storage.file.url:http://localhost:8080/admin/file/fileDownLoader?file=}")
    private String outerBaseUrl;
}
