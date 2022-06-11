package com.boudaa.tools;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;

/*------------ Fatima Ezzahra HAMDAOUI-------------
 * 
 * 
 * 
 * 
 *   1- cette class contient l'ensemble des traitement de verifications a faire afin d'obtenir un fichier xlsx susceptible d'etre stocke dans la base de donnees 
 *   2- tous les traitements de fonctionnalites sont implementes dans le controlleur y inclus la comparaison de nom de l'enseignant de Excel avec celle dans la base de donnees 
 *   
 *   
 *   
 *   
 *   
 *   
 *   */
public class DataTreatement {
	
	private static final String ANSI_ROUGE = "\u001B[31m";
	private static final String ANSI_VERT = "\u001B[32m";
	private static final String ANSI_BLACK = "\u001B[30m";
	public DataTreatement() {
		
	}
//	public static void ExtraireListe(List<ArrayList<Object>> lista) {
//		
//	}
	

	//=================== Verifier les notes des modules =============================
	public static void VerifierNotesElements(List<ArrayList<Object>> lista) throws TestNotesElementsException {
		System.out.print("**Verification des notes des modules**\n");
		List<ArrayList<Object>> notes = lista.subList(3, lista.size());
		Iterator <ArrayList<Object>> it = notes.iterator();
		while(it.hasNext()) {
			  ArrayList<Object> iter = it.next();//new ArrayList<Object>();
			  double note1 = (double)iter.get(4);
			  double note2 = (double)iter.get(5);
			  
			  if(note1<0.0 ||  note2<0 || note1>20.0 || note2>20.0) throw new TestNotesElementsException(ANSI_ROUGE+"la note de "+iter.get(2)+" "+iter.get(3)+" est mal remplie!!") ;
			  else {
//				  System.out.print(iter.get(4) +" "+iter.get(5)); 
//				  System.out.print("\n");
				  continue;
			  }  
		}
		 System.out.println(ANSI_VERT+ "les notes sont Valides");
	}
	
	//=================== Vérifier le format du fichier =============================
	
	public static boolean VerifierFormatFichier(String url) {
		System.out.print(ANSI_BLACK+"**Verification de format du fichier**\n");
		String str = url.toLowerCase();
		if(str.endsWith(".xlsx") || str.endsWith(".xls")) {
			return true;
		}else return false;
	}
	
	//=================== Verifier le Nom de l'enseignant =============================
	
	public static void VerifierEnseignant(List<ArrayList<Object>> lista,String DBNomEnseignant) {
		System.out.print(ANSI_BLACK+"**Verification de Nom de l'enseignant**\n");
		String ExcelNomEnseignant = (String)lista.get(1).get(1);
		if(ExcelNomEnseignant.toLowerCase().equals(DBNomEnseignant.toLowerCase())) System.out.print(ANSI_VERT+"Enseignant Trouve dans la base de donnees\n");
		else System.out.print(ANSI_ROUGE+"Enseignant n'est pas trouve dans la base de donnees\n");
		
	}
	//=================== Verifier la formule de moyenne  =============================
	
	public static void VerifierFormuleMoyenne(List<ArrayList<Object>> lista) throws TestNotesElementsException , TestMoyenneException {
		System.out.print(ANSI_BLACK+"**Verification de la formule de moyenne**\n");
		VerifierNotesElements(lista); 
		List<ArrayList<Object>> notes = lista.subList(3, lista.size()); 
		Iterator <ArrayList<Object>> it = notes.iterator();
		while(it.hasNext()) { 
			  ArrayList<Object> iter = it.next();
			  
			  double note1 = (double)iter.get(4);
			  double note2 = (double)iter.get(5);
			  double moy   = (double)iter.get(6);
			  
			  if(!((note1+note2)/2 == moy)) {
				  throw new TestMoyenneException(ANSI_ROUGE+"vous devez verifier la formule de calcul de moyenne de "+iter.get(2)+" "+iter.get(3)+"!\\n");
			  }
//			  else {
//				  System.out.print("la moyenne de "+iter.get(2)+" est "+iter.get(6)); 
//				  System.out.print("\n");
//			  }  
		}
		
	}
	//=================== Verifier la Validation ( par session Normal-Rattrapage )  =============================
	
	public static void VerifierValidation(List<ArrayList<Object>> lista) throws TestNotesElementsException , TestMoyenneException ,TestValidationException{
		System.out.print(ANSI_BLACK+"**Verification de la colonne Validation - par session**\n");
		VerifierFormuleMoyenne(lista);
		List<ArrayList<Object>> notes = lista.subList(3, lista.size());
		Iterator <ArrayList<Object>> it = notes.iterator();
		while(it.hasNext()) {
			  ArrayList<Object> iter = it.next();
			  
			  double moy   		= (double)iter.get(6);
			  String Validation = (String)iter.get(7);
			  String Session    = getSession(lista);
			  
			  if(Session.equals("Normal")) {
				  if( (moy>=12 && Validation.equals("V")) || (moy<12 && Validation.equals("R"))) {
					  continue;
				  }else {
					  throw new TestValidationException(ANSI_ROUGE+"Verifier la colonne Validation de "+iter.get(2)+" "+iter.get(3)+"!!\n");
				  }
			  }else if(Session.equals("Rattrapage")) {
				  if( (moy>=12 && Validation.equals("V")) || (moy<12 && Validation.equals("NV"))) {
					  continue;
				  }else {
					  throw new TestValidationException(ANSI_ROUGE+"Verifier la colonne Validation de "+iter.get(2)+" "+iter.get(3)+"!!\n");
				  }
			  }
		}
		System.out.print(ANSI_BLACK+"tout est bien!");
			
	}

	public static String getSession(List<ArrayList<Object>> lista) {
		String str = (String)lista.get(1).get(3);
		return str;
	}
	
	//=================== Verifier l’année universitaire =============================
	//fonction outil 
	public static String getCurrentYear(){
	        int year=new Date().getYear()+1900;
	        String str = (year-1)+"/"+year;
			return str;
	}
	public static void VerifierAnnee(List<ArrayList<Object>> lista) {
		System.out.print(ANSI_BLACK+"**Verification de l’année universitaire**\n");
		String ExcelAnnee = (String)lista.get(0).get(5);
		if( ExcelAnnee.equals(getCurrentYear() ) ) System.out.print(ANSI_VERT+"Annee Valide");
		else System.out.print(ANSI_ROUGE+"Annee n'est pas Valide");
		
	}
	 
}
