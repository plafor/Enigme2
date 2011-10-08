package dpt.info.project.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import dpt.info.project.client.service.Connexion;



public class OngletProfil extends VerticalPanel {

	TextArea zone2Text;

	private int cpt = 1;

	public OngletProfil() {
		super();
		zone2Text = new TextArea();
		zone2Text.setVisibleLines(10);
		Button bouton = new Button("envoyer");
		bouton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Connexion.Util.getInstance().testEnvoie("coucou !", new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
					}
					
				});
			}
		});

		add(zone2Text);
		add(bouton);
		
		refresh();
	}

	private void refresh() {
		Connexion.Util.getInstance().getNewMessage(cpt , new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String result) {
				zone2Text.setText(zone2Text.getText()+"\n"+result);
				cpt++;
				refresh();
			}
		});
	}


}

