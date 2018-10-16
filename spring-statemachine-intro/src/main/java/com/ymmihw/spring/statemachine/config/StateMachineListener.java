package com.ymmihw.spring.statemachine.config;

import java.util.logging.Logger;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

public class StateMachineListener<S, E> extends StateMachineListenerAdapter<S, E> {

  public static final Logger LOGGER = Logger.getLogger(StateMachineListener.class.getName());

  @Override
  public void stateChanged(State<S, E> from, State<S, E> to) {
    LOGGER.info(String.format("Transitioned from %s to %s%n", from == null ? "none" : from.getId(),
        to.getId()));
  }
}
