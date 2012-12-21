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

import mBeanControl.interfaces.ISAFAgent;

/**
 *
 * @author jbrasca
 * date: Aug 16, 2011
 */
public class SAFAgent implements ISAFAgent {

	private MBeanServerConnection connection;
	private ObjectName agentRuntime;

	/**
	 * @param connection
	 * @param agentMbean
	 */
	public SAFAgent(MBeanServerConnection connection, ObjectName agentRuntime) {
		this.connection = connection;
		this.agentRuntime = agentRuntime;	
	}
	
	public String getName() {

		String name = null;
		try {
			name = (String) connection.getAttribute(agentRuntime, "Name");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}
	public HealthState getHealthState() {
		try {
			return (HealthState) connection.getAttribute(agentRuntime, "HealthState");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}


	public Long getMessagesReceivedCount() {
		try {
			return (Long) connection.getAttribute(agentRuntime, "MessagesReceivedCount");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}


	public Long getMessagesCurrentCount() {
		try {
			return (Long) connection.getAttribute(agentRuntime, "MessagesCurrentCount");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}


	public Long getFailedMessagesTotal() {
		try {
			return (Long) connection.getAttribute(agentRuntime, "FailedMessagesTotal");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}


}
