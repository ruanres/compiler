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
	     labels = 100;
	     register = -1;
	 }
	 
	 public String getInitAssemblyCode() {
		 return "100: LD SP, #4000\n";
	 }
	
	 public void assignmentDeclaration(Variable var) {
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

    public void generateADDCode() {
        labels += 4;
        Register one = registers[register - 1];
        Register two = allocateRegister();

        register++;
        Register result = allocateRegister();
        addCode(labels + ": ADD " + result + ", " + one + ", " + two);
    }

    public void generateADDCode(String cons) {
        labels += 4;
        Register one = registers[register];
        register++;
        Register result = allocateRegister();
        addCode(labels + ": ADD " + result + ", " + one + ", #" + cons);
    }

    public void generateADDCode(Register result, Register one, String cons) {
        labels += 4;
        addCode(labels + ": ADD " + result + ", " + one + ", " + cons);
    }

    public void generateADDCode(Register result, Register one, Expression exp) {
        labels += 4;
        addCode(labels + ": ADD " + result + ", " + one + ", #" + exp.getAssemblyValue());
    }

    public void generateSUBCode() {
        labels += 4;

        Register one = registers[register - 1];
        Register two = allocateRegister();

        register++;
        Register result = allocateRegister();
        addCode(labels + ": SUB " + result + ", " + one + ", " + two);
    }

    public void generateSUBCode(Register result, Register one, Expression exp) {
        labels += 4;
        addCode(labels + ": SUB " + result + ", " + one + ", #" + exp.getAssemblyValue());
    }

    public void generateMULCode() {
        labels += 4;

        Register one = registers[register - 1];
        Register two = allocateRegister();

        register++;
        Register result = allocateRegister();
        addCode(labels + ": MUL " + result + ", " + one + ", " + two);
    }

    public void generateMULCode(Register result, Register one, Expression exp) {
        labels += 4;
        addCode(labels + ": MUL " + result + ", " + one + ", #" + exp.getValue());
    }

    public void generateDIVCode() {
        labels += 4;

        Register one = registers[register - 1];
        Register two = allocateRegister();

        register++;
        Register result = allocateRegister();
        addCode(labels + ": DIV " + result + ", " + one + ", " + two);
    }


	 @Override
	 public String toString() {
		 System.out.println(assemblyCode);
		 return assemblyCode;
	 }
}
