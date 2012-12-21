package mBeanControl.interfacesImpl.jboss;

import java.util.ArrayList;
import java.util.HashMap;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IServer;

public class DomainRuntime implements IDomain {
	private  final ObjectName domainRuntimeMBean;
	private  MBeanServerConnection connection;
	private IServer adminServer;
	private HashMap<String,IServer> runtimeServers = new HashMap<String, IServer>();

	public DomainRuntime(ObjectName domainRuntimeMBean,
			MBeanServerConnection connection) {
		super();
		this.domainRuntimeMBean = domainRuntimeMBean;
		this.connection = connection;
		adminServer = new ServerRuntime(domainRuntimeMBean,connection);
		runtimeServers.put("admin", adminServer);
	}

	@Override
	public String getName() throws ObjectNotFoundException {
		try {
			return (String) connection.getAttribute(domainRuntimeMBean, "domain");
		} catch (Exception e) {
			throw new ObjectNotFoundException(e);
		} 
	}

	@Override
	public String getAdminServerName() throws ObjectNotFoundException {
		return adminServer.getName();
	}

	@Override
	public ArrayList<IServer> getIServers() throws ObjectNotFoundException {
		ArrayList<IServer> iServers =  new ArrayList<IServer>();		
		for(String key: runtimeServers.keySet()){
			IServer server = runtimeServers.get(key);
			iServers.add(server);			
		}
		return iServers;
	}

	@Override
	public IServer getIServer(String name) throws ObjectNotFoundException {
		return runtimeServers.get(name);
	}

	@Override
	public IServer getAdminServer() throws ObjectNotFoundException {
		
		
		return adminServer;
	}

	@Override
	public ArrayList<IServer> getManagedServers()
			throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return new ArrayList<IServer>();		
	}

}
