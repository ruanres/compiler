package compiler.generated;

import core.Expression;
import core.Register;
import core.Variable;

public class CodeGenerator {
     private String assemblyCode;
     private int labels;
     private Register[] registers;
 	 private int register;

	 public CodeGenerator() {
		 this.assemblyCode = getInitAssemblyCode();
		 this.registers = Register.values();
	     labels = 10;
	     register = -1;
	 }
	 
	 public String getInitAssemblyCode() {
		 return "100: LD SP, #4000\n";
	 }
	
	 public void assignmentDeclaration(Variable var, Object obj) {
	   // No futuro fazer para função	 
	   generateSTCode(var);
	 }

	 public void generateSTCode(Variable variable) {
	    labels += 4;
	    addCode(labels + ": ST " + variable.getName() + ", " + allocateRegister());
	    this.register = -1;
	 }
	 
	 public void generateLDCode(Expression expression) {
		if (expression.getAssemblyValue() != null) {
			register++;
			labels += 4;
			addCode(labels + ": LD " + allocateRegister() + ", " + expression.getAssemblyValue());
		}
	 }
	 
	 public void addCode(String newCode) {
		this.assemblyCode += newCode + '\n'; 
	 }
	 
	 public Register allocateRegister(){
		try {
			Register allocated = registers[register]; 
			return allocated;
		} catch (Exception e) {
			register++; 
			return allocateRegister();
		}
	}
}
