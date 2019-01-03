package tb.blog.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.nio.charset.Charset;

@Configuration
@ConditionalOnClass({JSON.class})
public class FastJsonHttpMessageConvertersConfiguration {

    @Configuration
    @ConditionalOnClass({FastJsonHttpMessageConverter.class})
    @ConditionalOnProperty(
            name = {"spring.http.converters.preferred-json-mapper"},
            havingValue = "fastjson",
            matchIfMissing = true
    )
    protected static class FastJson2HttpMessageConverterConfiguration{
        protected FastJson2HttpMessageConverterConfiguration() {
        }

        @Bean
        @ConditionalOnMissingBean({FastJsonHttpMessageConverter.class})
        public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
            FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

            FastJsonConfig fastJsonConfig = new FastJsonConfig();
            fastJsonConfig.setSerializerFeatures(
                    SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteMapNullValue
            );

            SerializeConfig serializeConfig = SerializeConfig.globalInstance;
            serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
            serializeConfig.put(Long.class, ToStringSerializer.instance);
            serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
            fastJsonConfig.setSerializeConfig(serializeConfig);
            fastJsonConfig.setCharset(Charset.forName("UTF-8"));
            fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
            converter.setFastJsonConfig(fastJsonConfig);
            return converter;
        }
    }
}
