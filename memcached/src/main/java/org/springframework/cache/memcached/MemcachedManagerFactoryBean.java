package org.springframework.cache.memcached;

import java.io.InputStream;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class MemcachedManagerFactoryBean implements FactoryBean<CacheManager>,
		InitializingBean, DisposableBean {

	protected final Log logger = LogFactory.getLog(super.getClass());

	private Resource configLocation;
	private CacheManager cacheManager;
	private MemcachedClient memcachedClient;

	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public CacheManager getObject() throws Exception {

		return cacheManager;
	}

	public Class<?> getObjectType() {

		return (cacheManager != null) ? cacheManager.getClass(): CacheManager.class;

	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		logger.info("Initializing  Memcached CacheManager");
		InputStream input = (this.configLocation != null) ? this.configLocation.getInputStream() : null;
		try
	    {
	      Configuration configuration = (input != null) ? ConfigurationFactory.parse(input) : ConfigurationFactory.parse();
	      this.cacheManager = new CacheManager(configuration,memcachedClient);
	    }
	    finally
	    {
	      if (input != null)
	    	  input.close();
	    }

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
