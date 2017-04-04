package org.cc11001100.web.test.utils.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.stereotype.Component;

/**
 * 
 * ������������������Ĺ�����
 * 
 * @author chenjc20326
 *
 */
@Component
public class CommandLineInterfaceUtil {

	/**
	 * ���ݴ���Ĳ������������д���
	 * 
	 * @param args
	 */
	public void process(String[] args) {
		

		
		
	}
	
	/**
	 * �������
	 * 
	 * @param args
	 */
	private void processArgs(String[] args){
		
		Options options=new Options();
		
		try {
			CommandLineParser parser=new DefaultParser();
			CommandLine cmd=parser.parse(options, args);
			
			if(cmd.hasOption("urls")){
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * ��ʼ��ѡ��
	 * 
	 * @return
	 */
	private Options initOptions(){
		
		Options options=new Options();

		//urlsӳ�� (��ѡ)
		Option urls=Option.builder("u").longOpt("urls").hasArg().argName("urls")
					.desc("Will test url set in a file line by line.").required().build();
		
		//ȫ�ֲ���(��ѡ)
		Option globalParams=Option.builder("g").longOpt("globalParams").hasArg().argName("globalParams")
				.desc("Every http request will use this params.").build();
		
		//���·��(��ѡ)
		Option output=Option.builder("o").longOpt("output").hasArg().argName("outputPath")
				.desc("Result export to where?").required().build();
		
		//������(��ѡ)
		Option host=Option.builder("h").longOpt("host").hasArg().argName("host").desc("Host").build();
		
		//�˿�(��ѡ)
		Option post=Option.builder("p").longOpt("post").hasArg().argName("post").desc("Post").required().build();
		
		//����(��ѡ)
		Option help=Option.builder("h").longOpt("help").desc("Get help.").build();
		
		options.addOption(urls);
		options.addOption(globalParams);
		options.addOption(output);
		options.addOption(host);
		options.addOption(post);
		options.addOption(help);
		
		return options;
	}
	
}
