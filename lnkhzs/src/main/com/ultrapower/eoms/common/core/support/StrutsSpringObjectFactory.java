package com.ultrapower.eoms.common.core.support;

/*
 * $Id: StrutsSpringObjectFactory.java 674498 2008-07-07 14:10:42Z mrdon $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsConstants;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.spring.SpringObjectFactory;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.ultrapower.eoms.common.core.web.IBaseAction;

/**
 * Struts object factory that integrates with Spring.
 * <p/>
 * Spring should be loaded using a web context listener
 * <code>org.springframework.web.context.ContextLoaderListener</code> defined in
 * <code>web.xml</code>.
 * 
 */
public class StrutsSpringObjectFactory extends SpringObjectFactory {
	private static final Logger LOG = LoggerFactory
			.getLogger(StrutsSpringObjectFactory.class);

	/**
	 * Constructs the spring object factory
	 * 
	 * @param autoWire
	 *            The type of autowiring to use
	 * @param alwaysAutoWire
	 *            Whether to always respect the autowiring or not
	 * @param useClassCacheStr
	 *            Whether to use the class cache or not
	 * @param servletContext
	 *            The servlet context
	 * @since 2.1.3
	 */
	@Inject
	public StrutsSpringObjectFactory(
			@Inject(value = StrutsConstants.STRUTS_OBJECTFACTORY_SPRING_AUTOWIRE, required = false) String autoWire,
			@Inject(value = StrutsConstants.STRUTS_OBJECTFACTORY_SPRING_AUTOWIRE_ALWAYS_RESPECT, required = false) String alwaysAutoWire,
			@Inject(value = StrutsConstants.STRUTS_OBJECTFACTORY_SPRING_USE_CLASS_CACHE, required = false) String useClassCacheStr,
			@Inject ServletContext servletContext) {

		super();
		boolean useClassCache = "true".equals(useClassCacheStr);
		LOG.info("Initializing Struts-Spring integration...");

		ApplicationContext appContext = (ApplicationContext) servletContext
				.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		if (appContext == null) {
			// uh oh! looks like the lifecycle listener wasn't installed. Let's
			// inform the user
			String message = "********** FATAL ERROR STARTING UP STRUTS-SPRING INTEGRATION **********\n"
					+ "Looks like the Spring listener was not configured for your web app! \n"
					+ "Nothing will work until WebApplicationContextUtils returns a valid ApplicationContext.\n"
					+ "You might need to add the following to web.xml: \n"
					+ "    <listener>\n"
					+ "        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\n"
					+ "    </listener>";
			LOG.fatal(message);
			return;
		}

		this.setApplicationContext(appContext);

		int type = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME; // default
		if ("name".equals(autoWire)) {
			type = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;
		} else if ("type".equals(autoWire)) {
			type = AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;
		} else if ("auto".equals(autoWire)) {
			type = AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT;
		} else if ("constructor".equals(autoWire)) {
			type = AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR;
		}
		this.setAutowireStrategy(type);

		this.setUseClassCache(useClassCache);

		this.setAlwaysRespectAutowireStrategy("true"
				.equalsIgnoreCase(alwaysAutoWire));

		LOG.info("... initialized Struts-Spring integration successfully");
	}

	@Override
	public Object buildBean(String beanName, Map<String, Object> extraContext,
			boolean injectInternal) throws Exception {
		Object o = null;
		try {
			if (beanName.contains(".")) {
				Class beanClazz = getClassInstance(beanName);
				o = beanClazz.newInstance();
			} else {
				Object bean = appContext.getBean(beanName);
				IBaseAction baseAction = (IBaseAction) bean;
				baseAction.clearValidate();
				o = baseAction.clone();
			}

		} catch (Exception e) {
			e.printStackTrace();
			Class beanClazz = getClassInstance(beanName);
			o = beanClazz.newInstance();
		}
		if (injectInternal) {
			injectInternalBeans(o);
		}
		return o;
	}
}
