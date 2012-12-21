package wlstatistics.client.controller.connectionComponent;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

import wlstatistics.client.WeblogicMonitorService.UtilGWT;
import wlstatistics.client.WeblogicMonitorServiceAsync;
import wlstatistics.client.domain.DomainManager;
import wlstatistics.client.event.EventManager;
import wlstatistics.client.resources.ResourcesManager;
import wlstatistics.client.views.connectionComponent.NewConnectionScreenView;
import wlstatistics.shared.model.Domain;

public class NewConnectionScreenController {
	private NewConnectionScreenView view;
	private WeblogicMonitorServiceAsync serverCall = UtilGWT.getInstance();

	public NewConnectionScreenController(NewConnectionScreenView viewScreen) {
		view = viewScreen;
		view.getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final Domain domain = new Domain(view.getHost(),view.getPort(),view.getUser(),view.getPassword(),view.getType());
				
				serverCall.ConnectToServer(domain.getAdminHost(),
						domain.getAdminPort(), domain.getUser(),
						domain.getPassword(), domain.getType(),new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(ResourcesManager.getConnectionErrorMsg());
							}

							@Override
							public void onSuccess(Void result) {

								DomainManager.getDomainManager().addDomain(
										domain);
								view.clear();
								view.hide();
								EventManager.getEventManager()
										.addedDomainEvent(domain);
							}

						});

			}
		});
	}
}
