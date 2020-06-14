package com.digcredit.core.fsm;

import com.digcredit.core.fsm.config.OrderEvents;
import com.digcredit.core.fsm.config.OrderStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 * @author shniu
 */
@SpringBootApplication
@Slf4j
public class FsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsmApplication.class, args);
		log.info("FSM 应用启动成功!");
	}

	@Component
	public class CommandLine implements CommandLineRunner {
        private StateMachine<OrderStates, OrderEvents> stateMachine;

        @Autowired
	    public CommandLine(StateMachine<OrderStates, OrderEvents> stateMachine) {
	        this.stateMachine = stateMachine;
        }

        @Override
        public void run(String... strings) throws Exception {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>> F S M S T A R T <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.info("Initial state: {}", stateMachine.getInitialState().getId());
            log.info("Now state: {}", stateMachine.getState().getId());
            stateMachine.sendEvent(OrderEvents.PAYED);
            log.info("OrderEvents.PAYED, Now state: {}", stateMachine.getState().getId());
            stateMachine.sendEvent(OrderEvents.DELIVERY);
            log.info("OrderEvents.DELIVERY, Now state: {}", stateMachine.getState().getId());
            stateMachine.sendEvent(OrderEvents.RECEIVED);
            log.info("OrderEvents.RECEIVED, Now state: {}", stateMachine.getState().getId());
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>> F S M E N D <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        }
    }

}

