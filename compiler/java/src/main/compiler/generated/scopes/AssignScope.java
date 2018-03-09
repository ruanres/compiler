package compiler.generated.scopes;
import core.Expression;
import core.ScopedEntity;
import core.Type;
import core.Variable;
import exceptions.AssignScopeException;

public class AssignScope {
	
	private static AssignScope assign;
	
	public AssignScope() {
	
	}
	
	public static AssignScope getInstance() {
		if (assign == null) {
			assign = new AssignScope();
		}
		
		return assign;
	}
	
	public void initVariable(String varName, Expression expression, ScopedEntity scope) {
		
		if (scope.getVariables().get(varName) != null) {
			throw new AssignScopeException("This variable already was declared in this scope");
		} else {
			Type tp = new Type(expression.getType().toString());
			Variable var = new Variable(varName, tp);
			var.setExpression(expression);
			scope.addVariables(var);
		}
		
	}
	
	public void initVariable(String assignedVariable, String assignVariable, ScopedEntity scope) {
		Variable auxVar = scope.getVariables().get(assignVariable);
		
		if (auxVar == null) {
			throw new AssignScopeException("The variable " + assignVariable+ " was not declared");
		}
		
		
		if (auxVar.getExpression() == null) {
			throw new AssignScopeException("The variable " + assignVariable + " was not initialized with an expression");
		} else {
			initVariable(assignedVariable, auxVar.getExpression(), scope);
		}
	}
	
}