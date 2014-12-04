package org.springframework.cache.memcached;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.util.Assert;

public class MemcachedCacheCacheManager extends
		AbstractTransactionSupportingCacheManager {

	private CacheManager cacheManager;

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public CacheManager getCacheManager() {
		return this.cacheManager;
	}


	protected Collection<? extends Cache> loadCaches() {

		Assert.notNull(this.cacheManager, "A backing Memcached CacheManager is required");
		Collection<Cache> caches = new LinkedHashSet<Cache>();
		for (MemCache memcache : cacheManager.getCaches()) {
			caches.add(new MemCachedCache(memcache));
		}
		return caches;
	}

	public Cache getCache(String name) {
		Cache cache = super.getCache(name);
		if (cache == null) {
			MemCache memcache = cacheManager.getCache(name);
			if (memcache != null) {
				cache = new MemCachedCache(memcache);
				addCache(cache);
			}
		}
		return cache;
	}

}
