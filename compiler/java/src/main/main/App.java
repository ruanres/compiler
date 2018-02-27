package main;

import compiler.generated.Parser;
import compiler.generated.Scanner;
import java_cup.runtime.Symbol;


import java.io.*;


public class App {
    public static Scanner scanner = null;

    public static void main(String[] args) throws Exception {
        File input = new File("inputs/code.c");
        String filePath = input.getAbsolutePath();
        String sCurrentLine;
        String fileInfo = "";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        	while ((sCurrentLine = br.readLine()) != null) {
        		fileInfo += sCurrentLine + '\n';
    	    }
            
        	scanner = new Scanner(new StringReader(fileInfo));
        } catch (IOException e) {
    		e.printStackTrace();
    	}
        
        //LexicalAnalyser(scanner);
        SyntacticAnalyser(scanner);    
    } 
    
    private static void LexicalAnalyser(Scanner scanner) throws IOException {
    	while (true){
          Symbol token = scanner.next_token();

          if (token.sym == 0){
              System.out.println("Fim do Arquivo");
        	  break;
          }
             
          System.out.println(token.toString());
    	}
    }
    
    private static void SyntacticAnalyser (Scanner scanner) throws Exception {
    	try {
    		Parser parser = new Parser((java_cup.runtime.Scanner) scanner);
    		parser.parse();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    }
    

}
