package com.uss.mode.observer;

import java.util.Observable;

class Watched extends Observable {

	void changeState(){
		this.setChanged();
		this.notifyObservers();
	}
	
	

}
