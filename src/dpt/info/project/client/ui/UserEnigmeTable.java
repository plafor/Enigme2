package dpt.info.project.client.ui;

import com.google.gwt.cell.client.AbstractInputCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;


import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

import dpt.info.project.client.model.EnigmeDatabase;
import dpt.info.project.client.model.EnigmeInfo;
import dpt.info.project.client.model.UserEnigmeDatabase;
import dpt.info.project.client.model.UserInfo;


public class UserEnigmeTable extends Widget{


	
	/**
	 * Initialize this example.
	 */

	public Widget onInitialize() {
		// Create a table.
		final CellTable<UserInfo> table = new CellTable<UserInfo>(30,
				UserInfo.KEY_PROVIDER);

		// Add the id column.
		table.addColumn(new Column<UserInfo, String>(new TextCell()) {
			@Override
			public String getValue(UserInfo object) {
				return String.valueOf(object.getId());
			}
		}, "Id");
		// Add the name column.
		table.addColumn(new Column<UserInfo, String>(new TextCell()) {
			@Override
			public String getValue(UserInfo object) {
				return object.getPrenom()+" "+object.getNom().toUpperCase();
			}
		}, "Identite");
		
		// Add the NbE column.
		table.addColumn(new Column<UserInfo, String>(new TextCell()) {
			@Override
			public String getValue(UserInfo object) {
				return new Integer(object.getNbEnigmesResolues()).toString();
			}
		}, "Nb enigmes OK");
		
		// Add the lien column.
		table.addColumn(new Column<UserInfo, String>(new ActionCell<String>("Details", new ActionCell.Delegate<String>() {
		
			@Override
			public void execute(String idU) {
				new FenetreDetailReponseUser(idU);
			}
		} )){

			@Override
			public String getValue(UserInfo object) {
				return object.getId();
			}}, "Details");
		
		// Add the table to the database.
		UserEnigmeDatabase.get().addDataDisplay(table);

		return table;
	}
}