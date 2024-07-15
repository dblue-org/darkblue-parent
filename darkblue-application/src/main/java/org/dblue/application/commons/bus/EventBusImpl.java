/*
 * Copyright (c) 2023-2024. the original authors and DBLUE.ORG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dblue.application.commons.bus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Wang Chengwei
 * @since 1.0.0
 */
@Slf4j
@Component
public class EventBusImpl implements ApplicationContextAware, EventBus {

    private ApplicationContext applicationContext;

    private EventBusImpl() {

    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void fireEventAfterCommit(ApplicationEvent event) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCompletion(int status) {
                    if (status == TransactionSynchronization.STATUS_COMMITTED) {
                        log.info("事务已提交，触发事件：{}", event.getClass().getName());
                        applicationContext.publishEvent(event);
                    }
                }
            });
        } else {
            log.info("没有找到本地事务上下文，直接触发事件：{}", event.getClass().getName());
            applicationContext.publishEvent(event);
        }
    }

    public void fireEvent(ApplicationEvent event) {
        log.info("直接触发事件：{}", event.getClass().getName());
        applicationContext.publishEvent(event);
    }
}