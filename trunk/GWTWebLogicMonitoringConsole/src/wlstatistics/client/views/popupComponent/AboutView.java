package wlstatistics.client.views.popupComponent;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;

public class AboutView extends Window {
	private static final String href = "<a href='http://code.google.com/p/wl-monitoring-gwt/' target='_blank'>WebLogic Monitoring GWT Project</a>";	
	private static final String windowTitle = "Acerca de :";
	private static AboutView me;
	
	public AboutView(){		
		me = this;
        this.setWidth(360);  
        this.setHeight(115);  
        this.setShowMinimizeButton(false);  
        this.setIsModal(true);  
        this.setShowModalMask(true);  
        this.addCloseClickHandler(new CloseClickHandler() {
			
			@Override
			public void onCloseClick(CloseClientEvent event) {
				me.destroy();  
			}
		});               
        Label projectLink = new Label();
        projectLink.setContents(href);
        projectLink.setWidth100();
        projectLink.setAlign(Alignment.CENTER);
        projectLink.setHeight(20);
        projectLink.setMargin(10);
        this.setTitle(windowTitle);
        this.addItem(projectLink);  
        this.centerInPage();	        
	}
}
