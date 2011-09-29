package dpt.info.project.client.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.view.client.ProvidesKey;


/**
   * Information about an enigma.
   */
  public class EnigmeInfo implements Comparable<EnigmeInfo>, Serializable {

    /**
     * The key provider that provides the unique ID of an enigma.
     */
   public static final ProvidesKey<EnigmeInfo> KEY_PROVIDER = new ProvidesKey<EnigmeInfo>() {
      public Object getKey(EnigmeInfo item) {
        return item == null ? null : item.getId();
      }
    };

    private String reponse;
    private String solution;
    private String lien;
    public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public String getSolution() {
		return solution;
	}
    private volatile int nbBonnesReponses;

	public int getNbBonnesReponses() {
		return nbBonnesReponses;
	}

	public void setNbBonnesReponses(int nbBonnesReponses) {
		this.nbBonnesReponses = nbBonnesReponses;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	private String creationDate;
    private String title;
    private String id;
    public void setId(String id) {
		this.id = id;
	}
	private String description;

    public EnigmeInfo() {
      
    }

    public int compareTo(EnigmeInfo o) {
      return (o == null || o.title == null) ? -1
          : -o.title.compareTo(title);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof EnigmeInfo) {
        return id == ((EnigmeInfo) o).id;
      }
      return false;
    }

    /**
     * @return the answer
     */
    public String getReponse() {
      return reponse;
    }

    /**
     * @return the creation date
     */
    public String getCreationDate() {
      return creationDate;
    }


    /**
     * @return the contact's firstName
     */
    public String getTitle() {
      return title;
    }


    /**
     * @return the unique ID of the contact
     */
    public String getId() {
      return this.id;
    }

    /**
     * @return the enigme description
     */
    public String getDescription() {
      return description;
    }

    @Override
    public int hashCode() {
      return id.hashCode();
    }

    /**
     * Set the answer
     *
     * @param answer
     */
    public void setReponse(String ans) {
      this.reponse = ans;
    }

    /**
     * Set the creation date
     *
     * @param date
     */
    public void setCreationDate(String d) {
      this.creationDate = d;
    }

    /**
     * Set the title.
     *
     * @param the title to set
     */
    public void setTitle(String t) {
      this.title = t;
    }

    /**
     * Set the desc
     *
     * @param the desc to set
     */
    public void setDescription(String desc) {
      this.description = desc;
    }
  }