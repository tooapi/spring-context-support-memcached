package org.springframework.cache.memcached;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class MemcachedManagerFactoryBean implements FactoryBean<CacheManager>,
		InitializingBean, DisposableBean {
	protected final Log logger = LogFactory.getLog(super.getClass());
	private CacheManager cacheManager;

	private MemcachedClient memcachedClient;

	public CacheManager getObject() throws Exception {
		System.out.println("MemcachedManagerFactoryBean.getObject");
		return this.cacheManager;
	}

	public Class<?> getObjectType() {

		return (this.cacheManager != null) ? this.cacheManager.getClass()
				: CacheManager.class;

	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("MemcachedManagerFactoryBean.afterPropertiesSet");
		this.cacheManager = new CacheManager(memcachedClient);

	}

	public void destroy() throws Exception {
		logger.info("Shutting down Memcached CacheManager");
		this.cacheManager.shutdown();

	}

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

}
