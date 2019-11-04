package com.digcredit.core.fsm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * Fsm configuration
 * Created by Administrator on 2018/12/28 0028.
 */
@Configuration
@EnableStateMachine
@Slf4j
public class FsmConfig extends EnumStateMachineConfigurerAdapter<OrderStates, OrderEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config)
            throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states)
            throws Exception {
        states.withStates()
                .initial(OrderStates.WAIT_PAYMENT)
                .end(OrderStates.FINISH)
                .states(EnumSet.allOf(OrderStates.class));
    }

    public void configure(StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions)
            throws Exception {
        transitions.withExternal()
                .source(OrderStates.WAIT_PAYMENT).target(OrderStates.WAIT_DELIVER).event(OrderEvents.PAYED).guard(payGuard()).action(action())
                .and()
                .withExternal()
                .source(OrderStates.WAIT_DELIVER).target(OrderStates.WAIT_RECEIVE).event(OrderEvents.DELIVERY)
                .and()
                .withExternal()
                .source(OrderStates.WAIT_RECEIVE).target(OrderStates.FINISH).event(OrderEvents.RECEIVED);
    }

    /**
     * Interface Guard is used to do an evaluation where method has access to a StateContext.
     *
     * @return Guard's implementation
     */
    @Bean
    public Guard<OrderStates, OrderEvents> payGuard() {
        return stateContext -> stateContext.getSource().getId().equals(OrderStates.WAIT_PAYMENT);
    }

    @Bean
    public Action<OrderStates, OrderEvents> action() {
        return new Action<OrderStates, OrderEvents>() {
            @Override
            public void execute(StateContext<OrderStates, OrderEvents> stateContext) {
                log.info(">>>>>>>>>>>>>>>>>>> A C T I O N <<<<<<<<<<<<<<<<<<");
            }
        };
    }

    @Bean
    public StateMachineListener<OrderStates, OrderEvents> listener() {
        return new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
            @Override
            public void stateChanged(State<OrderStates, OrderEvents> from, State<OrderStates, OrderEvents> to) {
                log.info("State change to {}", to.getId());
            }
        };
    }

}
