package dpt.info.project.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dpt.info.project.client.model.EnigmeInfo;
import dpt.info.project.client.model.UserInfo;
import dpt.info.project.client.service.EnigmeService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



public class EnigmeServiceImpl extends RemoteServiceServlet implements EnigmeService {

	@Override
	public boolean creeEnigme(int id, String titre, String desc, String solution, String lien, String date) {
		ConBDD connexion=new ConBDD();
		String requete="INSERT INTO `Enigme` (`id`, `titre`, `desc`, `date`, `solution`, `lien`) VALUES ('"+id+"', '"+titre+"', '"+desc+"', '"+date+"', '"+solution+"', '"+lien+"')"; 
		String resultat=connexion.setData(requete);
		connexion.fermer();
		if (resultat==null)
			return false;
		if (resultat.equals("OK"))
			return true;
		else return false;
	}

	@Override
	public EnigmeInfo[] recupEnigmes(String idU) {
		ConBDD connexion=new ConBDD();
		List<EnigmeInfo> liste=new ArrayList<EnigmeInfo>();

		String requete="SELECT * FROM Enigme ORDER BY id";

		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				EnigmeInfo p=new EnigmeInfo();
				p.setId(resultat.getString("id")); 
				p.setTitle(resultat.getString("titre")); 
				p.setDescription(resultat.getString("desc"));
				p.setSolution(resultat.getString("solution"));
				p.setLien(resultat.getString("lien"));
				p.setCreationDate(resultat.getString("date"));
				// recup de la reponse
				String rep = recupReponseEnigme(p.getId(),idU);
				if (rep!=null)
					p.setReponse(rep);
				else p.setReponse("");
				//recup nb bonnes reponses de tlm a cette enigme
				p.setNbBonnesReponses(this.recupNbBonnesReponsesParEnigme(p.getId()));
				
				liste.add(p);
			}
			EnigmeInfo[] res=new EnigmeInfo[liste.size()];
			for (int i=0;i<liste.size();i++)
				res[i]=liste.get(i);
					
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			connexion.fermer();
		}
	}

	@Override
	public String recupReponseEnigme(String idE, String idU) {
		ConBDD connexion=new ConBDD();
		String requete="SELECT reponse FROM Rep WHERE idE='"+idE+"' AND idU='"+idU+"'";
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			if (resultat.next())
				if (resultat==null)
					return null;
				else return resultat.getString("reponse");
			else return null;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			connexion.fermer();
		}
	}

	@Override
	public int recupNbBonnesReponsesParEnigme(String idE) {
		ConBDD connexion=new ConBDD();
		String requete="SELECT COUNT(Rep.idU) AS RES FROM Rep, Enigme WHERE Rep.idE='"+idE+"' AND Rep.reponse = Enigme.solution"; 
			//"SELECT COUNT(Rep.idE) FROM Rep, Enigme WHERE Rep.idU='a' AND Rep.reponse = Enigme.solution 
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return 0;
		try {
			if (resultat.next())
				if (resultat==null)
					return 0;
				else return resultat.getInt("RES");
			else return 0;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			connexion.fermer();
		}
		
	
	}

	@Override
	public String[] recupUsersBonneReponsesParEnigme(String idE) {
		ConBDD connexion=new ConBDD();
		List<String> liste=new ArrayList<String>();

		String requete="SELECT nom, prenom FROM User, Rep, Enigme WHERE User.id = Rep.idU AND rep.idE = '"+idE+"' AND Rep.reponse = Enigme.solution ";

		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				String res=new String();
				res = resultat.getString("nom"); 
				res+= " ";
				res += resultat.getString("prenom");
				liste.add(res);
			}
			String[] res=new String[liste.size()];
			for (int i=0;i<liste.size();i++)
				res[i]=liste.get(i);
					
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			connexion.fermer();
		}
	}

	@Override
	public UserInfo[] recupUsers() {
		ConBDD connexion=new ConBDD();
		List<UserInfo> liste=new ArrayList<UserInfo>();

		String requete="SELECT * FROM User ORDER BY id";

		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				UserInfo p=new UserInfo();
				p.setId(resultat.getString("id")); 
				p.setNom(resultat.getString("nom")); 
				p.setPrenom(resultat.getString("prenom"));
				
				//recup nb bonnes reponses aux enigmes
				p.setNbEnigmesResolues(recupNbBonnesReponsesParUser(p.getId()));
				
				liste.add(p);
			}
			UserInfo[] res=new UserInfo[liste.size()];
			for (int i=0;i<liste.size();i++)
				res[i]=liste.get(i);
					
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			connexion.fermer();
		}
	}

	private int recupNbBonnesReponsesParUser(String idU) {
		ConBDD connexion=new ConBDD();
		String requete="SELECT COUNT(Rep.idE) AS RES FROM Rep, Enigme WHERE Rep.idU='"+idU+"' AND Rep.reponse = Enigme.solution"; 
		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return 0;
		try {
			if (resultat.next())
				if (resultat==null)
					return 0;
				else return resultat.getInt("RES");
			else return 0;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			connexion.fermer();
		}
	}

	@Override
	public String[] recupEnigmesBonneReponsesParUser(String idU) {
		ConBDD connexion=new ConBDD();
		List<String> liste=new ArrayList<String>();

		String requete="SELECT Enigme.id, titre FROM User, Rep, Enigme WHERE User.id = Rep.idU AND rep.idU = '"+idU+"' AND Rep.reponse = Enigme.solution ";

		ResultSet resultat=connexion.getData(requete);
		if (resultat==null)
			return null;
		try {
			while (resultat.next()){
				String res=new String();
				res = resultat.getString("id"); 
				res+= " - ";
				res += resultat.getString("titre");
				liste.add(res);
			}
			String[] res=new String[liste.size()];
			for (int i=0;i<liste.size();i++)
				res[i]=liste.get(i);
					
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			connexion.fermer();
		}
	}
}
