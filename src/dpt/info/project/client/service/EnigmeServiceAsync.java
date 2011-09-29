package dpt.info.project.client.service;

import java.sql.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dpt.info.project.client.model.EnigmeInfo;
import dpt.info.project.client.model.UserInfo;


public interface EnigmeServiceAsync {

	public void creeEnigme(int id,String titre, String desc, String reponse, String lien, String date,AsyncCallback<Boolean> callback);

	public void recupEnigmes(String idU, AsyncCallback<EnigmeInfo[]> callback);

	public void recupReponseEnigme(String idE, String idU, AsyncCallback<String> asyncCallback);

	public void recupNbBonnesReponsesParEnigme(String id,AsyncCallback<Integer> asyncCallback);

	void recupUsersBonneReponsesParEnigme(String idE, AsyncCallback<String[]> callback);

	public void recupUsers(AsyncCallback<UserInfo[]> asyncCallback);

	public void recupEnigmesBonneReponsesParUser(String idU,AsyncCallback<String[]> asyncCallback);

}
