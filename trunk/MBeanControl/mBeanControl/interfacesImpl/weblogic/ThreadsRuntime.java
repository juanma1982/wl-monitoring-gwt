/*
 * Copyright 2011 Juan Cruz Basso
 * juancruzbasso@gmail.com
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package mBeanControl.interfacesImpl.weblogic;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import weblogic.health.HealthState;

import mBeanControl.interfaces.IThreads;
import mBeanControl.log.DefaultLogger;

public class ThreadsRuntime implements IThreads{
	
	private static String executeThreadIdleCount="ExecuteThreadIdleCount";
	private static String pendingUserRequestCount="PendingUserRequestCount";
	private static final String healthState = "HealthState";
	private static final String hoggingThreadCount = "HoggingThreadCount";
	private static final String throughput = "Throughput";
	private static final String executeThreadTotalCount = "ExecuteThreadTotalCount";
	private MBeanServerConnection connection;
	private ObjectName threadPoolRuntime;
	
	
	public ThreadsRuntime(MBeanServerConnection connection, ObjectName threadPoolRuntime) {
		this.connection = connection;
		this.threadPoolRuntime = threadPoolRuntime;
	}

	public Integer getExecuteThreadIdleCount() {
		try {
			return (Integer) connection.getAttribute(threadPoolRuntime, executeThreadIdleCount);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(executeThreadIdleCount,this.getClass(),e.getMessage());			 
		}
		return 0;
	}

	public Integer getPendingUserRequestCount() {
		try {
			return (Integer) connection.getAttribute(threadPoolRuntime, pendingUserRequestCount);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(pendingUserRequestCount,this.getClass(),e.getMessage());	
		}
		return 0;
	}

	@Override
	public HealthState getState() {
		try {
			return (HealthState) connection.getAttribute(threadPoolRuntime, healthState);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(healthState,this.getClass(),e.getMessage());			 
		}
		return null;
	}

	@Override
	public Integer getHoggingThreadCount() {
		try {
			return (Integer) connection.getAttribute(threadPoolRuntime,
					hoggingThreadCount);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(hoggingThreadCount,
					this.getClass(), e.getMessage());
		}
		return 0;
	}

	@Override
	public Integer getExecuteThreadTotalCount() {
		try {
			return (Integer) connection.getAttribute(threadPoolRuntime,
					executeThreadTotalCount);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(executeThreadTotalCount,
					this.getClass(), e.getMessage());
		}
		return 0;
	}

	@Override
	public Double getThroughput() {
		try {
			return (Double) connection.getAttribute(threadPoolRuntime,
					throughput);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(throughput,
					this.getClass(), e.getMessage());
		}
		return 0.0;
	}

}
