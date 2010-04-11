/*
 * Copyright (c) 2010. Axon Framework
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

package org.axonframework.domain;

import org.axonframework.eventsourcing.AbstractEventSourcedAggregateRoot;
import org.axonframework.eventstore.SnapshotProducer;

import java.util.UUID;

/**
 * @author Allard Buijze
 */
public class StubAggregate extends AbstractEventSourcedAggregateRoot implements SnapshotProducer {

    private int invocationCount;

    public StubAggregate() {
    }

    public StubAggregate(UUID identifier) {
        super(identifier);
    }

    public void doSomething() {
        apply(new StubDomainEvent());
    }

    @Override
    protected void handle(DomainEvent event) {
        invocationCount++;
    }

    public int getInvocationCount() {
        return invocationCount;
    }

    @Override
    public DomainEvent createSnapshotEvent() {
        return new StubDomainEvent(getIdentifier(), 5);
    }
}