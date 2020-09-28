package filterAppTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class Main {
	
	private static ApplicationContext context;

	public static void main(String[] args) throws Exception 
	{
		context = new ClassPathXmlApplicationContext("beans.xml");
		MyRscManager rscMngr = (MyRscManager) context.getBean("MyRscMngr");
		
		//Lis le contenue du fichier en spécifiant le path
		//myRscLoader.getFileContent("Pass file path here");
		
		String[] tofind = {"Aaah", "Eux", "Hache"};
		String file_path ="c:/Users/Iso/Desktop/tofilter.txt";
		rscMngr.copyFilter(file_path, tofind);
		
		
		}

}
