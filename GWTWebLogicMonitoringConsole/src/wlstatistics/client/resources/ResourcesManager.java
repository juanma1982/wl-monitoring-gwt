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
package wlstatistics.client.resources;

import java.util.Date;

import com.google.gwt.core.client.GWT;

public class ResourcesManager {
	private static ResourcesManager instance;
	private static final String redStatusImgUrl = "images/red.GIF";
	private static final String greenStatusImgUrl = "images/green.GIF";
	private static final String serverImgUrl = "images/computer-server.png";
	private static final String refreshImgUrl = "images/Button-Refresh-icon.png";
	private static final String refreshOverImgUrl = "images/Button-Refresh-icon-over.png";
	private static final String serverImage = "images/computer-server.png";
	private static final String adminServerImage = "images/server.jpg";
	private static final String dbImage = "images/db2.jpg";
	private static final String datasoruceExp ="Muestra el estado de datasource (conexion a base de datos).";
	private static final String adminServerExp ="Muestra el estado del servidor administrador del dominio de weblogic.";
	private static final String serverExp ="Muestra el estado del servidor manejado del dominio de weblogic.";
	private static final String notImplementedMsg = "Funcionalidad No Implementada";
	private static final String newText ="Nueva";
	private static final String deleteText = "Eliminar";
	private static final String connectionsText = "Conexiones";
	private static final String newConnectionText = "Nueva Conexión";
	private static final String typeText = "Tipo de Servidor";
	private static final String hostText = "Host";
	private static final String portText = "Port";
	private static final String userText = "User";
	private static final String passwordText = "Password";
	private static final String createText = "Crear";
	private static final String connectionErrorMsg = "Error al comunicar con servidor";
	private static final String savedDomainMsg = "Dominio Guardado con exito";
	private static final String errorSaveDomainMsg = "";
	private static final String weblogic = "Weblogic";
	private static final String jboss = "JBoss";
	
	private ResourcesManager() {
	}

	public static ResourcesManager getResourcesManager() {
		if (instance == null)
			instance = new ResourcesManager();
		return instance;
	}
	
	public static String getRefreshImgUrl() {
		return GWT.getHostPageBaseURL()+refreshImgUrl;
	}

	public static String getRefreshOverImgUrl() {
		return GWT.getHostPageBaseURL()+refreshOverImgUrl;
	}

	public static String getRedStatusImgUrl() {
		return GWT.getHostPageBaseURL()+redStatusImgUrl;
	}

	public static String getGreenStatusImgUrl() {
		return GWT.getHostPageBaseURL()+greenStatusImgUrl;
	}

	public static String getServerImgUrl() {
		return GWT.getHostPageBaseURL()+serverImgUrl;
	}

	public static String getRedstatusimgurl() {
		return GWT.getHostPageBaseURL()+redStatusImgUrl;
	}

	public static String getGreenstatusimgurl() {
		return GWT.getHostPageBaseURL()+greenStatusImgUrl;
	}

	public static String getServerimgurl() {
		return GWT.getHostPageBaseURL()+serverImgUrl;
	}

	public static String getRefreshimgurl() {
		return GWT.getHostPageBaseURL()+refreshImgUrl;
	}

	public static String getRefreshoverimgurl() {
		return GWT.getHostPageBaseURL()+refreshOverImgUrl;
	}

	public static String getServerimage() {
		return GWT.getHostPageBaseURL()+serverImage;
	}

	public static String getAdminserverimage() {
		return GWT.getHostPageBaseURL()+adminServerImage;
	}

	public static String getDbimage() {
		return GWT.getHostPageBaseURL()+dbImage;
	}

	public static String getDatasoruceexp() {
		return datasoruceExp;
	}

	public static String getAdminserverexp() {
		return adminServerExp;
	}

	public static String getServerexp() {
		return serverExp;
	}

	public static String getNotimplementedmsg() {
		return notImplementedMsg;
	}

	public static String getNewtext() {
		return newText;
	}

	public static String getDeletetext() {
		return deleteText;
	}

	public static String getConnectionstext() {
		return connectionsText;
	}

	public static String getNewconnectiontext() {
		return newConnectionText;
	}

	public static String getHosttext() {
		return hostText;
	}

	public static String getPorttext() {
		return portText;
	}

	public static String getUsertext() {
		return userText;
	}

	public static String getPasswordtext() {
		return passwordText;
	}

	public static String getCreatetext() {
		return createText;
	}

	public static String getConnectionErrorMsg() {		
		return connectionErrorMsg;
	}

	public static String getSavedDomainMsg() {
		return savedDomainMsg;
	}

	public static String getErrorSaveDomainMsg() {
		return errorSaveDomainMsg;
	}

	public static String getTypetext() {
		return typeText;
	}

	public static String getWebLogictext() {
		return weblogic;
	}

	public static String getJBosstext() {
		return jboss;
	}
	
	

}
