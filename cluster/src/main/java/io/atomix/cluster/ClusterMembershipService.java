/*
 * Copyright 2014-present Open Networking Foundation
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

import java.util.Set;

import io.atomix.utils.event.Listenable;
import io.atomix.utils.net.Address;

/**
 * Service for obtaining information about the individual members within
 * the cluster.
 */
public interface ClusterMembershipService extends Listenable<MemberEvent> {

  /**
   * Returns the local member.
   *
   * @return local member
   */
  Member getLocalMember();

  /**
   * Returns the local member ID.
   *
   * @return the local member ID
   */
  default MemberId getLocalMemberId() {
    return MemberId.from(getLocalMember());
  }

  /**
   * Returns the set of current cluster members.
   *
   * @return set of cluster members
   */
  Set<Member> getMembers();

  /**
   * Returns the specified member node.
   * <p>
   * This is a convenience method that wraps the given {@link String} in a {@link MemberId}. To avoid unnecessary
   * object allocation, repeated invocations of this method should instead use {@link #getMember(MemberId)}.
   *
   * @param memberId the member identifier
   * @return the member or {@code null} if no node with the given identifier exists
   */
  default Member getMember(String memberId) {
    return getMember(MemberId.from(memberId));
  }

  /**
   * Returns the specified member.
   *
   * @param memberId the member identifier
   * @return the member or {@code null} if no node with the given identifier exists
   */
  Member getMember(MemberId memberId);

  /**
   * Returns a member by address.
   *
   * @param address the member address
   * @return the member or {@code null} if no member with the given address could be found
   */
  default Member getMember(Address address) {
    return getMembers().stream()
        .filter(member -> member.getHost().equals(address.host()) && member.getPort() == address.port())
        .findFirst()
        .orElse(null);
  }

}
