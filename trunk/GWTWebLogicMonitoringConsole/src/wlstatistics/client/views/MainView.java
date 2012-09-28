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
package wlstatistics.client.views;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.menu.MenuBar;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.smartgwt.client.widgets.WidgetCanvas;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.smartgwt.client.widgets.Img;

public class MainView extends Canvas {
	public MainView() {
		
		WidgetCanvas widgetCanvas = new WidgetCanvas(new Label("New label"));
		addChild(widgetCanvas);
		widgetCanvas.moveTo(29, 30);
		
		WidgetCanvas widgetCanvas_1 = new WidgetCanvas(new Label("New label"));
		addChild(widgetCanvas_1);
		widgetCanvas_1.moveTo(137, 30);
		
		WidgetCanvas widgetCanvas_2 = new WidgetCanvas(new Image((String) null));
		addChild(widgetCanvas_2);
		widgetCanvas_2.moveTo(98, 152);
		
		com.smartgwt.client.widgets.Label lblNewLabel = new com.smartgwt.client.widgets.Label("New Label");
		addChild(lblNewLabel);
		lblNewLabel.moveTo(243, 145);
		
		Img img = new Img("desktop.png");
		addChild(img);
		img.moveTo(243, 39);
	}
}
