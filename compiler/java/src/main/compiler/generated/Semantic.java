package compiler.generated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import core.*;
import util.SemanticException;

public class Semantic {
	public static Parser parser;

	private static Semantic sAnalysis;
    private Stack<ScopedEntity> scopeStack = new Stack<ScopedEntity>();
    private Program cProgram;
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
		ScopedEntity scoped = new ScopedEntity("main");
		scopeStack.push(scoped);
		cProgram = new Program();
		cProgram.addFunction(new Function("main", null));
	}
	
	public static CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
	
	public ScopedEntity getCurrentScope() {
        return scopeStack.peek();
    }
	
	public void assignVariable(Variable var, Expression exp) {
		if (getCurrentScope().getVariable().get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		var.setType(exp.getType());
		getCodeGenerator().generateLDCode(exp);
		getCurrentScope().getVariable().put(var.toString(), var);
	}
	
	//** When "type" "variable" = "value"/"variable"
	public void checkAssignVariableIsValid(Type variableType, Variable var) {
		Variable currentScopeVar = getCurrentScope().getVariable().get(var.getName()); 
		
		if (var.getType().toString() == null) {
			currentScopeVar.setType(variableType);
		}
		
		Type expressionAssignedType = currentScopeVar.getType();
		
		
		//Inicialmente a variavel � associada com o o mesmo tipo da express�o que foi dado assign. S� aqui � verificado se os tipos batem		
		if (!variableType.equals(expressionAssignedType)) {
			throw new SemanticException("Nao é possivel a variavel do tipo " + variableType.getName() + " ser associado com um valor/variavel do tipo " +  expressionAssignedType);
		}
		
		currentScopeVar.setType(variableType);
		getCodeGenerator().generateSTCode(currentScopeVar);
		getCurrentScope().getVariable().put(var.toString(), var);
	}
	
	//** When "variable" = "value"/"variable"
	public void checkAssignVariableIsValid(Variable var, Expression exp) {
		Variable currentScopeVariable = (Variable) getIdentifier(var.getName()); 
		
		if (!currentScopeVariable.getType().equalsAssignRelational(exp.getType())) {
			
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
	
	public void createVariableWithoutExpression (Variable var) {
		if (getCurrentScope().getVariable().get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		getCurrentScope().getVariable().put(var.toString(), var);

	}
	
	// when type "a" = function(list_parameters);
	public void assignFunction(Variable var, Function func) {
		if (getCurrentScope().getVariable().get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		// To distinguish between int a = k(not declared variable) and int a = function(list_parameters);
		if (cProgram.getFunctions().get(func.getName()) == null ) {
			throw new SemanticException("Variable " + func.getName() + " is not declared");
		}
		
		//Check the assign between the variable and the function type
		getCurrentScope().getVariable().put(var.toString(), var);
	}
	
	public Variable getIdentifier(String name) {
		if (getCurrentScope().getVariable().get(name) == null) {
			throw new SemanticException("Identifier name doesn't exists: " + name);
		}
		

		return getCurrentScope().getVariable().get(name);
	}
	
	public Function possibleFunction(Function func) {
		if (getCurrentScope().getVariable().get(func.getName()) != null) {
			throw new SemanticException("Variable " + func + " already exists");		
		}
		
		return func;
	}
	
	public void callFunction(Function func, List<Expression> expressions) {
		cProgram.addFunction(func);
		
		for (Expression exp : expressions) {
			cProgram.getFunctions().get(func.getName()).addParameter(exp);
		}
	}
	
	public void checkNewScope(String id) {
		if (cProgram.getFunctions().get(id) != null) {
			ScopedEntity scoped = new ScopedEntity(id);
			scopeStack.push(scoped);
		}
	}

	public boolean isRelationalExpression(Expression le, Expression re) throws SemanticException {
		if(!le.getType().equalsAssignRelational(re.getType())){
            throw new SemanticException("ERRO: Nao é possivel comparar uma expressao do tipo " + 
		le.getType().getName() + " com uma expressao do tipo " + re.getType().getName());
        }

		return true;
    }

    private void checkTypeCompatibility(Expression leftExp, Expression rightExp) throws SemanticException{
        boolean leftIsChar = leftExp.getType().toString().equals("char");
        boolean rightIsChar = rightExp.getType().toString().equals("char");

        if(leftIsChar || rightIsChar)
            throw new SemanticException("Illegal Operation between " +
                    leftExp.getType().toString() + " and " + rightExp.getType().toString());
    }

    public Expression getExpressionForOperation(Operation op, Expression e1, Expression e2) {
        checkTypeCompatibility(e1, e2);
        Type minorType = getMinorType(e1.getType(), e2.getType());

        getCodeGenerator().generateLDCode(e1);
        getCodeGenerator().generateLDCode(e2);

        switch (op) {
            case PLUS:
                getCodeGenerator().generateADDCode();
                break;
            case MINUS:
                getCodeGenerator().generateSUBCode();
                break;
            case MULT:
                getCodeGenerator().generateMULCode();
                break;
            case DIV:
                getCodeGenerator().generateDIVCode();
        }
        return new Expression(minorType);
    }

    private Type getMinorType(Type type1, Type type2) {
        if(type1.equals(type2)) {
            return type1;
        } else if(type1.getName().equals("int") || type2.getName().equals("int")) {
            return new Type("int");
        } else {
            return new Type("float");
        }
    }
}
