package org.springframework.cache.memcached;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Configuration {

	private final Map<String, CacheConfiguration> cacheConfigurations = new ConcurrentHashMap<String, CacheConfiguration>();

	public final Map<String, CacheConfiguration> getCacheConfigurations() {
		return this.cacheConfigurations;
	}

}
