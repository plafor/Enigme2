package dpt.info.project.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dpt.info.project.client.model.UserInfo;

public interface ConnexionAsync {
	public void isIdentifie(String id,String mdp, AsyncCallback<UserInfo> callback);
}
