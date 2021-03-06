package org.destiny.activiti;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.ActivitiRule;
import org.destiny.activiti.cmd.JumpCmd;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @author wangkang
 * @version 1.8.0_191
 * create by 2019-03-11 10:41
 * --------------------------------------------------------------
 * <p>
 * --------------------------------------------------------------
 * Copyright: Copyright (c) 2019
 */
@Slf4j
public class MultiJumpTest {

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    public void testDeploy() {
        Deployment deploy = activitiRule.getRepositoryService()
                .createDeployment()
                .addClasspathResource("org/destiny/activiti/my-process-jump.bpmn20.xml")
                .deploy();

        log.info("deploy: {}", deploy);

        Map<String, Object> map = Maps.newHashMap();
        map.put("userList1", Arrays.asList("destiny1", "freedom1", "justice1"));
        map.put("userList2", Arrays.asList("destiny2", "freedom2", "justice2"));
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("multi-jump", map);
        log.info("processInstance: {}", processInstance);
    }

    @Test
    public void testTaskList() {
        activitiRule.getTaskService()
                .createTaskQuery()
                .processInstanceId("")
                .list();
    }

    @Test
    public void testComplete() {
        activitiRule.getTaskService().complete("152526");
    }

    @Test
    public void testJump() {
        activitiRule.getManagementService().executeCommand(new JumpCmd("155011", "task1"));
    }
}
