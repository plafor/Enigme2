package dpt.info.project.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ReponseService")
public interface ReponseService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ReponseServiceAsync instance;
		public static ReponseServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(ReponseService.class);
			}
			return instance;
		}
	}
	
	boolean majReponseEnigme(String idE, String idU, String reponse);
}
