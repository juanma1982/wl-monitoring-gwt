package wlstatistics.client.views.popupComponent;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;

public class HelpView extends Window {
	private static final String title = "Ayuda";
	private static HelpView me;
	
	public HelpView(String explination){
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
        projectLink.setContents(explination);
        projectLink.setWidth100();
        projectLink.setAlign(Alignment.CENTER);
        projectLink.setHeight(20);
        projectLink.setMargin(10);
        this.setTitle(title);
        this.addItem(projectLink);  
        this.centerInPage();	        
	}
}
