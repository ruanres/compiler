package compiler.generated;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
     private HashMap<String, Integer> functions;
     private int labels;
     private Register[] registers;
 	 private int register;
 	 private Integer relationalLabel;
 	 private int currentFunctionLocalization;
 	 private Integer branch;
	 public CodeGenerator() {
		 this.assemblyCode = new HashMap<Integer, String>();
		 this.functions = new HashMap<String, Integer>(); 
		 this.currentFunctionLocalization = 1200;
		 this.registers = Register.values();
	     labels = 100;
	     register = -1;
		 initAssemblyCode();
	 }
	 
	 public void initAssemblyCode() {
		 assemblyCode.put(labels, "100: LD SP, #4000\n");
	 }
	
	 public void assignmentDeclaration(Variable var) { 
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
    	boolean check = false;
    	if ( relationalLabel != null) {
    		String labelString = assemblyCode.get(relationalLabel);
    		Integer labelIndex = labelString.indexOf("#");
    		String newString = "";
    	
    		if (labelIndex != -1) {
    			newString = labelString.substring(0 ,labelIndex);
    	    	addCode(labels + ": ;;;BR #100");
    			newString += "#" + (labels + 4);
    			branch = labels;
 

    			check = true;
    		} else {
    			System.out.println("fsdjçfdsçjfsd");
    			newString = "#" + (labels + 4);
            	newString = labelString.replace("#",newString);
    		}
    		
    		
    		
        	assemblyCode.put(relationalLabel, newString);
        	
        	if (check) {
        		relationalLabel = null;
        	}
    	}
    	
    }
    
    public void updateIfRelation() {
    	System.out.println(branch);
    	if (branch != null) {
    		String labelString = assemblyCode.get(branch);
    		Integer labelIndex = labelString.indexOf(":");
			String newString = labelString.substring(0 ,labelIndex+1);
    		newString += " #BR " + (labels + 4);
        	assemblyCode.put(branch, newString);
    	}
	


    }
    
    public void generateBNE() {
    	labels += 4;
        Register result = allocateRegister();
        relationalLabel = labels;
    	addCode(labels + ": BNE " + result + " , 0 , "+ " #");
    }
 
    public void generateCodeFunction(String funcName) {
    	labels += 4; 
    	addCode(labels + ": SP, SP, #msize");
    	labels += 4; 
    	int auxLabels = labels + 8;
    	addCode(labels + ": ST, *SP, " + " #" + auxLabels);
    	labels += 4; 
    	
    	int functionLabel = functions.get(funcName);
    	addCode(labels + ": BR #" + functionLabel);
    	labels += 4;
    	addCode(labels + ": SUB, SP, SP, #msize");

    } 
    
    public void changeFunctionLabels(String funcName) {
    	int functionLabel = functions.get(funcName);
    	labels = functionLabel - 4;
    }
    
    public void initFunction(String funcName) {
    	functions.put(funcName, currentFunctionLocalization);
    	currentFunctionLocalization += 1000;
    }
    
    public void generateReturn(String funcName) {
    	labels += 4;
    	if (funcName.equals("main")) {
        	addCode(labels + ": halt");
    	} else {
        	addCode(labels + ": BR *0(SP)");
    	}
    }

	 @Override
	 public String toString() {
		
		String assCode = "";
		List<Integer> arrayLabels = new ArrayList<Integer>();
		arrayLabels.addAll(assemblyCode.keySet());
		Collections.sort(arrayLabels);
		
		for (Integer var : arrayLabels) {
			assCode += (assemblyCode.get(var) + System.lineSeparator());
		}
		return assCode.toString();
	 }
	 
	 public void printToFile() {
		
		 try {
			 PrintWriter out = new PrintWriter("output/result.txt");
			 out.println(this.toString());
			 out.close();
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
	
	 }
}
