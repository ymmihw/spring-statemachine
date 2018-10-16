package com.ymmihw.spring.statemachine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

public class StateMachineListener<S, E> extends StateMachineListenerAdapter<S, E> {

  public static final Logger LOGGER = LoggerFactory.getLogger(StateMachineListener.class.getName());

  @Override
  public void stateChanged(State<S, E> from, State<S, E> to) {
    if (LOGGER.isInfoEnabled()) {
      LOGGER.info("Transitioned from {} to {}", from == null ? "none" : from.getId(), to.getId());
    }
  }
}
