/**
 * 
 */
package comf.farkalit.student.config.cache;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

/**
 * @File name: RedisCacheManagerResolver.java
 * This class .....
 *
 * @author name: Farkalit Usman (S785410)
 * @Created on: 23 May 2019
 */
@Configuration
@EnableCaching
public class RedisCacheManagerResolver {

private final static Logger LOG = LoggerFactory.getLogger(RedisCacheManagerResolver.class);
	
	@Value("${spring.redis.host}")
	String redisHost;
	
	@Value("${spring.redis.port}")
	int redisPort;

	@Value("${spring.redis.password}")
	String redisPassword;
	
	@Value("${spring.cache.redis.time-to-live}")
	int redisCacheTTL;
	
	@Bean
	@ConditionalOnProperty(name="${spring.cache.type}", havingValue="redis")
	public LettuceConnectionFactory redisConnectionFactory() {
		LOG.debug("LettuceConnectionFactory.redisConnectionFactory...");
		RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
		LOG.debug("LettuceConnectionFactory.redisHost:{} , redisPort:{}",redisHost, redisPort);
		redisConf.setHostName(redisHost);
		redisConf.setPort(redisPort);
		redisConf.setPassword(RedisPassword.of(redisPassword));
		return new LettuceConnectionFactory(redisConf);
	}

	public RedisCacheConfiguration cacheConfiguration() {
		LOG.debug("RedisCacheConfiguration.cacheConfiguration...");
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(redisCacheTTL)).disableCachingNullValues();
		SerializationPair<String> keySerializationPair = cacheConfig.getKeySerializationPair();
		cacheConfig.serializeKeysWith(keySerializationPair);
		return cacheConfig;
	}

	@Bean
    @ConditionalOnProperty(name="${spring.cache.type}", havingValue="redis")
	public RedisCacheManager cacheManager() {
		LOG.debug("RedisCacheManager.cacheManager...");
		RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(cacheConfiguration())
				.transactionAware().build();
		LOG.debug("RedisCacheManager.cacheManager...built:{}",rcm);
		return rcm;
	}
}
