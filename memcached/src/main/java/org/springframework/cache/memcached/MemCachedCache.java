package org.springframework.cache.memcached;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class MemCachedCache implements Cache {
	

	private final MemCache cache;

	public MemCachedCache(MemCache memcache) {
		this.cache = memcache;
	}

	public void clear() {
	
		cache.clear();

	}

	public void evict(Object key) {

		cache.remove(key);
	}

	public ValueWrapper get(Object key) {

		Object value = this.cache.get(key);

		return ((value != null) ? new SimpleValueWrapper(value) : null);
	}

	public String getName() {
		return cache.getName();
	}

	public MemCache getNativeCache() {
		return cache;
	}

	public void put(Object key, Object value) {

		cache.put(key, value);

	}

	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}
}
