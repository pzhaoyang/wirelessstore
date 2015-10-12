package com.uninet.xiaoyou;

import java.util.concurrent.Semaphore;

@SuppressWarnings("serial")
public class SyncLock extends Semaphore{
	
	public SyncLock(int permits) {
		super(permits);
	}
	
	public void waitfor(){
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void goon(){
		synchronized (this) {
			notifyAll();
		}
	}
}