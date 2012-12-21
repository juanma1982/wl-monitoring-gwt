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
package wlstatistics.server;

import wlstatistics.shared.model.Domain;
import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IServer;

public class WLDomainTrans {


	public static Domain transform(IDomain domain) {

		Domain wLDomain = new Domain();
		try {
			wLDomain.setName(domain.getName());
			wLDomain.setAdminServerName(domain.getAdminServerName());
			wLDomain.setAdminHost(domain.getAdminServer().getListenAddress());
			wLDomain.setAdminPort(domain.getAdminServer().getListenPort().toString());
			wLDomain.setAdminHealth(domain.getAdminServer().getHealthState());
			wLDomain.setAdminServerStatus(domain.getAdminServer().getState());
			for(IServer server: domain.getManagedServers()){
				wLDomain.addManagedServers(WLServerTransfor.transform(server));				
			}
			
			
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return wLDomain;
	}
	
	

}
