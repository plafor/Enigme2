package dpt.info.project.client;

import dpt.info.project.client.model.EnigmeDatabase;
import dpt.info.project.client.model.UserEnigmeDatabase;
import dpt.info.project.client.ui.EnigmeTable;
import dpt.info.project.client.ui.FenetreConnexion;
import dpt.info.project.client.ui.OngletEnigmeLazy;
import dpt.info.project.client.ui.PanelAdminEnigme;
import dpt.info.project.client.ui.PanelAdminUser;
import dpt.info.project.client.ui.PanelResults;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

import com.google.gwt.user.client.ui.HTML;

import com.google.gwt.user.client.ui.HorizontalPanel;

import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Image;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Enigme2 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
		+ "attempting to contact the server. Please check your network "
		+ "connection and try again.";


	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
	.create(GreetingService.class);

	
	private TabPanel tabPanel;
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {



		DockPanel dockPanel = new DockPanel();

		dockPanel.setHorizontalAlignment(dockPanel.ALIGN_CENTER);
		dockPanel.setWidth("70%");

		RootPanel rootPanel = RootPanel.get();
		rootPanel.add(dockPanel);

		tabPanel = new TabPanel();
		//tabPanel.setAnimationDuration(1000);
		dockPanel.add(tabPanel, DockPanel.CENTER);

		tabPanel.setSize("900px", "800px");

		tabPanel.add(new HTML("<h3>Bienvenue sur le site des enigmes</h3><br /><br />" +
				"<p>Vous pouvez commencer a resoudre les enigmes dans l'onglet dedie \"mes enigmes\".</p>" +
				"<p>Vous pouvez aussi modifier votre mot de passe et visualiser quelques statistiques dans la partie \"mon profil\".</p><br />" +
				"<p>Bonne chance !</p>" +
				"<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />" +
		"<small>Ce site a ete developpe avec GWT 2 - Droits reserves au Departement Informatique de l'IUT de Laval</small>"), "Accueil");
		//LazyPanel pour la table
		//EnigmeTable enigmeEcran = new EnigmeTable();
		//tabPanel.add(enigmeEcran.onInitialize(), "Mes enigmes");
		tabPanel.add(new OngletEnigmeLazy(),"Mes enigmes");
		//tabPanel.add(new HTML("<p>Ici viendront les enigmes.</p>"), "Mes enigmes");
		tabPanel.add(new HTML("<p>Ici viendra le profil.</p>"), "Mon profil");
		
		//tabPanel.add(new HTML("<p>il.</p>"), "A propos");
		//tabPanel.animate(1000);
		
		//tabPanel.setAnimationEnabled(true);

		VerticalPanel panelLeft = new VerticalPanel();
		panelLeft.setSpacing(10);
		dockPanel.add(panelLeft, DockPanel.WEST);
		panelLeft.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);

		Image image2 = new Image("img/logoIUT.gif");  
		panelLeft.add(image2);
		Image image = new Image("img/enigme.jpg");  
		panelLeft.add(image);

		//dockPanel.setCellHorizontalAlignment(panelLeft, HasHorizontalAlignment.ALIGN_CENTER);
		//panelHaut.setHeight("48px");
		//dockPanel.setCellVerticalAlignment(panelLeft, HasVerticalAlignment.ALIGN_MIDDLE);

		HorizontalPanel panelHaut = new HorizontalPanel();
		panelHaut.setSpacing(5);
		panelHaut.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		//panelHaut.setStyleName("topPanel");
		//Image imageTop = new Image("img/BlueWave2.jpg");  
		//imageTop.setSize("770px", "100px");
		//tabPanel.getElement().getStyle().setBackgroundImage("img/BlueWave2.jpg");
		Button btnMeDeconnecter = new Button("Me deconnecter");
		//btnMeDeconnecter.setEnabled(false);
		panelHaut.add(btnMeDeconnecter);
		btnMeDeconnecter.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				EnigmeDatabase.instance = null;
				UserEnigmeDatabase.instance=null;
				onModuleLoad();
				
			}
		});


		dockPanel.add(panelHaut, DockPanel.NORTH);
		dockPanel.setCellHorizontalAlignment(panelHaut, HasHorizontalAlignment.ALIGN_RIGHT);
		//panelHaut.setHeight("48px");
		dockPanel.setCellVerticalAlignment(panelHaut, HasVerticalAlignment.ALIGN_MIDDLE);

		tabPanel.setAnimationEnabled(true);
		tabPanel.selectTab(0);

		// Create the dialog box
		final DialogBox dialogBox = FenetreConnexion.createDialogBox(this);
		dialogBox.setGlassEnabled(true);
		dialogBox.setAnimationEnabled(true);

		// launch the dialog box
		dialogBox.center();
		dialogBox.show();	

	}

	public void majAdmin() {
		//tabPanel.remove(0);
		tabPanel.add(new PanelAdminEnigme(), "Admin enigmes");
		tabPanel.add(new PanelAdminUser(), "Admin etudiants");
		tabPanel.add(new PanelResults(), "Voir resultats");
		
	}

	
}
