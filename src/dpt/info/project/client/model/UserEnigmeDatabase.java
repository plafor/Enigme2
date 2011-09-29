package dpt.info.project.client.model;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import dpt.info.project.client.service.EnigmeService;
import dpt.info.project.client.ui.FenetreConnexion;

public class UserEnigmeDatabase {

	  /**
	   * The singleton instance of the database.
	   */
	  public static UserEnigmeDatabase instance;

	  /**
	   * Get the singleton instance of the contact database.
	   *
	   * @return the singleton instance
	   */
	  public static UserEnigmeDatabase get() {
	    if (instance == null) {
	      instance = new UserEnigmeDatabase();
	    }
	    return instance;
	  }

	  /**
	   * The provider that holds the list of contacts in the database.
	   */
	  private ListDataProvider<UserInfo> dataProvider = new ListDataProvider<UserInfo>();

	  //private final Category[] categories;
	  
	 // private static int cptEnigme = 1;

	  /**
	   * Construct a new user database.
	   */
	  private UserEnigmeDatabase() {
		  EnigmeService.Util.getInstance().recupUsers(new AsyncCallback<UserInfo[]>(){
				public void onFailure(Throwable caught) {
					Window.alert("Probleme lors de la recuperations des infos sur les users");
				}
				public void onSuccess(UserInfo[] result) {
					if (result!=null){
						for (int i=0;i<result.length;i++)
							addUser(result[i]);
					}
				}  
			});
	  }

	  /**
	   * Add a new user.
	   *
	   * @param the user to add.
	   */
	  public void addUser(UserInfo user) {
	    List<UserInfo> contacts = dataProvider.getList();
	    // Remove the contact first so we don't add a duplicate.
	    contacts.remove(user);
	    contacts.add(user);
	  }

	  /**
	   * Add a display to the database. The current range of interest of the display
	   * will be populated with data.
	   *
	   * @param display a {@Link HasData}.
	   */
	  public void addDataDisplay(HasData<UserInfo> display) {
	    dataProvider.addDataDisplay(display);
	  }


	  public ListDataProvider<UserInfo> getDataProvider() {
	    return dataProvider;
	  }

	 
	  /**
	   * Refresh all displays.
	   */
	  public void refreshDisplays() {
	    dataProvider.refresh();
	  }
	}

