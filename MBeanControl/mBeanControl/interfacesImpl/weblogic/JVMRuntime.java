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

import mBeanControl.interfaces.IJVMRuntime;

public class JVMRuntime implements IJVMRuntime{
	private MBeanServerConnection connection;
	private ObjectName jVMRuntimeMBean;
	
	public JVMRuntime(MBeanServerConnection connection, ObjectName runtimeMBean) {
		this.connection = connection;
		this.jVMRuntimeMBean = runtimeMBean;
	}
	
	public Long getHeapSizeCurrent(){
		try {
			return (Long) connection.getAttribute(jVMRuntimeMBean, "HeapSizeCurrent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Long(0);
	}
	
}
