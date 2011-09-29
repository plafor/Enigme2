package dpt.info.project.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractEditableCell;
import com.google.gwt.cell.client.AbstractInputCell;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

import dpt.info.project.client.model.EnigmeDatabase;
import dpt.info.project.client.model.EnigmeInfo;
import dpt.info.project.client.service.Connexion;
import dpt.info.project.client.service.ReponseService;


public class EnigmeTable extends Widget{

	private abstract static class PendingChange<T> {
		private final EnigmeInfo enigme;
		private final T value;

		public PendingChange(EnigmeInfo eni, T value) {
			this.enigme = eni;
			this.value = value;
		}

		/**
		 * Commit the change to the contact.
		 */
		public void commit() {
			doCommit(enigme, value);
		}

		/**
		 * Update the appropriate field in the {@link ContactInfo}.
		 *
		 * @param contact the contact to update
		 * @param value the new value
		 */
		protected abstract void doCommit(EnigmeInfo contact, T value);
	}

	private static class LastReponseChange extends PendingChange<String> {

		public LastReponseChange(EnigmeInfo eni, String value) {
			super(eni, value);
		}

		@Override
		protected void doCommit(EnigmeInfo eni, String value) {
			eni.setReponse(value);
		}
	}

	private static interface GetValue<C> {
		C getValue(EnigmeInfo contact);
	}


	private List<PendingChange<?>> pendingChanges = new ArrayList<PendingChange<?>>();
	
	/**
	   * The list of cells that are editable.
	   */
	  private List<AbstractEditableCell<?, ?>> editableCells;

	/**
	 * Add a column with a header.
	 *
	 * @param <C> the cell type
	 * @param cell the cell used to render the column
	 * @param headerText the header string
	 * @param getter the value getter for the cell
	 */
	private <C> Column<EnigmeInfo, C> addColumn(Cell<C> cell, String headerText,
			final GetValue<C> getter, FieldUpdater<EnigmeInfo, C> fieldUpdater) {
		Column<EnigmeInfo, C> column = new Column<EnigmeInfo, C>(cell) {
			@Override
			public C getValue(EnigmeInfo object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		if (cell instanceof AbstractEditableCell<?, ?>) {
			editableCells.add((AbstractEditableCell<?, ?>) cell);
		}
		table.addColumn(column, headerText);
		return column;
	}


	/**
	 * Initialize this example.
	 */
	
	private CellTable<EnigmeInfo> table;

	public Widget onInitialize() {

		editableCells = new ArrayList<AbstractEditableCell<?, ?>>();
		
		// init la table avec les enigmes
		EnigmeDatabase.get();
		
		
		// Create a table.
		table = new CellTable<EnigmeInfo>(15, EnigmeInfo.KEY_PROVIDER);

		// Add the id column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return String.valueOf(object.getId());
			}
		}, "Id");
		//table.getColumn(0).setSortable(true);

		// Add the title column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getTitle();
			}
		}, "Titre");
		//table.getColumn(1).setSortable(true);
		// Add the desc column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getDescription();
			}
		}, "Description");

		// Add the date column.
		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				return object.getCreationDate();
			}
		}, "Date");
		//table.getColumn(3).setSortable(true);
		// Add the lien column.
		table.addColumn(new Column<EnigmeInfo, String>(new ActionCell<String>("Voir", new ActionCell.Delegate<String>() {

			@Override
			public void execute(String object) {
				//pageRediriger("test.html");
				pageOuvrir(object);
			}
		} )){

			@Override
			public String getValue(EnigmeInfo object) {
				return object.getLien();
			}}, "Lien");

		addColumn(new TextInputCell(), "Ma reponse", new GetValue<String>() {
			public String getValue(EnigmeInfo eni) {
				return eni.getReponse();
			}
		}, new FieldUpdater<EnigmeInfo, String>() {
			public void update(int index, EnigmeInfo object, String value) {
				//pendingChanges.add(new LastReponseChange(object, value));
				object.setReponse(value);
				ReponseService.Util.getInstance().majReponseEnigme(FenetreConnexion.getIdU(),object.getId(),object.getReponse(),new AsyncCallback<Boolean>(){
					public void onFailure(Throwable caught) {
						Window.alert("Pb lors de la maj de la reponse avc le SGBD");
					}
					public void onSuccess(Boolean result) {
					}					
				});
				table.redraw();
			}
		});

		table.addColumn(new Column<EnigmeInfo, String>(new TextCell()) {
			@Override
			public String getValue(EnigmeInfo object) {
				if (object.getSolution().equalsIgnoreCase(object.getReponse()))
					return "OK";
				else return "KO";
			}
		}, "Res");


		table.redraw();
		// Add the table to the database.
		
		EnigmeDatabase.get().addDataDisplay(table);

		//reponseColumn.getFieldUpdater().update(0, EnigmeDatabase.get().getDataProvider().getList().get(0), "poil");

		return table;
	}

	/**
	 * Redirect to  URL, changing by   the  windows  .location
	 * @param url The new URL who remplace the  current  like  http ://www .gwt.com/
	 */      
	public    static   native  void windowLocation(  String   url)/*-{
		$wnd.location = url;
	  }-*/;	  

	/**
	 * Open new (tab) browser  to URL, open with window.open() 
	 * @param url The new URL to open like http://www.gwt.com/
	 */  
	public static native void windowOpen(String url)/*-{
			$wnd.open(url);
	  }-*/;

	/**
	 * Redirige la page en cours  vers la nouvelle URL
	 * @param url L'url qui remplacera l'actuelle
	 */  
	public static void pageRediriger(String url)
	{
		windowLocation(url);
	}	 

	/**
	 * Ouvre dans le  navigateur  , une nouvelle page ou onglet a    destination    de URL
	 * @param url L'url a ouvrir dans un nouvel onglet ou un  nouveau navigateur
	 */  
	public static void pageOuvrir(String url)
	{
		windowOpen(url);
	}
}