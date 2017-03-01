package org.cc11001100.web.test.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cc11001100.web.test.domain.ResponseDO;
import org.cc11001100.web.test.handler.HttpRequestHandler;

/**
 * ���ڵ���url�����и��ִ����
 * 
 * 
 * @author chenjc20326
 *
 */
public class UrlScheduler {

	
	/**
	 * ɵ���������������
	 * 
	 * @param urls
	 * @param params
	 * @return
	 */
	public static List<ResponseDO> stupidBlockQueueScheduler(Set<String> urls, Map<String, String> globalParams){
		List<ResponseDO> res=new ArrayList<>();
		for(String url : urls){
			//ÿ������Ҫ����ȫ�ֲ���������Ҫ������Ⱦȫ�ֲ���
			Map<String,String> currentParams=new HashMap<>();
			currentParams.putAll(globalParams);
			ResponseDO responseDO=new HttpRequestHandler().handle(url, currentParams);
			res.add(responseDO);
		}
		return res;
	}
	
	// ���ӹ���������
//	/**
//	 * ʹ��ͳһ�Ĳ�����䣬������������
//	 * @param urls
//	 * @return
//	 */
//	public List<ResponseDO> scheduler(Set<String> urls, Map<String, String> params){
//		//��ΪĬ��������ʽ�ģ���urls.size()�ܴ�ʱ�ٶȿ����������ܣ��ʲ�������
//		
//		ExecutorService executorService=Executors.newFixedThreadPool(100);
//		List<Future<ResponseDO>> list=new ArrayList<>();
//		for(String url : urls){
//			Future<ResponseDO> future=executorService.submit(new UrlSchedulerThreadPoolTask(url,params));
//			list.add(future);
//		}
//		
//		List<ResponseDO> res=new ArrayList<>();
//		
//		while(!list.isEmpty()){
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			};
//			for(int i=list.size()-1;i>=0;i--){
//				Future<ResponseDO> future=list.get(i);
//				if(future.isDone()){
//					try {
//						res.add(future.get());
//						list.remove(future);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					} catch (ExecutionException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		
//		//�ص�����
//		executorService.shutdown();
//		return res;
//	}
//	
//	/**
//	 * ������
//	 * 
//	 * @author chenjc20326
//	 *
//	 */
//	private class UrlSchedulerThreadPoolTask implements Callable<ResponseDO> {
//
//		private String url;
//		private Map<String, String> params;
//		
//		public UrlSchedulerThreadPoolTask(String url, Map<String, String> params) {
//			this.url = url;
//			this.params = params;
//		}
//
//		@Override
//		public ResponseDO call() throws Exception {
//			return new HttpRequest().post(url, params);
//		}
//		
//	}
	
}
