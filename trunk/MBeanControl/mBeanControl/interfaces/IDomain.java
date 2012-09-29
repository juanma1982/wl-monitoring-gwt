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
package mBeanControl.interfaces;

import java.util.ArrayList;

import mBeanControl.exceptions.ObjectNotFoundException;
/**
 * Domain Interface
 * This is the root interface for access you need to generate a connection with
 * the connectionFactory class
 * 
 * example
 * IDomain iDomain = ConnectionFactory.Connect(hostname, portString, username, password);
 * 
 * Weblogic MBean reference
 * http://download.oracle.com/docs/cd/E12840_01/wls/docs103/wlsmbeanref/core/index.html
 * 
 * @author jbrasca
 */
public interface IDomain {
	
	public String getName() throws ObjectNotFoundException;
	
	public String getAdminServerName() throws ObjectNotFoundException;
	
	public ArrayList<IServer> getIServers() throws ObjectNotFoundException;
	
	public IServer getIServer(String name) throws ObjectNotFoundException;
	
	public IServer getAdminServer() throws ObjectNotFoundException;
	
	public ArrayList<IServer> getManagedServers() throws ObjectNotFoundException;
}
