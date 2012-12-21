package wlstatistics.client.views.connectionComponent;

import wlstatistics.client.resources.ResourcesManager;

import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.form.fields.CanvasItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;

public class NewConnectionScreenView extends Window {
	private TextItem hostItem;
	private TextItem portItem;
	private PasswordItem passwordItem;
	private IButton addButton;
	private TextItem userItem;
	private ComboBoxItem typeItem;
	private VLayout layout_1;

	 public void draw()
	 {
		 hostItem.clearValue();
		 portItem.clearValue();
		 passwordItem.clearValue();
		 userItem.clearValue();
		 super.draw();
	 }
	 
	public NewConnectionScreenView() {

		layout_1 = new VLayout();
		final DynamicForm form = new DynamicForm();
		form.setWidth(250);
		layout_1.setTop(50);
		hostItem = new TextItem();
		hostItem.setTitle(ResourcesManager.getHosttext());
		hostItem.setRequired(true);

		portItem = new TextItem();
		portItem.setTitle(ResourcesManager.getPorttext());
		portItem.setRequired(true);

		userItem = new TextItem();
		userItem.setTitle(ResourcesManager.getUsertext());
		userItem.setRequired(true);

		passwordItem = new PasswordItem();
		passwordItem.setTitle(ResourcesManager.getPasswordtext());
		passwordItem.setRequired(true);
		
		typeItem = new ComboBoxItem();
		typeItem.setTitle(ResourcesManager.getTypetext());
		typeItem.setValueMap(ResourcesManager.getWebLogictext(),ResourcesManager.getJBosstext());
		
		CanvasItem canvasItem_1 = new CanvasItem("newCanvasItem", "");

		addButton = new IButton(ResourcesManager.getCreatetext());
		canvasItem_1.setCanvas(addButton);
		form.setFields(new FormItem[] { hostItem, portItem, userItem,
				passwordItem,typeItem, canvasItem_1 });
		layout_1.addMember(form);
		form.moveTo(0, 44);
		addItem(layout_1);
		layout_1.moveTo(6, 66);
		this.setHeight(350);
		this.setWidth(300);
		this.centerInPage();
		this.setTitle(ResourcesManager.getNewconnectiontext());
	}

	public IButton getAddButton() {
		return addButton;
	}

	public String getHost() {
		return hostItem.getValueAsString();
	}

	public String getPort() {
		return portItem.getValueAsString();
	}

	public String getUser() {
		return userItem.getValueAsString();
	}

	public String getPassword() {
		return passwordItem.getValueAsString();
	}

	public int getType() {
		if(typeItem.getValue().equals("Weblogic"))
			return 0;
		else
			return 1;
	}

}
