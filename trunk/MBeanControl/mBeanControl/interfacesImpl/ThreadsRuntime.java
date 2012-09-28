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
package mBeanControl.interfacesImpl;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.interfaces.IThreads;

public class ThreadsRuntime implements IThreads{
	
	private MBeanServerConnection connection;
	private ObjectName threadPoolRuntime;
	
	public ThreadsRuntime(MBeanServerConnection connection, ObjectName threadPoolRuntime) {
		this.connection = connection;
		this.threadPoolRuntime = threadPoolRuntime;
	}

	public Integer getExecuteThreadIdleCount() {
		try {
			return (Integer) connection.getAttribute(threadPoolRuntime, "ExecuteThreadIdleCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer getPendingUserRequestCount() {
		try {
			return (Integer) connection.getAttribute(threadPoolRuntime, "PendingUserRequestCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
