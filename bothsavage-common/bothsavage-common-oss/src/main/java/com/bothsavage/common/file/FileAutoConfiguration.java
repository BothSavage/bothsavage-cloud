

package com.bothsavage.common.file;

import com.bothsavage.common.file.core.FileProperties;
import com.bothsavage.common.file.local.LocalFileAutoConfiguration;
import com.bothsavage.common.file.oss.OssAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

/**
 * aws 自动配置类
 *


 */
@Import({ LocalFileAutoConfiguration.class, OssAutoConfiguration.class })
@EnableConfigurationProperties({ FileProperties.class })
public class FileAutoConfiguration {

}
