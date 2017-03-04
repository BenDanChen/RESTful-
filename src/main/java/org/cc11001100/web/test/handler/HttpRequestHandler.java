package org.cc11001100.web.test.handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.cc11001100.web.test.domain.ParamPair;
import org.cc11001100.web.test.domain.ResponseDO;
import org.cc11001100.web.test.process.ParamLackProcessor;
import org.cc11001100.web.test.process.ParamLackProcessor4Jetty;

/**
 * ��������http����Ĺ�����
 * 
 * @author chenjc20326
 *
 */
public class HttpRequestHandler implements RequestHandler {

	private Logger logger=Logger.getLogger(HttpRequestHandler.class);
	
	/** ��������200ʱ������Դ��� */
	private Integer LACK_PARAM_MAX_TRY=100;

	/** Ҫʹ�õ���һЩ����ȱʧ��������������һ�����ϣ�ʹ�õ�ʱ������ֱ���ҵ�һ���ܹ��������ʹ��  */
	private List<ParamLackProcessor> paramLackProcessors=new ArrayList<>();
	
	public HttpRequestHandler() {
		//װ��Ҫʹ�õĲ��������� 
		paramLackProcessors.add(new ParamLackProcessor4Jetty());
	}

	/**
	 * ����url�Ͳ���������һ������ʹ��post��ʽ
	 * 
	 * @param url
	 * @param params
	 */
	private ResponseDO post(String url, Map<String, String> params) {
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(url);
		
		//���ò���
		List<NameValuePair> formParams=new ArrayList<>();
		for(Iterator<Entry<String, String>> iterator=params.entrySet().iterator();iterator.hasNext();){
			Entry<String, String> entry=iterator.next();
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		try {
			UrlEncodedFormEntity uefEntity=new UrlEncodedFormEntity(formParams, "UTF-8");
			httppost.setEntity(uefEntity);
			
			// ����ȥ��������
			httppost.setHeader("Cache-Control", "no-cache");
			httppost.setHeader("Pragma", "no-cache");
			httppost.setHeader("Cache-Control", "no-store");
			
			//�����ռ����践������
			ResponseDO res=new ResponseDO(url);
			
			//������ʹ�õĲ���
			res.setParams(params);
			
			//ͳ�����󻨷ѵ�ʱ�䣬��Ϊ�ǵ��̻߳����������
			long start=System.currentTimeMillis();
			CloseableHttpResponse response=httpclient.execute(httppost);
			long spend=System.currentTimeMillis()-start;
			res.setSpend(spend);
			
			//�ռ����ص�״̬��
			HttpEntity entity=response.getEntity();
			res.setStatus(response.getStatusLine().getStatusCode());
			
			if (entity != null) {
				String responseContent=EntityUtils.toString(entity, "UTF-8");
				res.setResponseContent(responseContent);
			}
			
			// log
			StringBuilder mesg=new StringBuilder();
			mesg.append("URL [").append(url).append("] request done.");
			logger.info(mesg.toString());
			
			return res;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ر�����,�ͷ���Դ
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public ResponseDO handle(String url, Map<String, String> params) {
		
		ResponseDO responseDO=null;
		for(int i=0;i<LACK_PARAM_MAX_TRY;i++){
			
			responseDO=post(url, params);
			if(responseDO==null){
				continue;
			}else if(responseDO.getStatus()==HttpStatus.SC_OK){
				return responseDO;
			}else if(!processorLackedParam(responseDO)){
				//����ȱʧ���������������������еĶ����ܽ�����ʱ��ֱ�ӽ��������ԣ������Ѿ����ܸ㵽��
				break;
			}
			
		}
		
		return responseDO;
	}
	
	/**
	 * ʹ��ȱʧ�����������������������ķ�����Ϣ���Եõ�һ�����еķ���
	 * 
	 * @param responseDO
	 * @return
	 */
	private boolean processorLackedParam(ResponseDO responseDO){
		for(ParamLackProcessor paramLackProcessor : paramLackProcessors){
			if(paramLackProcessor.canProcess(responseDO)){
				ParamPair paramPair=paramLackProcessor.process(responseDO);
				if(paramPair!=null){
					responseDO.getParams().put(paramPair.getName(), paramPair.getValue());
					return true;
				}
			}
		}
		return false;
	}
	
	// �첽ȥ��!!!
//	/**
//	 * ����url�Ͳ���������һ������ʹ��post��ʽ
//	 * 
//	 * @param url
//	 * @param params
//	 */
//	public ResponseDO post(String url, Map<String, String> params) {
//		
//		System.out.println(Thread.currentThread().getId());
//		
//		AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
//		BoundRequestBuilder boundRequestBuilder=asyncHttpClient.preparePost(url);
//		
//		//���ò���
//		List<Param> formParams=new ArrayList<>();
//		for(Iterator<Entry<String, String>> iterator=params.entrySet().iterator();iterator.hasNext();){
//			Entry<String, String> entry=iterator.next();
//			formParams.add(new Param(entry.getKey(), entry.getValue()));
//		}
//		boundRequestBuilder.setFormParams(formParams);
//		
//		try {
//			// ����ȥ��������
//			boundRequestBuilder.setHeader("Cache-Control", "no-cache");
//			boundRequestBuilder.setHeader("Pragma", "no-cache");
//			boundRequestBuilder.setHeader("Cache-Control", "no-store");
//			
//			long start=new Date().getTime();
//			ListenableFuture<Response> listenableFuture=boundRequestBuilder.execute();
//			while(!listenableFuture.isDone()){
//				TimeUnit.MILLISECONDS.sleep(1);
//			}
//			long spend=new Date().getTime()-start;
//			
//			Response response=listenableFuture.get();
//			
//			//�����ռ����践������
//			ResponseDO res=new ResponseDO(url);
//			res.setSpend(spend);
//			res.setStatus(response.getStatusCode());
//			res.setResponseContent(response.getResponseBody());
//			
//			return res;
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
}
