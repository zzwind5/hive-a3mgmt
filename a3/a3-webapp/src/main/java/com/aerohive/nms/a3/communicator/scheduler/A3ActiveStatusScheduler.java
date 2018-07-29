package com.aerohive.nms.a3.communicator.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aerohive.nms.a3.communicator.handler.A3ActiveStatusHander;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class A3ActiveStatusScheduler {
	
	@Autowired
	private A3ActiveStatusHander activeStatusHandler;

	@Scheduled(cron="${A3.status.scan.corn}")
	private void scanStatus() {
		try {
			activeStatusHandler.disconnectHandle();
		} catch(Throwable t) {
			log.error("Scan A3 active status failed.", t);
		}
	}
}
