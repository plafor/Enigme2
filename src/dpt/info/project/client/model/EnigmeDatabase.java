package dpt.info.project.client.model;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import dpt.info.project.client.service.EnigmeService;
import dpt.info.project.client.ui.FenetreConnexion;

import java.util.List;

/**
 * The data source for contact information used in the sample.
 */
public class EnigmeDatabase {

	/**
	 * The singleton instance of the database.
	 */
	public static EnigmeDatabase instance;

	/**
	 * Get the singleton instance of the contact database.
	 *
	 * @return the singleton instance
	 */
	public static EnigmeDatabase get() {
		if (instance == null) {
			instance = new EnigmeDatabase();
		}
		return instance;
	}

	/**
	 * The provider that holds the list of contacts in the database.
	 */
	private ListDataProvider<EnigmeInfo> dataProvider = new ListDataProvider<EnigmeInfo>();

	/**
	 * Construct a new contact database.
	 */
	private EnigmeDatabase() {
		EnigmeService.Util.getInstance().recupEnigmes(FenetreConnexion.getIdU(),new AsyncCallback<EnigmeInfo[]>(){
			public void onFailure(Throwable caught) {
				Window.alert("Probleme lors de la recuperations des infos sur les enigmes");
			}
			public void onSuccess(EnigmeInfo[] result) {
				if (result!=null){
					for (int i=0;i<result.length;i++)
						addContact(result[i]);
				}
			}  
		});
	}
	
	public void majNbReponse(){
		List<EnigmeInfo> contacts = dataProvider.getList();
		for (EnigmeInfo e : contacts){
			final EnigmeInfo modif = e;
			EnigmeService.Util.getInstance().recupNbBonnesReponsesParEnigme(e.getId(),new AsyncCallback<Integer>(){
				public void onFailure(Throwable caught) {
					Window.alert("Probleme lors de la recuperations des infos sur les enigmes");
				}
				public void onSuccess(Integer result) {
					modif.setNbBonnesReponses(result);
				}  
			});
			
		}
	}

	/**
	 * Add a new enigme.
	 *
	 * @param the enigme to add.
	 */
	public void addContact(EnigmeInfo enigme) {
		List<EnigmeInfo> contacts = dataProvider.getList();
		// Remove the contact first so we don't add a duplicate.
		contacts.remove(enigme);
		contacts.add(enigme);
	}

	/**
	 * Add a display to the database. The current range of interest of the display
	 * will be populated with data.
	 *
	 * @param display a {@Link HasData}.
	 */
	public void addDataDisplay(HasData<EnigmeInfo> display) {
		dataProvider.addDataDisplay(display);
	}

	public ListDataProvider<EnigmeInfo> getDataProvider() {
		return dataProvider;
	}

	/**
	 * Refresh all displays.
	 */
	public void refreshDisplays() {
		/*removeAll();
		EnigmeService.Util.getInstance().recupEnigmes(new AsyncCallback<EnigmeInfo[]>(){
			public void onFailure(Throwable caught) {
				Window.alert("Probleme lors de la recuperations des infos sur les enigmes");
			}
			public void onSuccess(EnigmeInfo[] result) {
				if (result!=null){
					for (int i=0;i<result.length;i++)
						addContact(result[i]);
				}
				//maj reponse
				majReponse();
				dataProvider.refresh();
			}  
		});*/
		
		dataProvider.refresh();
	}

/*	private void majReponse() {
		List<EnigmeInfo> eni = dataProvider.getList();
		for (EnigmeInfo e:eni)
			majEnigme(e);
		
	}

	private void majEnigme(final EnigmeInfo e) {
		EnigmeService.Util.getInstance().recupReponseEnigme(e.getId(),FenetreConnexion.getIdU(),new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
				Window.alert("Probleme lors de la recuperations de la reponse a l'enigme");
			}
			public void onSuccess(String result) {
				if (result!=null){
					e.setReponse(result);
					//dataProvider.refresh();
				}
			}  
		});
	}
*/
}
