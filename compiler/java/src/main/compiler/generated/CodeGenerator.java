package compiler.generated;

import core.Expression;
import core.Variable;

public class CodeGenerator {
     private String assemblyCode;

	 public CodeGenerator() {
		 this.assemblyCode = getInitAssemblyCode();
				 
	 }
	 
	 public String getInitAssemblyCode() {
		 return "gg";
	 }
	
	 public void assignmentDeclaration(String var, String obj) {
		 System.out.println("Insert here assembly code");
	 }
}
