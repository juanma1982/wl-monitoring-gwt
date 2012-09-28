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

import wlstatistics.shared.model.WLDomain;
import mBeanControl.exceptions.ObjectNotFoundException;
import mBeanControl.interfaces.IDomain;
import mBeanControl.interfaces.IServer;

public class WLDomainTrans {


	public static WLDomain transform(IDomain domain) {

		WLDomain wLDomain = new WLDomain();
		try {
			wLDomain.setName(domain.getName());
			wLDomain.setAdminHost(domain.getAdminServer().getListenAddress());
			wLDomain.setAdminPort(domain.getAdminServer().getListenPort().toString());
			wLDomain.setAdminServerStatus(domain.getAdminServer().getHealthState().toString());
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
