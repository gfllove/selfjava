package com.uss.mode.observer;

/*
 * 合适场景：
 * 如：订单状态变化，发短信通知用户；调用财务接口通知财务等
 * 
 */
final class TestObserver {
	
	public static void main(String[] arg){
		
		SmsSendWatcher sms = new SmsSendWatcher();
		
		Watched watched = new Watched();
		watched.addObserver(new Watcher());
		watched.addObserver(sms);
		watched.addObserver(new MailSendWatcher());
		
		watched.changeState(10);
		
	}
	

	

}
