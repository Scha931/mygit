package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerBean {
	@Before("within(cn.tedu.cloud_note.controller..*)")
	public void logController(){
		System.out.println("AOP¹¦ÄÜ×¢Èë£¡");
	}
}
