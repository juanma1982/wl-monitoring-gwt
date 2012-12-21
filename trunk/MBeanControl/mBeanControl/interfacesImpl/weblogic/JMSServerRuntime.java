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

import mBeanControl.interfaces.IJMSDestination;
import mBeanControl.interfaces.IJMSServer;

public class JMSServerRuntime implements IJMSServer{
	private ObjectName JMSserverRuntime;
	private  MBeanServerConnection connection;
	
	public JMSServerRuntime(MBeanServerConnection connection, ObjectName jmsServRTObject) {
		this.connection = connection;
		this.JMSserverRuntime = jmsServRTObject;
	}
	
	public ArrayList<JMSDestinationRuntime> getJMSDdestinations(){
		ArrayList<JMSDestinationRuntime> jMSdestinations = new ArrayList<JMSDestinationRuntime>();
		try {
			ObjectName[] destRT = (ObjectName[])connection.getAttribute(JMSserverRuntime, "Destinations");
			for(ObjectName dest :destRT){
				JMSDestinationRuntime JMSDest = new JMSDestinationRuntime(connection,dest);
				jMSdestinations.add(JMSDest);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jMSdestinations;
	}
	
	public ArrayList<IJMSDestination> getIJMSDestinations() {
		
		ArrayList<IJMSDestination>  iJMSDestinations = new ArrayList<IJMSDestination>();
		iJMSDestinations.addAll(getJMSDdestinations());
		
		return iJMSDestinations;
	}

	public String getName() {
		try {
			return (String) connection.getAttribute(JMSserverRuntime, "Name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	public IJMSDestination getIJDestination(String name) {
		
		for(JMSDestinationRuntime dest : getJMSDdestinations()){
			if(dest.getName().equals(name)){
				return dest;
			}
		}
		
		return null;
	}


}