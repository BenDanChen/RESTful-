package org.cc11001100.web.test.main.cli;

import org.cc11001100.web.test.utils.cli.CommandLineInterfaceUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * �������������
 * 
 * @author chenjc20326
 *
 */
public class MainCommandLineInterface {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		CommandLineInterfaceUtil commandLineInterfaceUtil=context.getBean(CommandLineInterfaceUtil.class);
		commandLineInterfaceUtil.process(args);
		
	}
	
}
