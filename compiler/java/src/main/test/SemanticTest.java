package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import compiler.generated.Parser;
import compiler.generated.Scanner;
import exceptions.ArithmeticOperationTypeError;
import exceptions.AssignTypeErrorException;
import exceptions.ExistingFunctionException;
import exceptions.ExistingIdentifierException;
import exceptions.ExistingVariableException;
import exceptions.FunctionNotDeclaratedException;
import exceptions.IllegalParametersException;
import exceptions.RelationOperationTypeError;
import exceptions.StringImutableException;

public class SemanticTest {
	
	public Scanner scanner;
	public Parser parser;
	
	@Before
	public void setUp() {
		scanner = null;
		parser = null;
	}
	
	private Scanner montaScanner(String nomeDoArquivo) {
		Scanner scanner = null;
		String fileName = nomeDoArquivo;
	    File input = new File("java/src/main/test/" + fileName + ".c");
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
	    
	    return scanner;
		
	}
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = ExistingVariableException.class)
//	public void testExistingVariable() throws Exception {
//		scanner = montaScanner("code2");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = FunctionNotDeclaratedException.class)
//	public void testFunctionErrorDeclaration() throws Exception {
//		scanner = montaScanner("code1");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = ExistingFunctionException.class)
//	public void testFunctionExisting() throws Exception {
//		scanner = montaScanner("code18");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = IllegalParametersException.class)
//	public void testIlegalParameters() throws Exception {
//		scanner = montaScanner("code19");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = IllegalParametersException.class)
//	public void testIlegalParameters2() throws Exception {
//		scanner = montaScanner("code20");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = IllegalParametersException.class)
//	public void testIlegalParameters3() throws Exception {
//		scanner = montaScanner("code21");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void assignErrorWithFunction() throws Exception {
//		scanner = montaScanner("code22");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void AssignTypeError() throws Exception {
//		scanner = montaScanner("code3");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void AssignTypeError2() throws Exception {
//		scanner = montaScanner("code4");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = StringImutableException.class)
//	public void AssignTypeError3() throws Exception {
//		scanner = montaScanner("code5");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void AssignTypeError4() throws Exception {
//		scanner = montaScanner("code6");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void AssignTypeError5() throws Exception {
//		scanner = montaScanner("code7");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void AssignTypeError6() throws Exception {
//		scanner = montaScanner("code7");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = AssignTypeErrorException.class)
//	public void AssignTypeError7() throws Exception {
//		scanner = montaScanner("code8");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = ExistingVariableException.class)
//	public void testExistingVariable() throws Exception {
//		scanner = montaScanner("code9");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = ExistingIdentifierException.class)
//	public void testExistingIdentifier() throws Exception {
//		scanner = montaScanner("code10");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = RelationOperationTypeError.class)
//	public void testRelationalOperationTypeError() throws Exception {
//		scanner = montaScanner("code11");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = RelationOperationTypeError.class)
//	public void testRelationalOperationTypeError2() throws Exception {
//		scanner = montaScanner("code12");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = RelationOperationTypeError.class)
//	public void testRelationalOperationTypeError3() throws Exception {
//		scanner = montaScanner("code13");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = RelationOperationTypeError.class)
//	public void testRelationalOperationTypeError4() throws Exception {
//		scanner = montaScanner("code14");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = ArithmeticOperationTypeError.class)
//	public void testArithmetic() throws Exception {
//		scanner = montaScanner("code15");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
//	@SuppressWarnings("deprecation")
//	@Test(expected = ArithmeticOperationTypeError.class)
//	public void testArithmetic2() throws Exception {
//		scanner = montaScanner("code17");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
//	// Nao deve gerar erro, testa todas as expressoes relacionais e aritmeticas possiveis
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testArithmeticAndRelationalWorking() throws Exception {
//		scanner = montaScanner("code16");
//		parser = new Parser((java_cup.runtime.Scanner) scanner);
//		parser.parse();
//		
//	}
	
	
	@SuppressWarnings("deprecation")
	@Test(expected = AssignTypeErrorException.class)
	public void testeAssignErrorWithArithmeticExpression() throws Exception {
		scanner = montaScanner("code23");
		parser = new Parser((java_cup.runtime.Scanner) scanner);
		parser.parse();
		
	}
	

	

	

	
	
	

}
