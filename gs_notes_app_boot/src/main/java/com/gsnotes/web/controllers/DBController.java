package com.gsnotes.web.controllers;

import com.boudaa.tools.DataTreatement;
import com.boudaa.tools.ExcelHandler;
import com.boudaa.tools.ExcelHandlerException;
import com.boudaa.tools.TestMoyenneException;
import com.boudaa.tools.TestNotesElementsException;
import com.boudaa.tools.TestValidationException;
import com.gsnotes.bo.Utilisateur;
import com.gsnotes.services.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Repository
public class DBController {
	private static final String ANSI_BLACK = "\u001B[30m";
	
	@Autowired
	private IPersonService iPersonService;
	
	@GetMapping("/admin/test")
	public String getUtilisateur()  throws ExcelHandlerException, TestMoyenneException , TestValidationException{
		
		

		try {
			System.out.println("BEGINNING");
			
			DataTreatement.VerifierFormatFichier("G:\\GI2\\S2\\JAVA EE\\ExcelReader\\ExcelReader\\noteDelib.xlsx");
			
			List<ArrayList<Object>> lista = new ArrayList<ArrayList<Object>>();
			lista = ExcelHandler.readFromExcel("G:\\GI2\\S2\\JAVA EE\\ExcelReader\\ExcelReader\\noteDelib.xlsx", 0);
			
			String DBNomEnseignant = iPersonService.getPersonByCin("AAAA").getNom();
			String DBPrenomEnseignant = iPersonService.getPersonByCin("AAAA").getNomArabe();
			String DBEnseignant = DBPrenomEnseignant+" "+DBNomEnseignant;
			DataTreatement.VerifierEnseignant(lista, DBEnseignant );
			
//			DataTreatement.VerifierNotesElements(lista);
			DataTreatement.VerifierValidation(lista);
			DataTreatement.VerifierAnnee(lista);
			
			

			}catch(TestNotesElementsException ex) {
				
				System.out.print(ex.getMessage() );
				
			}catch(TestMoyenneException ex) {
				
				System.out.print(ex.getMessage() );
				
			}catch(TestValidationException ex) {
				
				System.out.print(ex.getMessage() );
				
			}catch(ExcelHandlerException ex) {
				
				System.out.print(ex.getMessage() );
			}catch(Exception ex) {
			
			System.out.print(ex.getMessage() );
		}
			System.out.println(ANSI_BLACK+"\n ENDING");
			return "admin/adminHome";
	}

}
