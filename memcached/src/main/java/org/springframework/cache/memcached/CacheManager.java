package org.springframework.cache.memcached;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rubyeye.xmemcached.MemcachedClient;

public class CacheManager {

	private MemcachedClient memcachedClient;

	private ConcurrentMap<String, MemCache> cacheMap = new ConcurrentHashMap<String, MemCache>();

	public CacheManager(MemcachedClient memcachedClient) {
		super();
		this.memcachedClient = memcachedClient;
	}

	public void shutdown() {
		try {
			memcachedClient.shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public MemCache getCache(String name) {

		return cacheMap.get(name);
	}

	public Collection<MemCache> getCaches() {
		MemCache cache = new MemCache("test", 120000, memcachedClient);
		cacheMap.put("test", cache);

		return cacheMap.values();
	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
