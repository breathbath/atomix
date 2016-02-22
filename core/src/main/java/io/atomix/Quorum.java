/*
 * Copyright 2016 the original author or authors.
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
 * limitations under the License
 */
package io.atomix;

import io.atomix.catalyst.transport.Address;

/**
 * Magic quorum configuration constants.
 * <p>
 * Quorum constants can be used to configure "magic" quorum hints for an {@link AtomixReplica}.
 * Quorum hints define the minimum number of replicas that must participate in the Raft consensus
 * algorithm.
 *
 * @author <a href="http://github.com/kuujo>Jordan Halterman</a>
 */
public enum Quorum {

  /**
   * Indicates that the seed members should represent the quorum size.
   * <p>
   * This is a magic quorum hint that is based on the number of replica
   * {@link io.atomix.catalyst.transport.Address addresses} provided to the
   * {@link AtomixReplica#builder(Address, Address...) replica builder factory} when constructing a new
   * replica. If the number of {@code members} provided to the replica builder is {@code 3} then the
   * configured quorum hint will be {@code 3}. This is the default quorum hint.
   */
  SEED(0),

  /**
   * Indicates that all members of the cluster should participate in the quorum.
   * <p>
   * This is a special quorum hint that forces all {@link AtomixReplica}s in the cluster to be full
   * voting members of the Raft consensus algorithm. This is only recommended for small clusters.
   */
  ALL(-1);

  private final int size;

  Quorum(int size) {
    this.size = size;
  }

  /**
   * Returns the quorum size hint.
   *
   * @return The quorum size hint.
   */
  public int size() {
    return size;
  }

}
