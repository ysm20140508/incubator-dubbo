/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.config;

import org.apache.dubbo.common.Constants;
import org.apache.dubbo.config.support.Parameter;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.Map;

/**
 * AbstractMethodConfig
 *
 * @export
 */
public abstract class AbstractMethodConfig extends AbstractConfig {

    private static final long serialVersionUID = 1L;

    /**
     * 远程调用超时时间
     */
    protected Integer timeout;

    /**
     * 重试次数
     */
    protected Integer retries;

    /**
     * 最大调用并发数
     */
    protected Integer actives;

    /**
     * 负载均衡
     * random 随机
     * roundRobin 轮询
     * leastActive 最少活跃调用
     */
    protected String loadbalance;

    /**
     * 异步
     */
    protected Boolean async;

    /**
     * ack 确认报文 异步发送
     */
    protected Boolean sent;

    /**
     * 模拟
     */
    protected String mock;

    /**
     * 合并
     */
    protected String merger;

    /**
     * 缓存
     */
    protected String cache;

    /**
     * 验证
     */
    protected String validation;

    /**
     * 自定义参数
     */
    protected Map<String, String> parameters;

    /**
     * 设置并行最大服务器数
     */
    protected Integer forks;

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        checkExtension(LoadBalance.class, "loadbalance", loadbalance);
        this.loadbalance = loadbalance;
    }

    public Boolean isAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }

    public Integer getActives() {
        return actives;
    }

    public void setActives(Integer actives) {
        this.actives = actives;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    @Parameter(escaped = true)
    public String getMock() {
        return mock;
    }

    public void setMock(String mock) {
        if (mock == null) {
            return;
        }

        if (mock.startsWith(Constants.RETURN_PREFIX) || mock.startsWith(Constants.THROW_PREFIX + " ")) {
            checkLength("mock", mock);
        } else if (mock.startsWith(Constants.FAIL_PREFIX) || mock.startsWith(Constants.FORCE_PREFIX)) {
            checkNameHasSymbol("mock", mock);
        } else {
            checkName("mock", mock);
        }
        this.mock = mock;
    }

    public void setMock(Boolean mock) {
        if (mock == null) {
            setMock((String) null);
        } else {
            setMock(String.valueOf(mock));
        }
    }

    public String getMerger() {
        return merger;
    }

    public void setMerger(String merger) {
        this.merger = merger;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        checkParameterName(parameters);
        this.parameters = parameters;
    }

}
