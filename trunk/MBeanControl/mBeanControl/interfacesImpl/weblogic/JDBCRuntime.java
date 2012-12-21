package mBeanControl.interfacesImpl.weblogic;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;


import mBeanControl.interfaces.IJDBCRuntime;

public class JDBCRuntime implements IJDBCRuntime {
	private MBeanServerConnection connection;
	private ObjectName jDBCRuntimeMBean;
	private String name;
	private String state;
	private Integer connCount;
	
	public JDBCRuntime(MBeanServerConnection connection, ObjectName runtimeMBean) {
		this.connection = connection;
		this.jDBCRuntimeMBean = runtimeMBean;
		name="";
		state="";
		connCount= 0;
	}
	
	@Override
	public String getName() {
		try {
			name = (String) connection.getAttribute(jDBCRuntimeMBean, "Name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public String getState() {
		try {
			state = (String) connection.getAttribute(jDBCRuntimeMBean, "State");
		} catch (Exception e) {
			state = "Unknown";
			e.printStackTrace();
		}
		return state;
	}

	@Override
	public Integer getActiveConnectionsCurrentCount() {
		try {
			connCount = (Integer) connection.getAttribute(jDBCRuntimeMBean, "ActiveConnectionsCurrentCount");
		} catch (Exception e) {
			connCount = 0;
			e.printStackTrace();
		}
		return connCount;
	}

}

