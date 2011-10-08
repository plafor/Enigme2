package dpt.info.project.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import dpt.info.project.client.model.UserInfo;
import dpt.info.project.client.service.Connexion;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class ConnexionImpl extends RemoteServiceServlet implements Connexion {
	
	private String mess;
	private int cpt = 0;

	@Override
	public UserInfo isIdentifie(String id, String mdp) {
		UserInfo user = new UserInfo();
		String requete="SELECT id,mdp,nom,prenom,admin FROM User WHERE id LIKE '"+id+"' AND mdp LIKE '"+mdp+"'";
		ConBDD connexion=new ConBDD();
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				UserInfo p=new UserInfo();
				p.setId(resultat.getString("id"));
				p.setNom(resultat.getString("nom"));
				p.setPrenom(resultat.getString("prenom"));
				p.setAdmin(resultat.getBoolean("admin"));
				connexion.fermer();
				return p;//String[]{"OK",resultat.getString("id")};
			}
			return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void testEnvoie(String message) {
		mess = message;
		cpt++;
		//mess.notifyAll();
	}

	@Override
	public String getNewMessage(int num) {
		while (cpt != num)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return mess;
	}
}
