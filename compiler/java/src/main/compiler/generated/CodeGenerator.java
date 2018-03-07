package compiler.generated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import core.Expression;
import core.Register;
import core.Variable;

public class CodeGenerator {
     private HashMap<Integer, String> assemblyCode;
     private int labels;
     private Register[] registers;
 	 private int register;
 	 private Integer relationalLabel;
 	 
	 public CodeGenerator() {
		 this.assemblyCode = new HashMap<Integer, String>(); 
		 this.registers = Register.values();
	     labels = 100;
	     register = -1;
		 initAssemblyCode();
	 }
	 
	 public void initAssemblyCode() {
		 assemblyCode.put(labels, "100: LD SP, #4000\n");
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
	 
	 public void generateLDCode(Variable variable) {
		register++;
		labels += 4;
		addCode(labels + ": LD " + allocateRegister() + ", " + variable.getName());
     }
	 
	 public void addCode(String newCode) {
		String assemblyStringCode = newCode + '\n'; 
		this.assemblyCode.put(labels, assemblyStringCode);
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
    
    public void generateBGTZ() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;
    	addCode(labels + ": BGTZ " + result + " , "+ " #");
    }
    
    public void generateBLTZ() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;
    	addCode(labels + ": BLTZ " + result + " , "+ " #");
    }
    
    public void generateBLETZ() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;

    	addCode(labels + ": BLETZ " + result + " , "+ " #");
    }
    
    public void generateBGETZ() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;
    	addCode(labels + ": BGETZ " + result + " , "+ " #");
    }
    
    public void generateBEQ() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;
    	addCode(labels + ": BEQ " + result + " , 0 , "+ " #");
    }
    
    public void updateRelation() {
    	if ( relationalLabel != null) {
    		String labelString = assemblyCode.get(relationalLabel);
        	String newString = "#" + (labels + 4);
        	newString = labelString.replace("#",newString);
        	assemblyCode.put(relationalLabel, newString);
        	relationalLabel = null;
    	}
    	
    }
    
    public void generateBNE() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;
    	addCode(labels + ": BNE " + result + " , 0 , "+ " #");
    }
 


	 @Override
	 public String toString() {
		String assCode = "";
		List<Integer> arrayLabels = new ArrayList<Integer>();
		arrayLabels.addAll(assemblyCode.keySet());
		Collections.sort(arrayLabels);
		
		for (Integer var : arrayLabels) {
			assCode += assemblyCode.get(var);
		}
		
		return assCode;
	 }
}
