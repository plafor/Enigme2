package dpt.info.project.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dpt.info.project.client.model.UserInfo;

public interface ConnexionAsync {
	public void isIdentifie(String id,String mdp, AsyncCallback<UserInfo> callback);

	void testEnvoie(String message, AsyncCallback<Void> callback);

	void getNewMessage(int num, AsyncCallback<String> callback);
}
