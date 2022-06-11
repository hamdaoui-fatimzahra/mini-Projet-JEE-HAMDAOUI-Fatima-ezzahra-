package com.boudaa.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class Main {
	public static void main (String[] args) throws ExcelHandlerException, TestMoyenneException , TestValidationException{

		System.out.println("okkey");
		List<ArrayList<Object>> lista = new ArrayList<ArrayList<Object>>();
		try {
		lista = ExcelHandler.readFromExcel("G:\\GI2\\S2\\JAVA EE\\ExcelReader\\ExcelReader\\noteDelib.xlsx", 0);
		
		
		/*  il suffit d'appeler VerifierValidation pour valider les notes /la moyenne et la validation   */
		DataTreatement.VerifierValidation(lista);
//		DataTreatement.VerifierAnnee(lista);

		
		
		
		}catch(TestNotesElementsException ex) {
			System.out.print(ex.getMessage() );
		}catch(TestMoyenneException ex) {
			System.out.print(ex.getMessage() );
		}catch(TestValidationException ex) {
			System.out.print(ex.getMessage() );
		}catch(ExcelHandlerException ex) {
			System.out.print(ex.getMessage() );
		}
		System.out.println("\n okkey");
	}
}
