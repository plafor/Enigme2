package dpt.info.project.client.ui;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.TextCell;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;

import com.google.gwt.user.client.ui.Widget;

import dpt.info.project.client.model.EnigmeDatabase;
import dpt.info.project.client.model.EnigmeInfo;


public class EnigmeUserTable extends Widget{

	public Widget onInitialize() {
		// Create a table.
		
		final CellTable<EnigmeInfo> table = new CellTable<EnigmeInfo>(15,
				EnigmeInfo.KEY_PROVIDER);

		// Add the id column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return String.valueOf(object.getId());
			}
		}, "Id");
		// Add the title column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getTitle();
			}
		}, "Titre");
		// Add the nbRep column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return new Integer(object.getNbBonnesReponses()).toString();
			}
		}, "Nb rep OK");
		
		// Add the lien column.
		table.addColumn(new Column<EnigmeInfo, String>(new ActionCell<String>("Details", new ActionCell.Delegate<String>() {
		
			@Override
			public void execute(String idE) {
				new FenetreDetailReponseEnigme(idE);
			}
		} )){

			@Override
			public String getValue(EnigmeInfo object) {
				return object.getId();
			}}, "Details");
		

		// Add the table to the database.
		EnigmeDatabase.get().addDataDisplay(table);

		return table;
	}
}