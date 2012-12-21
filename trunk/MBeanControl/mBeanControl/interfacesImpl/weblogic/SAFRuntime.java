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

import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import weblogic.health.HealthState;

import mBeanControl.interfaces.ISAF;
import mBeanControl.interfaces.ISAFAgent;

/**
 *
 * @author jbrasca
 * date: Aug 15, 2011
 */
public class SAFRuntime implements ISAF {
	private ObjectName sAFRuntime;
	private MBeanServerConnection connection;
	
	/**
	 * @param connection
	 * @param sAFRuntime
	 */
	public SAFRuntime(MBeanServerConnection connection, ObjectName sAFRuntime) {
		this.connection = connection;
		this.sAFRuntime = sAFRuntime;
	}
	
	public String getName() {

		String name = null;
		try {
			name = (String) connection.getAttribute(sAFRuntime, "Name");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return name;
	}
	public HealthState getHealthState() {
		try {
			return (HealthState) connection.getAttribute(sAFRuntime, "HealthState");
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}

	public ArrayList<ISAFAgent> getAgents() {
		ArrayList<ISAFAgent> agents =  new ArrayList<ISAFAgent>();
		try {
			ObjectName[] agentsMbeans = (ObjectName[]) connection.getAttribute(sAFRuntime, "Agents");
			for(ObjectName agentMbean:agentsMbeans){
				agents.add(new SAFAgent(connection,agentMbean));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return agents;
	}



}
