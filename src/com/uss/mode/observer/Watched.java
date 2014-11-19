package com.uss.mode.observer;

import java.util.Observable;

class Watched extends Observable {

	int memberid=0;
	void changeState(int memberid){
		this.memberid=memberid;
		this.setChanged(); 		//标记此 Observable对象为已改变的对象
		this.notifyObservers(); //通知所有观察者
		
	}
	
	

}
