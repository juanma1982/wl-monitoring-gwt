package mBeanControl.interfacesImpl.jboss;

import java.util.ArrayList;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.interfaces.IJDBCRuntimeService;
import mBeanControl.interfaces.IJMSServer;
import mBeanControl.interfaces.IJVMRuntime;
import mBeanControl.interfaces.ISAF;
import mBeanControl.interfaces.IServer;
import mBeanControl.interfaces.IThreads;
import mBeanControl.log.DefaultLogger;

public class ServerRuntime implements IServer {
	private static final String listenPort = "port";
	private static final String listenAddress = "address";
	private static final String serverInfo = "serverInfo";
	private  final ObjectName serverRuntimeMBean;
	private  MBeanServerConnection connection;
	private Integer port;
	private String host;
	private String name;
	
	public ServerRuntime(ObjectName domainRuntimeMBean,
			MBeanServerConnection connection) {
		serverRuntimeMBean = domainRuntimeMBean;
		this.connection = connection;
	}

	@Override
	public String getName() {
		try {
			name = (String) connection.getAttribute(serverRuntimeMBean, serverInfo);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(serverInfo,this.getClass(),e.getMessage());
		}
		return name;
	}

	@Override
	public String getState() {
		try {			
			String result = connection.getAttribute(new ObjectName("jboss.system:type=Server"), "Started").toString();
			return result;
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(serverInfo,this.getClass(),e.getMessage());
		}
		return null;
	}

	@Override
	public Integer getOpenSocketsCurrentCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IJMSServer> getIJMSServers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJVMRuntime getIJVMRuntime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJDBCRuntimeService getIJDBCRuntimeService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IThreads getIThreads() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IJMSServer getIJMServer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAdminServer() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getListenAddress() {
		try {
			host = (String) connection.getAttribute(serverRuntimeMBean, listenAddress);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(listenAddress,this.getClass(),e.getMessage());
		}
		return host;
	}

	@Override
	public Integer getListenPort() {
		try {
			port = (Integer) connection.getAttribute(serverRuntimeMBean, listenPort);
		} catch (Exception e) {
			DefaultLogger.NotFoundObjectAttributeLog(listenPort,this.getClass(),e.getMessage());
		}
		return port;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHealthState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISAF getSAF() {
		// TODO Auto-generated method stub
		return null;
	}

}
