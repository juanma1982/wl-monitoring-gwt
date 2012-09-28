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
package wlstatistics.client.views.topologyComponent;

import wlstatistics.client.resources.ResourcesManager;

import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Canvas;

public class ServerComponent extends Canvas {
	private Img statusImg;
	private Img serverImg;
	private Label serverName;
	private Label serverUrl;
	public ServerComponent() {
		
		this.setSize("122px", "104px");
		
		serverImg = new Img(ResourcesManager.getServerImgUrl());
		this.addChild(serverImg);
		serverImg.moveTo(10, 10);
		serverImg.setSize("46px", "41px");
		
		statusImg = new Img(ResourcesManager.getRedStatusImgUrl());
		this.addChild(statusImg);
		statusImg.moveTo(71, 10);
		statusImg.setSize("28px", "28px");
		
		serverName = new Label("srvName");
		this.addChild(serverName);
		serverName.moveTo(10, 57);
		
		serverUrl = new Label("srvUrl");
		serverUrl.setStyleName("server-Label");
		this.addChild(serverUrl);
		serverUrl.moveTo(10, 75);
	}
	
	public void setStatusRed() {
		statusImg.setSrc(ResourcesManager.getRedStatusImgUrl());
	}

	public void setStatusGreen() {
		statusImg.setSrc(ResourcesManager.getGreenStatusImgUrl());
	}
	public void setServerName(String name){
		serverName.setContents(name);
	}
	public void setServerUrl(String name){
		serverUrl.setContents(name);
	}
}
