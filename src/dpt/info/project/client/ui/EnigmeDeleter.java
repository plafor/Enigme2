package dpt.info.project.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

public class EnigmeDeleter extends DecoratorPanel {

	private ListBox dropBox;

	public EnigmeDeleter() {
		super();
		this.setWidth("280px");
		// Create a table to layout the form options
	    FlexTable layout = new FlexTable();
	    layout.setCellSpacing(6);
	    layout.setWidth("260px");
	    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();
	    
	    // Add a title to the form
	    //layout.setHTML(0, 0, "<h4>Suppression d'une enigme</h4>");
	    //cellFormatter.setColSpan(0, 0, 2);
	    //cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add some standard form options
	    layout.setHTML(0, 0, "Identifiant de l'enigme : ");
	    //TextBox numE = new TextBox();
	    
	    dropBox = new ListBox(false);
	    String[] listTypes = recupListeEnigme();
	    for (int i = 0; i < listTypes.length; i++) {
	      dropBox.addItem(listTypes[i]);
	    }
	    
	    //layout.setWidth("450px");
	    //dropBox.setWidth("100px");
	    layout.setWidget(0, 1, dropBox);
	    
	    // Add a button to upload the file
	    Button uploadButton = new Button("Supprimer");
	    
	    uploadButton.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	          Window.alert("Enigme supprime (deconnexion/connexion pour visualiser le changement");
	          clean();
	      }
	      
	    });
	    layout.setWidget(2,0,uploadButton);
	    cellFormatter.setColSpan(2, 0, 2);
	    cellFormatter.setHorizontalAlignment(
	        2, 0, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    // Wrap the content in the DecoratorPanel
	    this.setWidget(layout);
	    
	}
	
	private String[] recupListeEnigme() {
		return new String[]{"1","2","3","1","2","3","1","2","3","1","2","3"};
	}

	private void clean(){
		// recuperer id et maj de la liste
		dropBox = new ListBox(false);
		String[] listTypes = recupListeEnigme();
	    for (int i = 0; i < listTypes.length; i++) {
	      dropBox.addItem(listTypes[i]);
	    }
		
	}

}
