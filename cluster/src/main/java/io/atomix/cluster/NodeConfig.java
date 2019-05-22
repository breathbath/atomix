/*
 * Copyright 2018-present Open Networking Foundation
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
package io.atomix.cluster;

import io.atomix.utils.config.Config;
import io.atomix.utils.net.Address;

/**
 * Node configuration.
 */
public class NodeConfig implements Config {
  private NodeId id = NodeId.anonymous();
  private String host = "localhost";
  private int port = 5679;

  /**
   * Returns the node identifier.
   *
   * @return the node identifier
   */
  public NodeId getId() {
    return id;
  }

  /**
   * Sets the node identifier.
   *
   * @param id the node identifier
   * @return the node configuration
   */
  public NodeConfig setId(String id) {
    return setId(id != null ? NodeId.from(id, this.id.namespace()) : null);
  }

  /**
   * Sets the node identifier.
   *
   * @param id the node identifier
   * @return the node configuration
   */
  public NodeConfig setId(NodeId id) {
    this.id = id != null ? id : NodeId.anonymous();
    return this;
  }

  /**
   * Sets the node namespace.
   *
   * @param namespace the node namespace
   * @return the node configuration
   */
  public NodeConfig setNamespace(String namespace) {
    return setId(namespace != null ? NodeId.from(this.id.id(), namespace) : NodeId.from(this.id.id()));
  }

  /**
   * Returns the node hostname.
   *
   * @return the node hostname
   */
  public String getHost() {
    return host;
  }

  /**
   * Sets the node hostname.
   *
   * @param host the node hostname
   * @return the node configuration
   */
  public NodeConfig setHost(String host) {
    this.host = host;
    return this;
  }

  /**
   * Returns the node port.
   *
   * @return the node port
   */
  public int getPort() {
    return port;
  }

  /**
   * Sets the node port.
   *
   * @param port the node port
   * @return the node configuration
   */
  public NodeConfig setPort(int port) {
    this.port = port;
    return this;
  }

  /**
   * Returns the node address.
   *
   * @return the node address
   */
  public Address getAddress() {
    return Address.from(host, port);
  }

  /**
   * Sets the node address.
   *
   * @param address the node address
   * @return the node configuration
   */
  @Deprecated
  public NodeConfig setAddress(String address) {
    return setAddress(Address.from(address));
  }

  /**
   * Sets the node address.
   *
   * @param address the node address
   * @return the node configuration
   */
  @Deprecated
  public NodeConfig setAddress(Address address) {
    this.host = address.host();
    this.port = address.port();
    return this;
  }
}
