package com.ymmihw.spring.statemachine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

@Configuration
@EnableStateMachine
public class ForkJoinStateMachineConfiguration
    extends StateMachineConfigurerAdapter<String, String> {

  private static final String SUB2_1 = "Sub2-1";
  private static final String SUB2_2 = "Sub2-2";
  private static final String SUB1_1 = "Sub1-1";
  private static final String S_FORK = "SFork";
  private static final String SUB1_2 = "Sub1-2";

  @Override
  public void configure(StateMachineConfigurationConfigurer<String, String> config)
      throws Exception {
    config.withConfiguration().autoStartup(true).listener(new StateMachineListener<>());
  }

  @Override
  public void configure(StateMachineStateConfigurer<String, String> states) throws Exception {
    states.withStates().initial("SI").fork(S_FORK).join("SJoin").end("SF").and().withStates()
        .parent(S_FORK).initial(SUB1_1).end(SUB1_2).and().withStates().parent(S_FORK)
        .initial(SUB2_1).end(SUB2_2);
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<String, String> transitions)
      throws Exception {
    transitions.withExternal().source("SI").target(S_FORK).event("E1").and().withExternal()
        .source(SUB1_1).target(SUB1_2).event("sub1").and().withExternal().source(SUB2_1)
        .target(SUB2_2).event("sub2").and().withFork().source(S_FORK).target(SUB1_1).target(SUB2_1)
        .and().withJoin().source(SUB1_2).source(SUB2_2).target("SJoin");
  }

  @Bean
  public Guard<String, String> mediumGuard() {
    return ctx -> false;
  }

  @Bean
  public Guard<String, String> highGuard() {
    return ctx -> false;
  }
}
