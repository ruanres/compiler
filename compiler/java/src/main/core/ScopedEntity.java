package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *	Entity that has a scope.. All variables here holds only to the scope of this entity...
 */
public class ScopedEntity{

	private HashMap<String, Variable> variables;
	private HashMap<String, Type> types;
	private List<String> params;
	private String name;
	
	public ScopedEntity(String name) {
		this.name = name;
		variables = new HashMap<String, Variable>();
		params = new ArrayList<String>();
	}

	public HashMap<String, Variable> getVariables() {
		return variables;
	}

	public void setVariables(HashMap<String, Variable> variables) {
		this.variables = variables;
	}
	

	public void addVariables(Variable variable) {
		this.variables.put(variable.getName(), variable);
	}
	
	public HashMap<String, Type> getTypes() {
		return types;
	}

	public void setTypes(HashMap<String, Type> types) {
		this.types = types;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
