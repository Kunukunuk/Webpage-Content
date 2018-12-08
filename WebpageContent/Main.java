
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;


import javax.swing.JFileChooser;


public class Main {
	
	private static BufferedReader fr;
	private static PrintWriter pw;
	static File inname;
	static String imgName, fileName;
	static int imgNum = 0, fileNum = 0;
	public static void main(String[] args) throws Exception{
		//String name;
		if(args.length < 2){
			inname = file();
			pw = new PrintWriter(file());
		}
		else if(args.length == 1){
			inname = new File(args[0]);
			pw = new PrintWriter(file());
		}
		else{
			inname = new File(args[0]);
			pw = new PrintWriter(args[1]);
		}
		
		fr = new BufferedReader(new FileReader(inname));
		String name = fr.readLine();
		while(name != null){
			String last4, last5;
			last4 = name.substring(name.length()-4);
			last5 = name.substring(name.length()-5);
			GetURLinfo get = new GetURLinfo();
			get.main(name, pw);
			if(last5.equals(".html") || last4.equals(".htm") || last4.equals(".txt") || last4.equals(".pdf")){
				if(last5.equals(".html")){
					fileName = "Savedhtmlurl" + fileNum + ".html";
				}
				else if(last4.equals(".htm")){
					fileName = "Savedhtmurl" + fileNum + ".htm";
				}
				else if(last4.equals(".txt")){
					fileName = "Savedtxturl" + fileNum + ".txt";
				}
				else if(last4.equals(".pdf")){
					fileName = "Savedpdfurl" + fileNum + ".pdf";
				}
				File newfile = new File(fileName);
				newfile.createNewFile();
				fileNum++;
				WebpageWA web = new WebpageWA();
				web.main(name, newfile, pw, last4);
				pw.println("SavedFile Name is " + fileName);
			}
			else if (last5.equals(".jpeg")|| last4.equals(".jpg") || last4.equals(".gif")){
				if(last5.equals(".jpeg")){
					imgName = "SavedImage" + imgNum + ".jpeg"; 
				}else if (last4.equals(".jpg")){
					imgName = "SavedImage" + imgNum + ".jpg"; 
				}else{
					imgName = "SavedGif" + imgNum + ".gif";
				}
				imgNum++;
				GetURLImage img = new GetURLImage();
				img.main(name, last4, last5, imgName);
				pw.println("Saved Image File Name is "+imgName);
			}
			name = fr.readLine();
		}
		fr.close();
		pw.close();
	}
	
	public static File file(){
    	JFileChooser fc = new JFileChooser();
    	int result = fc.showOpenDialog(null);
    	
    	if(result == JFileChooser.APPROVE_OPTION){
    		String ss = fc.getSelectedFile().getAbsolutePath();
    		File file = fc.getSelectedFile();
    		return file;
    	}
    	else{
    		return null;
    	}
    }
}
