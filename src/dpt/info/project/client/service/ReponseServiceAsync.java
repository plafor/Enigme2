package dpt.info.project.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ReponseServiceAsync {

	void majReponseEnigme(String idE, String idU, String reponse,
			AsyncCallback<Boolean> callback);

}
