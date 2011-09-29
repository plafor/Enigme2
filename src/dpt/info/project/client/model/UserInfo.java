package dpt.info.project.client.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.view.client.ProvidesKey;


/**
   * Information about an enigma.
   */
  /**
 * @author plaforca
 *
 */
public class UserInfo implements Comparable<UserInfo>, Serializable {

    /**
     * The key provider that provides the unique ID of a user.
     */
   public static final ProvidesKey<UserInfo> KEY_PROVIDER = new ProvidesKey<UserInfo>() {
      public Object getKey(UserInfo item) {
        return item == null ? null : item.getId();
      }
    };

    private String id;
    private String prenom, nom;
    private volatile int nbEnigmesResolues = 0;
    private boolean admin;
    
    public void setId(String id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public int getNbEnigmesResolues() {
		return nbEnigmesResolues;
	}

	public void setNbEnigmesResolues(int nbEnimesResolues) {
		this.nbEnigmesResolues = nbEnimesResolues;
	}

	public UserInfo() {
      super();
    }

    public int compareTo(UserInfo o) {
      return (o == null || o.id == null) ? -1
          : -o.id.compareTo(id);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof UserInfo) {
        return id == ((UserInfo) o).id;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return Integer.parseInt(id.substring(id.length()-3));
    }

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

   
  }