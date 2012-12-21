package wlstatistics.client.views;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Portlet;
import com.smartgwt.client.widgets.tree.TreeGrid;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class TopologyListView extends Portlet {
	private TreeGrid treeGridLeft;
	private TreeGrid treeGridRight;
	private HLayout layout;
	private static String sixty = "60%"; 
	private static String manejado = "Manejado";
	private static String estado = "Estado";
	private static String subEstado = "Estado Servidor";
	private static String indicador ="Indicador";
	private static String prefix = "images/";
	private static String sufix =".GIF";
	public static String name = "name";
	public static String health = "health";
	public static String serverStatus = "serverStatus";
	public static String serverHealth = "serverHealth";
	private String TitlePrefix;
	
	public TopologyListView() {
		layout = new HLayout();
		treeGridLeft = new TreeGrid();
		treeGridRight = new TreeGrid();
		treeGridLeft.setWidth(sixty);
		treeGridRight.setWidth(sixty);
		TreeGridField nameField = new TreeGridField(name,manejado, 250);
		nameField.setFrozen(true);
		TreeGridField statusField = new TreeGridField(health,estado, 70);
		TreeGridField healthField = new TreeGridField(serverHealth,subEstado, 80);
		TreeGridField indicatorField = new TreeGridField(serverStatus,indicador, 70);
		indicatorField.setImageURLPrefix(GWT.getHostPageBaseURL()+prefix);
		indicatorField.setImageURLSuffix(sufix);
		indicatorField.setType(ListGridFieldType.IMAGE);
		indicatorField.setAlign(Alignment.CENTER);
		treeGridLeft.setFields(nameField,indicatorField,statusField,healthField);
		treeGridRight.setFields(nameField,indicatorField,statusField,healthField);
		layout.addMember(treeGridLeft);
		layout.addMember(treeGridRight);
		addItem(layout);
		this.setHeight(sixty);
		this.setWidth(sixty);
		this.setShowMaximizeButton(false);
	}

	public TreeGrid getTreeGridLeft() {
		return treeGridLeft;
	}

	public void setTreeGridLeft(TreeGrid treeGrid) {
		this.treeGridLeft = treeGrid;
	}

	public TreeGrid getTreeGridRight() {
		return treeGridRight;
	}

	public void setTreeGridRight(TreeGrid treeGridRight) {
		this.treeGridRight = treeGridRight;
	}

	public String getTitlePrefix() {
		return TitlePrefix;
	}

	public void setTitlePrefix(String titlePrefix) {
		TitlePrefix = titlePrefix;
	}
	
}
