package dpt.info.project.server;

import dpt.info.project.client.service.ReponseService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ReponseServiceImpl extends RemoteServiceServlet implements ReponseService {

	@Override
	public boolean majReponseEnigme(String idU, String idE, String reponse) {
		try {
			String requete="UPDATE `Rep` SET reponse = '"+reponse+"' WHERE idU = '"+idU+"' AND idE ="+idE;
			ConBDD connexion=new ConBDD();
			String resultat=connexion.setData(requete);
			
			if (resultat == null || resultat.equals("Error") ) {
				//System.out.println("bordel ca marche pas l'update");
				requete="INSERT INTO `Rep`(idU, idE, reponse) VALUES ('"+idU+"'"+", "+idE+", '"+reponse+"')"; 
				resultat=connexion.setData(requete);
				connexion.fermer();
				if (resultat==null)
					return false;
				if (resultat.equals("OK"))
					return true;
					else return false;
				
			}
			connexion.fermer();
		}
		catch (Exception e){
			return false;
		}
		return true;
	}
}
