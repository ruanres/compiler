package core;

import java.util.ArrayList;
import java.util.List;


public class Node {
	
	List<Node> nodeChildren = new ArrayList<Node>();
	private String value;
	private String tipo;
	private String identifier;
	
	public Node(String value, String tipo, String identifier) {
		this.setValue(value);
		this.setTipo(tipo);
		this.setIdentifier(identifier);
	}
	
	public void addNode(Node n) {
		nodeChildren.add(n);
	}

	public String getValue() {
		return value;
	}
	

	public void setValue(String value) {
		this.value = value;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@Override
	public String toString() {
		return getValue();
	}

}