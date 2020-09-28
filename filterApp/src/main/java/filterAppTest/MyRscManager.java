package filterAppTest;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MyRscManager implements ResourceLoaderAware  {
	
	private ResourceLoader resourceLoader;
	

	public void setResourceLoader(ResourceLoader rscLoader) {
		this.resourceLoader = rscLoader;
	}
	
	//Fonction prenant en paramètre le chemin absolue d'un fichier et retourne son contenue
	public void getFileContent(String fichier) throws IOException
	{
		if(fichier != null || fichier != "") {
			
			//Pour les fichiers présent dans le dossier Ressources
			//Resource myRsc = ((org.springframework.core.io.ResourceLoader) resourceLoader).getResource("classpath:"+fichier);
			
			//Pour les fichiers avec chemin absolu - On instancie l'objet Resource avec le chemin du fichier
			Resource myRsc = ((org.springframework.core.io.ResourceLoader) resourceLoader).getResource("file:"+fichier);
			
			//Définition d'un InputStream et d'un reader
			InputStream iStream = myRsc.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
			
			while(true) {
				//Lecture des lignes
				String line = reader.readLine();
				if(line == null || line == "") break;
				System.out.println(line);
			}
			reader.close();
		}
	}
	
	//Fonction prenant en paramètres le chemin absolue d'un fichier et un terme à retrouver dans le fichier
	public void copyFilter(String fichier, String[] filter) throws IOException
	{
		
		Resource myRsc = ((org.springframework.core.io.ResourceLoader) resourceLoader).getResource("file:"+fichier);
		InputStream iStream = myRsc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
		
		while(true) {
						
			String line = reader.readLine();
			if(line == null || line == "") break;
			
			try {
				//Création du fichier en sortie
				File myFile = new File("filtered.txt");
				
				//On boucle sur toute les valeur de la liste de String filter
				for(String filters: filter) {
					if(line.contentEquals(filters)) {
						
						//Instanciation du FileWriter et du Buffer 
						FileWriter myWriter = new FileWriter(myFile);
						BufferedWriter bw = new BufferedWriter(myWriter);
						myWriter.append(filters);
						bw.newLine();
						System.out.println("Fichier crée à cet emplacement :"+myFile.getAbsolutePath());
						bw.close();
						myWriter.close();
						
						
					}
					
				}
				
			} catch(IOException e) {
				System.out.println("Une erreur s'est produite.");
			    e.printStackTrace();
			}		
		}
		
		reader.close();
	}


}
