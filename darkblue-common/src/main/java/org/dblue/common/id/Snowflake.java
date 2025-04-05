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
package org.dblue.common.id;

/**
 * 雪花ID生成算法
 *
 * @author Wang Chengwei
 * @since 1.0.0
 */
public class Snowflake {
    /**
     * Start time : 2014-9-1
     */
    private static final long START_TIME = 1409529600000L;

    private static final long BIT_LEN_TIME = 39L;

    private static final long BIT_LEN_SEQUENCE = 16L;

    private static final long BIT_LEN_MACHINE_ID = 63 - BIT_LEN_TIME - BIT_LEN_SEQUENCE;
    private static final int MAX_MACHINE_ID = ~(-1 << BIT_LEN_MACHINE_ID);
    private static final long MAX_SEQUENCE = ~(-1 << BIT_LEN_SEQUENCE);
    private final int machineId;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    public Snowflake(int machineId) {
        if (machineId > MAX_MACHINE_ID || machineId < 0) {
            throw new IllegalArgumentException(String.format("Machine id must greater than 0 and less than %d", MAX_MACHINE_ID));
        }
        this.machineId = machineId;
    }

    public static long id() {
        return DemandHolder.SNOWFLAKE.nextId();
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new SnowflakeException("time back??");
        }
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1L) & MAX_SEQUENCE;
            // overflow
            if (this.sequence == 0L) {
                // block to next millis
                timestamp = tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }
        this.lastTimestamp = timestamp;
        return ((this.lastTimestamp - START_TIME) << (BIT_LEN_SEQUENCE + BIT_LEN_MACHINE_ID)) | (this.sequence << BIT_LEN_MACHINE_ID) | this.machineId;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    public static long longSnowflakeId() {
        return DemandHolder.SNOWFLAKE.nextSnowflakeId();
    }

    public long nextSnowflakeId() {
        return nextId();
    }

    public static String stringId() {
        return DemandHolder.SNOWFLAKE.nextStringId();
    }

    public String nextStringId() {
        long id = nextId();
        return Long.toString(id);
    }

    public static String hexId() {
        return DemandHolder.SNOWFLAKE.nextHexId();
    }

    public String nextHexId() {
        long id = nextId();
        return Long.toHexString(id);
    }
    private static class DemandHolder {
        private static final Snowflake SNOWFLAKE = new Snowflake(randomWorkerId());
        private static int randomWorkerId() {
            long workerId = RandomUtils.nextLong();
            return (int) (workerId % MAX_MACHINE_ID);

        }
    }
}