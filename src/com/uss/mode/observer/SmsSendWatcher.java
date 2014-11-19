/**
 * 
 */
package com.uss.mode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author gfl
 *
 */
public class SmsSendWatcher implements Observer {

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		Watched watched = (Watched)o;
		System.out.println("SMS send succeed!memberid="+watched.memberid);

	}

}
