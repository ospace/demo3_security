package com.example.demo3.annotation;

import org.aopalliance.intercept.MethodInvocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aop.IntroductionInterceptor;

public class AnnotationInterceptor implements IntroductionInterceptor/*, BeanFactoryAware */{
	private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationInterceptor.class);
	
//	private final StandardEvaluationContext evaluationCtx = new StandardEvaluationContext(); 
//	private BeanFactory beanFactory;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		LOGGER.info("invoke : {}", invocation);
		return invocation.proceed();
	}

	@Override
	public boolean implementsInterface(Class<?> arg0) {
		LOGGER.info("implementsInterface : arg[{}]", arg0);
		return false;
	}

//	@Override
//	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//		LOGGER.info("setBeanFactory : {}", beanFactory);
//		// TODO Auto-generated method stub
//		this.beanFactory = beanFactory;
//		this.evaluationCtx.setBeanResolver(new BeanFactoryResolver(this.beanFactory));
//	}

}
