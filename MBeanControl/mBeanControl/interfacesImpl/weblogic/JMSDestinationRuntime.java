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

import mBeanControl.interfaces.IJMSDestination;

/**
 * 
 * @author jbrasca
 * 
 */
public class JMSDestinationRuntime implements IJMSDestination {
	private ObjectName jMSDestination;
	private  MBeanServerConnection connection;
	
	/**
	 * 
	 * @param connection
	 * @param dest
	 */
	public JMSDestinationRuntime(MBeanServerConnection connection, ObjectName dest) {
		this.jMSDestination = dest;
		this.connection = connection;
	}

	public String getMessages() {
//		try {
//			String curs = (String)connection.invoke(jMSDestination,"getMessages",new Object[]{null,new Integer(0)}, new String[]{"java.lang.String","java.lang.Integer"});
//			Long curs1 = (Long)connection.invoke(jMSDestination,"getCursorSize",new Object[]{curs}, new String[]{"java.lang.String"});
//			Long curs2 = (Long)connection.invoke(jMSDestination,"getCursorStartPosition",new Object[]{curs}, new String[]{"java.lang.String"});
//			CompositeData[] curs3 = (CompositeData[])connection.invoke(jMSDestination,"getNext",new Object[]{curs,new Integer(0)}, new String[]{"java.lang.String","java.lang.Integer"});
//			CompositeData[] curs4 = (CompositeData[])connection.invoke(jMSDestination,"getNext",new Object[]{curs,new Integer(1)}, new String[]{"java.lang.String","java.lang.Integer"});
//			Collection col = curs4[0].values();
//			
//	
//			
//			return curs;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}

	public Long getMessagesCurrentCount() {
		try {
			return (Long) connection.getAttribute(jMSDestination, "MessagesCurrentCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getName() {
		try {
			return (String) connection.getAttribute(jMSDestination, "Name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getState() {
		try {
			return (String) connection.getAttribute(jMSDestination, "State");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getMessagesPendingCount() {
		try {
			return (Long) connection.getAttribute(jMSDestination, "MessagesPendingCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getMessagesHighCount() {
		try {
			return (Long) connection.getAttribute(jMSDestination, "MessagesHighCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Long getConsumersTotalCount() {
		try {
			return (Long) connection.getAttribute(jMSDestination, "ConsumersTotalCount");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
