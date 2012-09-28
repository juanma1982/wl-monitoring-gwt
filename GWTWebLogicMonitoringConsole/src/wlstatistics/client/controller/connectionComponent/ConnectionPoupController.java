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
package wlstatistics.client.controller.connectionComponent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import wlstatistics.client.WeblogicMonitorServiceAsync;
import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.domain.DomainManager;
import wlstatistics.client.event.EventManager;
import wlstatistics.client.views.connectionComponent.ConnectionPopUp;
import wlstatistics.shared.model.WLDomain;

public class ConnectionPoupController {
	private ConnectionPopUp view;
	private WeblogicMonitorServiceAsync serverCall = UtilGWT.getInstance();

	public ConnectionPoupController(ConnectionPopUp popup) {
		view = popup;
	
		view.getComponent().getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final WLDomain domain = view.getComponent().getDomain();
				serverCall.ConnectToServer(domain.getAdminHost(), domain.getAdminPort(),
						domain.getUser(), domain.getPassword(),
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Error al comunicar con servidor");
							}

							@Override
							public void onSuccess(Void result) {
								
								DomainManager.getDomainManager().addDomain(
										domain);
								view.getComponent().clear();
								view.hide();
								EventManager.getEventManager()
										.addedDomainEvent(domain);
							}

						});
			}
		});
	}
}
