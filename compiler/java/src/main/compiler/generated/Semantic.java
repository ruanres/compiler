package compiler.generated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import core.*;
import util.SemanticException;

public class Semantic {
	public static Parser parser;

	private static Semantic sAnalysis;
    private Stack<ScopedEntity> scopeStack = new Stack<ScopedEntity>();
    private Program cProgram;
    private HashMap<String,Variable> variables = new HashMap<String,Variable>();
    public static CodeGenerator codeGenerator;

	public static Semantic getInstance() {
		if (sAnalysis == null)
			sAnalysis = new Semantic();
		if (codeGenerator == null) {
			codeGenerator = new CodeGenerator();
		}
		return sAnalysis;
	}
	
	public Semantic() {
		cProgram = new Program();
	}
	
	public static CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
	
	public ScopedEntity getCurrentScope() {
        return scopeStack.peek();
    }
	
	public void assignVariable(Variable var, Expression exp) {
		if (variables.get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		var.setType(exp.getType());
		getCodeGenerator().generateLDCode(exp);
		variables.put(var.toString(), var);
	}
	
	//** When "type" "variable" = "value"/"variable"
	public void checkAssignVariableIsValid(Type variableType, Variable var) {
		Variable currentScopeVar = variables.get(var.getName()); 
		Type expressionAssignedType = currentScopeVar.getType();
		
		
		//Inicialmente a variavel � associada com o o mesmo tipo da express�o que foi dado assign. S� aqui � verificado se os tipos batem		
		if (!variableType.equals(expressionAssignedType)) {
			throw new SemanticException("N�o � possivel a variavel do tipo " + variableType.getName() + " ser associado com um valor/variavel do tipo " +  expressionAssignedType);
		}
		
		currentScopeVar.setType(variableType);
		getCodeGenerator().generateSTCode(currentScopeVar);
		variables.put(var.toString(), var);
	}
	
	//** When "variable" = "value"/"variable"
	public void checkAssignVariableIsValid(Variable var, Expression exp) {
		Variable currentScopeVariable = getIdentifier(var.getName()); 
		
		if (!currentScopeVariable.getType().equalsAssign(exp.getType())) {
			
			if (currentScopeVariable.getType().getName() == "char") {
				throw new SemanticException("ERRO: Literal string é imutavel");
			}
			throw new SemanticException("Nao é possivel a variavel do tipo " + currentScopeVariable.getName() + " ser associado com um valor/variavel do tipo " +  exp.getType());
		}
		
		if (exp instanceof Variable) {
			//Se for uma variavel, j� h� um registrador com um valor da variavel guardado
			getCodeGenerator().generateSTCode(currentScopeVariable);
		} else if (exp instanceof Expression) {
			//Se for uma express�o, precisa-se dar um LD na express�o para depois dar um ST
			getCodeGenerator().generateLDCode(exp);
			getCodeGenerator().generateSTCode(var);

		}
		
	}
	
	public Variable getIdentifier(String name) {
		if (variables.get(name) == null) {
			throw new SemanticException("Identifier name doesn't exists: " + name);
		}
		
		return variables.get(name);
	}
	

	public boolean isRelationalExpression(Expression le, Expression re) throws SemanticException {
		if(!le.getType().equalsRelationExpression(re.getType())){
            throw new SemanticException("ERRO: Nao é possivel comparar uma expressao do tipo " + 
		le.getType().getName() + " com uma expressao do tipo " + re.getType().getName());
        }

		return true;
    }

}
