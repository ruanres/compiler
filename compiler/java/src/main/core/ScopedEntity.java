package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *	Entity that has a scope.. All variables here holds only to the scope of this entity...
 */
public class ScopedEntity extends NamedEntity {

	private HashMap<String, Variable> variables;
	private HashMap<String, Type> types;
	private List<String> params;
	
	private int checkParam = 0;
	
	public ScopedEntity(String name) {
		super(name);
		variables = new HashMap<String, Variable>();
		params = new ArrayList<String>();
	}

	public Map<String, Variable> getVariable() {
		return variables;
	}
	
	public void addVariable(Variable v) {
		this.variables.put(v.getName(), v);
	}
	
	public void clearVariables() {
		variables = new HashMap<String, Variable>();

	}
	
	public void clearParams() {
		params = new ArrayList<String>();

	}
	
	public void addType(Type t) {
		this.types.put(t.getName(), t);
	}
	
	public Map<String, Type> getTypes() {
		return types;
	}
	
	public List<String> getParams() {
		return this.params;
	}
	
	public void addParam(String param) {
		this.params.add(param);
	}
	//** Needed to check the function call
	public void incrementCheckParam() {
		checkParam += 1;
	}
	
	public int getCheckParam() {
		return checkParam;
	}
	
}
