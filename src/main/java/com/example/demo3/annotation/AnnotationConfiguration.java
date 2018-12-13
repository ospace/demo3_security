package com.example.demo3.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.core.annotation.AnnotationUtils;

//@Configuration
public class AnnotationConfiguration extends AbstractPointcutAdvisor implements IntroductionAdvisor/*, BeanFactoryAware*/ {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationConfiguration.class);
	
	private static final long serialVersionUID = -4528767584639351076L;
//	private BeanFactory beanFactory;
	private Advice      advice;
	private Pointcut    pointcut;
	
	class AnnotationPointcut implements Pointcut {
		private final MethodMatcher methodMatcher;
		private final ClassFilter   classFilter;
		
		public AnnotationPointcut() {
			this.methodMatcher = new AnnotationMethodMatcher(TimeLog.class);
			this.classFilter = new AnnotationClassFilter(TimeLog.class, true) {
				@Override
				public boolean matches(Class<?> clazz) {
//					LOGGER.info("AnnotationClassFilter.matches - {}()", clazz.getSimpleName());
					try {
						boolean found = false;
						for(Method it : clazz.getMethods()) {
							Annotation annotation = AnnotationUtils.findAnnotation(it, TimeLog.class);
							if(null != annotation) {
								LOGGER.info("AnnotationClassFilter.found - {}", clazz.getSimpleName());
								found = true;
								break;
							}
						}
						return found;
					} catch(Exception e) {
						LOGGER.warn("AnnotationClassFilter.exception : {}", e.getMessage(), e);
						throw e;
					}
				}
			};
		}


		@Override
		public ClassFilter getClassFilter() {
			return classFilter;
		}

		@Override
		public MethodMatcher getMethodMatcher() {
			return methodMatcher;
		}
		
	}
	
	@PostConstruct
	private void init() {
		LOGGER.info("@PostConstruct");
		 
//		Pointcut pointcut1 = new AnnotationClassOrMethodPointcut();
		Pointcut pointcut1 = new AnnotationPointcut();
		ComposablePointcut compPointcut = new ComposablePointcut(pointcut1);
//		compPointcut.union(pointcut1);
		this.pointcut = compPointcut;
		
		AnnotationInterceptor interceptor = new AnnotationInterceptor();
//		interceptor.setBeanFactory(beanFactory);
		
		this.advice = interceptor;
	}
	
	@Override
	public Advice getAdvice() {
//		LOGGER.info("getAdvice");
		return this.advice;
	}

	@Override
	public Class<?>[] getInterfaces() {
//		LOGGER.info("getInterfaces");
		// TODO Auto-generated method stub
//		return new Class[] {TimeLog.class};
		return new Class[] {};
	}

	@Override
	public Pointcut getPointcut() {
//		LOGGER.info("getPointcut");
		return this.pointcut;
	}

//	@Override
//	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		LOGGER.info("setBeanFactory : {}", beanFactory);
//		this.beanFactory = beanFactory;
//		
//	}

	@Override
	public ClassFilter getClassFilter() {
//		LOGGER.info("getClassFilter");
		return pointcut.getClassFilter();
	}

	@Override
	public void validateInterfaces() throws IllegalArgumentException {
//		LOGGER.info("validateInterfaces");
	}

}
