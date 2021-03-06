/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.client;

import com.hazelcast.partition.InternalPartitionService;

import static com.hazelcast.partition.strategy.StringPartitioningStrategy.getPartitionKey;

public abstract class KeyBasedClientRequest extends PartitionClientRequest {

    protected abstract Object getKey();

    protected final int getPartition() {
        Object key = getKey();
        InternalPartitionService partitionService = clientEngine.getPartitionService();
        if (key instanceof String) {
            return partitionService.getPartitionId(getPartitionKey((String) key));
        }
        return partitionService.getPartitionId(key);
    }

}
