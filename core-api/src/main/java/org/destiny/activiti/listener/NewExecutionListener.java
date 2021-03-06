package org.destiny.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author destiny
 * destinywk@163.com
 * ------------------------------------------------------------------
 * <p></p>
 * ------------------------------------------------------------------
 * design by 2018/12/12 21:48
 * @version 1.8
 * @since JDK 1.8.0_101
 */
@Slf4j
public class NewExecutionListener implements ExecutionListener, TaskListener {
    @Override
    public void notify(DelegateExecution execution) {
        String eventName = execution.getEventName();
        log.info("eventName: [{}]", ToStringBuilder.reflectionToString(execution, ToStringStyle.JSON_STYLE));
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        log.info("delegateTask name: [{}]", delegateTask.getEventName());
    }
}
