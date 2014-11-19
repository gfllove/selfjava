package com.uss.mode.observer;

import java.util.Observable;
import java.util.Observer;

public class MailSendWatcher implements Observer {

	@Override
	public void update(Observable o, Object arg) {

		Watched watched = (Watched)o;
		System.out.println("mail send succeed,memberid="+watched.memberid);
	}

}
