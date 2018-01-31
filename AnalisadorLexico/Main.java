package Compilador;

import java.io.File;


public class Main {
  private static final String FILENAME = "teste.txt";

  public static void main(String[] args){
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;
      String fileInfo = ""

			while ((sCurrentLine = br.readLine()) != null) {
        fileInfo += sCurrentLine + '\n'
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

    generateResult(fileInfo);
  }

  public static void generateResult(String fileInfo) {
    Lexer lexer = new Lexer(new StringReader(fileInfo));

    while(true){
      Token token = lexer.yylex();
      if(token == null){
        System.out.println("Erro");
        return;
      } else {
        String resultado = "<Palavra_Reservada> " + lexer.lexeme + "\n";
        System.out.println(resultado)
      }
    }
  }

}
