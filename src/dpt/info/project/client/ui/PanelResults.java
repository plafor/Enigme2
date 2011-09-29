package dpt.info.project.client.ui;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;


public class PanelResults extends VerticalPanel {

	public PanelResults() {
		super();
		this.setSpacing(10);
		
		DecoratorPanel panel = new DecoratorPanel();
		this.add(new HTML("<h4>Resultats par etudiant</h4>"));
		panel.add(new UserEnigmeTable().onInitialize());
		this.add(panel);
		
		DecoratorPanel panel2 = new DecoratorPanel();
		this.add(new HTML("<h4>Resultats par enigme</h4>"));
		panel2.add(new EnigmeUserTable().onInitialize());
		this.add(panel2);
	}
}