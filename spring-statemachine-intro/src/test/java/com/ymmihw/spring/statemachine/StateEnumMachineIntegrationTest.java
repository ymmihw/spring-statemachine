package com.ymmihw.spring.statemachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.ymmihw.spring.statemachine.applicationreview.ApplicationReviewEvents;
import com.ymmihw.spring.statemachine.applicationreview.ApplicationReviewStates;
import com.ymmihw.spring.statemachine.config.SimpleEnumStateMachineConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleEnumStateMachineConfiguration.class)
public class StateEnumMachineIntegrationTest {

  @Autowired
  private StateMachine<ApplicationReviewStates, ApplicationReviewEvents> stateMachine;

  @Before
  public void setUp() {
    stateMachine.start();
  }

  @Test
  public void whenStateMachineConfiguredWithEnums_thenStateMachineAcceptsEnumEvents() {
    assertTrue(stateMachine.sendEvent(ApplicationReviewEvents.APPROVE));
    assertEquals(ApplicationReviewStates.PRINCIPAL_REVIEW, stateMachine.getState().getId());
    assertTrue(stateMachine.sendEvent(ApplicationReviewEvents.REJECT));
    assertEquals(ApplicationReviewStates.REJECTED, stateMachine.getState().getId());
  }

  @After
  public void tearDown() {
    stateMachine.stop();
  }
}
