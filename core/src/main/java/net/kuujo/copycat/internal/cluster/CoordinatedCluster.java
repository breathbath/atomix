/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.internal.cluster;

import net.kuujo.copycat.cluster.coordinator.ClusterCoordinator;
import net.kuujo.copycat.cluster.coordinator.MemberCoordinator;
import net.kuujo.copycat.internal.CopycatStateContext;
import net.kuujo.copycat.util.serializer.Serializer;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Coordinated cluster.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public class CoordinatedCluster extends AbstractCluster {

  public CoordinatedCluster(int id, ClusterCoordinator coordinator, CopycatStateContext context, Router router, Serializer serializer, ScheduledExecutorService executor) {
    super(id, coordinator, context, router, serializer, executor);
  }

  @Override
  protected CoordinatedMember createMember(MemberInfo info) {
    MemberCoordinator coordinator = this.coordinator.member(info.uri());
    return coordinator != null ? new CoordinatedMember(id, info, coordinator, serializer, executor) : null;
  }

}
