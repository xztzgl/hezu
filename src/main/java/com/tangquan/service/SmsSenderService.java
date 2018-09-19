package com.tangquan.service;

import com.tangquan.service.impl.SmsParameter;
import com.tangquan.service.impl.SmsSendResult;

/**
 * ADI短信发送器
 * @author LIQIU
 *
 */
public interface SmsSenderService {

	/**
	 * 发送短信
	 * @param parameter
	 * @return
	 */
	public SmsSendResult send(SmsParameter parameter);
//	public void setAccessKeyId(String accessKeyId);
//	public void setAccessKeySecret(String accessKeySecret);
}
