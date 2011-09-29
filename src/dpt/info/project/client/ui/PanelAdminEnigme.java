package dpt.info.project.client.ui;

import java.util.Iterator;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasVerticalAlignment;




public class PanelAdminEnigme extends VerticalPanel {

	public PanelAdminEnigme() {
		super();
		FlexTable table = new FlexTable();
		table.setCellPadding(2);
		VerticalPanel panelG = new VerticalPanel();
		panelG.add(new HTML("<h4>Ajouter une enigme</h4>"));
		panelG.add(new EnigmeCreator());
		
		table.setWidget(0, 1, panelG);
		FlexCellFormatter cellFormatter = table.getFlexCellFormatter();
		table.getFlexCellFormatter().setRowSpan(0, 1, 2);
	    
		VerticalPanel panelDH = new VerticalPanel();
		panelDH.add(new HTML("<h4>Uploader un ou des fichier(s)</h4>"));
		panelDH.add(new FichierUpload());
		
		table.setWidget(0, 0, panelDH);
		//cellFormatter.setRowSpan(0, 0, 2);
		
		this.add(table);
		
		VerticalPanel panelDB = new VerticalPanel();
		panelDB.add(new HTML("<h4>Retirer une enigme existante</h4>"));
		EnigmeDeleter enigmeDeleter = new EnigmeDeleter();
		panelDB.add(enigmeDeleter);
		enigmeDeleter.setWidth("280px");
		table.setWidget(1, 0, panelDB);
	}	
}
