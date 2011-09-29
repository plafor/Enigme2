package dpt.info.project.client.ui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

import dpt.info.project.client.model.EnigmeDatabase;
import dpt.info.project.client.service.EnigmeService;


public class EnigmeCreator extends DecoratorPanel {

	TextBox titre, numE, sol, lien;
	DateBox dateE;
	TextArea desc;
	
	public EnigmeCreator() {
		super();
		//this.setWidth("850px");
		// Create a table to layout the form options
	    FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    layout.setWidth("450px");
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

	    // Add a title to the form
	    //layout.setHTML(0, 0, "<h4>Ajout d'une enigme</h4>");
	    //cellFormatter.setColSpan(0, 0, 3);
	    //cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    // Add some standard form options
	    layout.setHTML(1, 0, "Numero d'enigme");
	    numE = new TextBox();
	    //numE.setSize("400px","20px");
	    layout.setWidget(1, 1, numE);
	    numE.setName("numE");
	    layout.setHTML(2, 0, "Titre");
	    titre = new TextBox();
	    titre.setSize("400px","20px");
	    layout.setWidget(2, 1, titre);
	    //cellFormatter.setColSpan(2, 1, 2);
	    titre.setName("titreE");
	    layout.setHTML(3, 0, "Description");
	    desc = new TextArea();
	    desc.setSize("400px", "100px");
	    layout.setWidget(3, 1, desc);
	    //cellFormatter.setColSpan(3, 1, 3);
	    desc.setName("descE");
	    layout.setHTML(4, 0, "Solution");
	    sol = new TextBox();
	    sol.setSize("400px","20px");
	    layout.setWidget(4, 1, sol);
	    sol.setName("solE");
	    layout.setHTML(5, 0, "Date creation");
	    DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
	    dateE = new DateBox();
	    dateE.setFormat(new DateBox.DefaultFormat(dateFormat));
	    layout.setWidget(5, 1, dateE);
	    
	    layout.setHTML(6, 0, "Lien");
	    lien = new TextBox();
	    lien.setSize("400px","20px");
	    layout.setWidget(6, 1, lien);
	    layout.setHTML(7, 0, "<small>Tapez une url (\"http://www.xxx...\") ou bien precisez seulement le nom du fichier uploade (\"fichier.gif\")</small>");
	    cellFormatter.setColSpan(7, 0, 2);
	    // Add a button to upload the file
	    Button uploadButton = new Button("Creer");
	    
	    uploadButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        if ((titre.getText().length()>0) && (numE.getText().length()>0)&& (lien.getText().length()>0) 
	        		&& (desc.getText().length()>0) && (sol.getText().length()>0) ){
	        	   	EnigmeService.Util.getInstance().creeEnigme(Integer.parseInt(numE.getText()), titre.getText(), desc.getText(), sol.getText(), lien.getText(), dateE.getTextBox().getText(), new AsyncCallback<Boolean>(){

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Erreur lors de la creation de l'enigme");
					}

					@Override
					public void onSuccess(Boolean result) {
						Window.alert("Enigme creee avec succes");
						clean();
						EnigmeDatabase.get().refreshDisplays();
					}
	        		
	        	});
	        	//Window.alert("Bien recu");
	        } else {
	          Window.alert("Veuillez remplir tous les champs");
	        }
	      }
	    });
	    layout.setWidget(8,0,uploadButton);
	    cellFormatter.setColSpan(8, 0, 2);
	    cellFormatter.setHorizontalAlignment(8, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    this.setWidget(layout);
	}
	
	private void clean(){
		titre.setText("");
		desc.setText("");
		numE.setText("");
		sol.setText("");
		lien.setText("");
	}

}
