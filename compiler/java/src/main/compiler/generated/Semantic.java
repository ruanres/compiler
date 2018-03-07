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
		cProgram = new Program();
		ScopedEntity scoped = new ScopedEntity("init");
		scopeStack.push(scoped);
	}
	
	public static CodeGenerator getCodeGenerator() {
        return codeGenerator;
    }
	
	public ScopedEntity getCurrentScope() {
        return scopeStack.peek();
    }
	
	public void checkNewScope(String id) {
		if (cProgram.getFunctions().get(id) != null || id.equals("main")) {
			ScopedEntity scoped = new ScopedEntity(id);
			scopeStack.push(scoped);
		}
		
	}
	
	// Init Function Scope
	public void addFunction(String name, List<String> params) {
		Function auxFunc = cProgram.getFunctions().get(name);

		if ( auxFunc != null) {

			if (params.size() != auxFunc.getParams().size()) {
				throw new SemanticException("Conflict types between " + name + " function. The function has more or less params than the previous declaration");
			} else {
				int index = 0;
				for(String param : auxFunc.getParams()) {
					if (!params.get(index++).equals(param)) {
						throw new SemanticException("Conflict types between " + name + " function");
					}
				}
				
			}
			
			getCodeGenerator().changeFunctionLabels(name);
		} else {
			getCodeGenerator().initFunction(name);
		}
		
		Function func = new Function(name, params);
		getCurrentScope().clearParams();
		cProgram.addFunction(func);
	}
	
	public Function getFunction(Object in) {
		return cProgram.getFunctions().get(in.toString());
	}
	
	public void initParam(Variable var) {
		if (getCurrentScope().getVariable().get(var.getName()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		
		String funcName = getCurrentScope().getName();
		System.out.println(cProgram.getFunctions());
		if (cProgram.getFunctions().get(funcName) != null) {
			if (cProgram.getFunctions().get(funcName).isFunctionInitialized() == false) {
				
			} else {
				List<Expression> expressions = cProgram.getFunctions().get(funcName).getFunctionParamaters();
				int checked = cProgram.getFunctions().get(funcName).getChecked();
				
				
				
				if (checked >= expressions.size()) {
					throw new SemanticException("The function " + funcName +  " called should have less parameters");
				}
				
				var.setExpression(expressions.get(checked));
				cProgram.getFunctions().get(funcName).incrementCheck();
			}
			

		}
		
		getCurrentScope().addParam(var.getType().toString());
		getCurrentScope().addVariable(var);
		
	}
	
	public void setTypeFunction(Type functionType , String funcName) {
		Function auxFunc = cProgram.getFunctions().get(funcName);
		
		if ( auxFunc == null) {
			throw new SemanticException("The function " + funcName + " doesn't exists");
		}
		
		cProgram.getFunctions().get(funcName).setReturnType(functionType);
	}
	
	
	//When we have function(list_parameters)
	public void callFunction(Function func, List<Expression> expressions) {
		String funcName = func.getName();
	
		
		if (cProgram.getFunctions().get(funcName) == null) {
			throw new SemanticException("The function " + funcName + " wasn't declared");
		}
		

		List<String> params = cProgram.getFunctions().get(funcName).getParams();
		
		if (expressions.size() < params.size()) {
			throw new SemanticException("The function " + funcName +  " called should have more parameters");
		} else if (expressions.size() > params.size()) {
			throw new SemanticException("The function " + funcName +  " called should have less parameters");
		}
		
		int index = 0;

		for (String paramType : params) {
			Type param = new Type(paramType);
			Type typeParamFunctionCall = expressions.get(index).getType();

			if (!param.equals(typeParamFunctionCall)) {
				throw new SemanticException("The function " + funcName + " should have this types "
						+ "of params: " + params.toString());
			}
			
			index += 1;
		}
		
		for (Expression exp: expressions) {
			getCodeGenerator().generateLDCode(exp);
		}
		
		getCodeGenerator().generateCodeFunction(funcName);
		cProgram.getFunctions().get(funcName).initializeFunction();
		cProgram.getFunctions().get(funcName).setFunctionParamaters(expressions);
		
		
	}
	
	// End Function Scope

	
	public Variable assignVariable(Variable var, Expression exp) {
		if (getCurrentScope().getVariable().get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		var.setType(exp.getType());
		var.setExpression(exp);
		getCodeGenerator().generateLDCode(exp);
		getCurrentScope().getVariable().put(var.toString(), var);
		
		return getCurrentScope().getVariable().get(var.getName());
	}
	

	public Variable assignVariable(Variable var, Variable exp) {
		Variable auxVar = getCurrentScope().getVariable().get(exp.toString());
		if (auxVar == null) {
			throw new SemanticException("Variable " + auxVar.getName() + " doesn't exist");
		}
		
		if (auxVar.getExpression() == null) {
			throw new SemanticException("Variable " + auxVar.getName() + " was not declared");
		}
		
		return assignVariable(var, auxVar.getExpression());
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
		if (currentScopeVar.getExpression() != null) {
			getCodeGenerator().generateSTCode(currentScopeVar);
		}
		getCurrentScope().getVariable().put(var.toString(), var);

	}
	
	//** When "variable" = "value"
	public void checkAssignVariableIsValid(Variable var, Expression exp) {
		
		Variable currentScopeVariable = (Variable) getIdentifier(var.getName()); 
		
		if (!currentScopeVariable.getType().equalsAssignRelational(exp.getType())) {
			
			if (currentScopeVariable.getType().getName() == "char") {
				throw new SemanticException("ERRO: Literal string é imutavel");
			}
			throw new SemanticException("Nao é possivel a variavel do tipo " + currentScopeVariable.getName() + " ser associado com um valor/variavel do tipo " +  exp.getType());
		}
		
		getCodeGenerator().generateLDCode(exp);
		getCodeGenerator().generateSTCode(var);
		getCurrentScope().getVariable().get(var.getName()).setExpression(exp);
		
	}
	
	//** When "variable" = "variable"
	public void checkAssignVariableIsValid(Variable var, Variable var_exp) {
		Variable currentScopeVar = getCurrentScope().getVariable().get(var_exp.getName());
		checkIfExistExpression(currentScopeVar);
		checkAssignVariableIsValid(var, currentScopeVar.getExpression());
	}
	

	public void createVariableWithoutExpression (Variable var) {
		if (getCurrentScope().getVariable().get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		getCurrentScope().getVariable().put(var.toString(), var);

	}
	
	// when type "a" = function(list_parameters);
	public Variable assignFunction(Variable var, Function func) {
		if (getCurrentScope().getVariable().get(var.toString()) != null) {
			throw new SemanticException("Variable " + var.getName() + " already exists");
		}
		
		// To distinguish between int a = k(not declared variable) and int a = function(list_parameters);
		if (cProgram.getFunctions().get(func.getName()) == null ) {
			throw new SemanticException("Variable " + func.getName() + " is not declared");
		}
		
		//Check the assign between the variable and the function type
		getCurrentScope().getVariable().put(var.toString(), var);
		return getCurrentScope().getVariable().get(var.getName());
	}
	
	public Variable getIdentifier(String name) {
		if (getCurrentScope().getVariable().get(name) == null) {
			throw new SemanticException("Identifier name doesn't exists: " + name);
		}
		

		return getCurrentScope().getVariable().get(name);
	}
		
	
	public boolean isRelationalExpression(String operation, Expression le, Expression re) throws SemanticException {
		if (le == null) {
			  throw new SemanticException("The left variable of declaration was not declared");
		} else if (re== null ) {
			  throw new SemanticException("The right variable of declaration was not declared");

		}
		
		if(!le.getType().equalsAssignRelational(re.getType())){
            throw new SemanticException("ERRO: Was not possible to compare an expression with the type " + 
		le.getType().getName() + " with an expression with the type " + re.getType().getName());
        }
		
		getCodeGenerator().generateSUBCode();

		switch (operation) {
			case ">":
				
				getCodeGenerator().generateBLETZ();
				break;
			case "<":
				
				getCodeGenerator().generateBGETZ();
				break;
			case "<=":
				
				getCodeGenerator().generateBGTZ();
				break;
			case ">=":
				
				getCodeGenerator().generateBLTZ();
				break;
			case "==":
				getCodeGenerator().generateBNE();
				break;
			case "!=":
				getCodeGenerator().generateBEQ();
				break;


		}

		return true;
    }

    private void checkTypeCompatibility(Expression leftExp, Expression rightExp) throws SemanticException{
    	
    	boolean leftIsChar = leftExp.getType().toString().equals("char");
    	boolean rightIsChar =  rightExp.getType().toString().equals("char");

        if(leftIsChar || rightIsChar)
            throw new SemanticException("Illegal Operation between " +
            		leftExp.toString() + " and " + rightExp.toString());
    }
    
    

    public Expression getExpressionForOperation(Operation op, Expression e1, Expression e2) {
        checkTypeCompatibility(e1, e2);
        
        Type e1Type = e1.getType();
        Type e2Type = e2.getType();
        
        
        Type minorType = getMinorType(e1Type, e2Type);

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
    
    public Expression getExpressionForOperation(Operation op, Variable v1, Expression e2) {

    	Variable var = getCurrentScope().getVariable().get(v1.getName());
    	checkIfExistExpression(var);
    	return getExpressionForOperation(op, var.getExpression(), e2);
    }
    
    public Expression getExpressionForOperation(Operation op, Expression e1, Variable v2) {
    	Variable var = getCurrentScope().getVariable().get(v2.getName());
    	checkIfExistExpression(var);
    	return getExpressionForOperation(op, e1, var.getExpression());
    }
    
    public Expression getExpressionForOperation(Operation op, Variable v1, Variable v2) {
    	Variable var1 = getCurrentScope().getVariable().get(v1.getName());
    	Variable var2 = getCurrentScope().getVariable().get(v2.getName());

    	checkIfExistExpression(var1);
    	checkIfExistExpression(var2);
    	return getExpressionForOperation(op, var1.getExpression(), var2.getExpression());
    }
    
    private void checkIfExistExpression(Variable temp) {
    	if (getCurrentScope().getVariable().get(temp.getName()) == null) {
			throw new SemanticException("Variable " + temp.getName() + " doesn't exists");
    	}
    	
    	Variable t = getCurrentScope().getVariable().get(temp.getName());
    	if(!t.existExpression()) {
			throw new SemanticException("Variable " + temp.getName() + " doesn't have an expression associated"); 
    	}
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

	public boolean isFunction(Object in) {
		if (cProgram.getFunctions().get(in.toString()) != null) {
			return true;
		}
		return false;
	}

	public void isRelationalExpression(String operation, Object e1, Object e2) {
		Expression exp1;
		Expression exp2;
		
		if (e1 instanceof Variable) {
			Variable v1 = getCurrentScope().getVariable().get(e1.toString());
			getCodeGenerator().generateLDCode(v1);
			exp1 = v1.getExpression();
			
		} else {
			exp1 = (Expression) e1;
			getCodeGenerator().generateLDCode(exp1);

		}
		
		if (e2 instanceof Variable) {
			Variable v2 = getCurrentScope().getVariable().get(e2.toString());
			getCodeGenerator().generateLDCode(v2);
			exp2 = v2.getExpression();
		} else {
			exp2 = (Expression) e2;
			getCodeGenerator().generateLDCode(exp2);

		}
		
		isRelationalExpression(operation, exp1, exp2);
	}

	public void checkReturnType(Type type, String funcName, Object compound) {
		// TODO Auto-generated method stub
		Type returnType;
		
		if (compound instanceof Variable) {
			Variable auxVar = getCurrentScope().getVariable().get(compound.toString());
			addReturn(funcName, auxVar.getExpression());
			returnType = auxVar.getExpression().getType();
		} else {
			addReturn(funcName,(Expression) compound);
			returnType = ((Expression) compound).getType();
		}
		
		if (!type.equals(returnType)) {
			throw new SemanticException("Error: The return type should be compatible with: " + type.toString());
		}
		
	
		
		getCodeGenerator().generateReturn(funcName);
	}
	
	private void addReturn(String funcName, Expression exp) {
		if (funcName != "main") {
			getCodeGenerator().generateLDCode(exp);
		}
	}
	
	
}
