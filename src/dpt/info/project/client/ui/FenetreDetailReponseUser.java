package dpt.info.project.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import dpt.info.project.client.service.EnigmeService;

public class FenetreDetailReponseUser extends DialogBox {
	
	public FenetreDetailReponseUser(String idU){
		super();
		this.setText("Details des enigmes resolues de l'etudiant");
		//dialogBox.setSize("400px","200px");
		VerticalPanel pan = new VerticalPanel();
		pan.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		final HTML infos = new HTML("<H4>Id    |    Titre</H4>"); 
		pan.add(infos);

		EnigmeService.Util.getInstance().recupEnigmesBonneReponsesParUser(idU, new AsyncCallback<String[]>(){
			public void onFailure(Throwable caught) {
				Window.alert("Probleme lors de la recuperations des infos detaillees sur les bonnes reponses de cet utilisateur");
			}

			public void onSuccess(String[] result) {
				//infos.setHTML(infos.getHTML() + "<table>");
				for (int i=0;i<result.length;i++){
					//String id = result[i].substring(0, result[i].indexOf(' '));
					//String titre = result[i].substring(result[i].indexOf(' '), result[i].length());
					infos.setHTML(infos.getHTML() + "<p>"+result[i]+"</p>");//"<tr><td align=\"center\">"+id+"</td><td align=\"center\">"+titre+"</td></tr><tr></tr>");
				}
				//infos.setHTML(infos.getHTML() + "</table>");
			}
		});

		// Add a close button at the bottom of the dialog
		Button closeButton = new Button(
				"Fermer", new ClickHandler() {
					public void onClick(ClickEvent event) {
						FenetreDetailReponseUser.this.hide();
					}
				});
		pan.add(closeButton);
		this.setWidget(pan);
		this.center();
		this.show();
	}
}