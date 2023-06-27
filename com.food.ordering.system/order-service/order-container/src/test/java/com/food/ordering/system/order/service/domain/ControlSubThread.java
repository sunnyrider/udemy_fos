package com.food.ordering.system.order.service.domain;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;

public class ControlSubThread implements Runnable {

	@Autowired
	private OrderPaymentSaga orderPaymentSaga;

	private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final int interval = 100;
    private final String threadName;

    public ControlSubThread(String name) {
        threadName = name;
    }
 
    public void start() {
        worker = new Thread(this, threadName);
        worker.start();
    }
 
    public void stop() {
        running.set(false);
    }

	@Override
	public void run() {
		running.set(true);
        while (running.get()) {
            try { 
                Thread.sleep(interval); 
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted, Failed to complete operation");
            }
            // do something here 
         } 
	}
}
