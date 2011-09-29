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


public class FenetreDetailReponseEnigme extends DialogBox {

	public FenetreDetailReponseEnigme(String idE){
		super();
		this.setText("Details des reponses d'une enigme");
		//dialogBox.setSize("400px","200px");
		VerticalPanel pan = new VerticalPanel();
		pan.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		final HTML infos = new HTML("<H4>Nom  |  Prenom</H4>"); 
		pan.add(infos);

		EnigmeService.Util.getInstance().recupUsersBonneReponsesParEnigme(idE, new AsyncCallback<String[]>(){
			public void onFailure(Throwable caught) {
				Window.alert("Probleme lors de la recuperations des infos detaillees sur les bonnes reponses a cette enigme");
			}

			public void onSuccess(String[] result) {
				for (int i=0;i<result.length;i++)
					infos.setHTML(infos.getHTML() + "<p>"+result[i]+"</p>"); 
			}
		});

		// Add a close button at the bottom of the dialog
		Button closeButton = new Button(
				"Fermer", new ClickHandler() {
					public void onClick(ClickEvent event) {
						FenetreDetailReponseEnigme.this.hide();
					}
				});
		pan.add(closeButton);
		this.setWidget(pan);
		this.center();
		this.show();
	}
}
