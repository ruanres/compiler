package lexico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;


public class Main {
  private static final String FILENAME = "teste.txt";

  public static void main(String[] args) throws IOException{
    String fileInfo = "";

	try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

	  String sCurrentLine;

	  while ((sCurrentLine = br.readLine()) != null) {
        fileInfo += sCurrentLine + '\n';
	  }

	} catch (IOException e) {
		e.printStackTrace();
	}

    generateResult(fileInfo);
  }

  public static void generateResult(String fileInfo) throws IOException {
    Lexer lexer = new Lexer(new StringReader(fileInfo));

    while(true){
      Token token = lexer.yylex();
      if(token == null){
        System.out.println("Erro");
        return;
      } else {
        String resultado = "<Palavra_Reservada> " + lexer.lexeme + "\n";
        System.out.println(resultado);
      }
    }
  }

}
