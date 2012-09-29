package mBeanControl.interfacesImpl;

import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IJDBCRuntime;
import mBeanControl.interfaces.IJDBCRuntimeService;

public class JDBCRuntimeService implements IJDBCRuntimeService {
	private MBeanServerConnection connection;
	private ObjectName jDBCRuntimeServiceMBean;
	private ArrayList<JDBCRuntime> jdbcRuntimes;
	
	public JDBCRuntimeService(MBeanServerConnection connection,
			ObjectName jdbcRuntime) {
		this.connection = connection;
		this.jDBCRuntimeServiceMBean = jdbcRuntime;
	}

	//Private methods
		private ObjectName[] getJDBCRuntimes() throws Exception {
			return (ObjectName[]) connection.getAttribute(jDBCRuntimeServiceMBean,
			"JDBCDataSourceRuntimeMBeans");
		}
		
		@Override
		public ArrayList<IJDBCRuntime> getIJDBCRuntimes() throws ObjectNotFoundException{
			ArrayList<IJDBCRuntime> iServers =  new ArrayList<IJDBCRuntime>();
			iServers.addAll(getRuntimes());
			
			return iServers;
			
		}
		
		public ArrayList<JDBCRuntime> getRuntimes() throws ObjectNotFoundException {
			jdbcRuntimes = new ArrayList<JDBCRuntime>();
			
			try {
				for(ObjectName o :getJDBCRuntimes()){
					JDBCRuntime serverRuntime = new JDBCRuntime(connection, o);
					jdbcRuntimes.add(serverRuntime);
				}
			} catch (Exception e) {
				throw new ObjectNotFoundException(e);
			}
		
		return jdbcRuntimes;
		}
}
