package dpt.info.project.client.service;

import java.sql.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import dpt.info.project.client.model.EnigmeInfo;
import dpt.info.project.client.model.UserInfo;

@RemoteServiceRelativePath("EnigmeService")
public interface EnigmeService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static EnigmeServiceAsync instance;
		public static EnigmeServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(EnigmeService.class);
			}
			return instance;
		}
	}

	boolean creeEnigme(int id, String titre, String desc, String reponse,
			String lien, String date);

	EnigmeInfo[] recupEnigmes(String idU);

	String recupReponseEnigme(String idE, String idU);

	int recupNbBonnesReponsesParEnigme(String id);
	
	String[] recupUsersBonneReponsesParEnigme(String idE);

	UserInfo[] recupUsers();

	String[] recupEnigmesBonneReponsesParUser(String idU);
}
