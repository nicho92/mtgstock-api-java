package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.List;

public class CardDetails {

	private Integer id;
	private Integer cmc;
	private String name;
	private String cost;
	private List<Legality> legal;
	private Integer lowestPrint;
	private String oracle;
	private String pwrtgh;
	private Boolean reserved;
	private String subtype;
	private String supertype;
	private String[] splitcost;
	
	@Override
	public String toString() {
		return getName();
	}
	
	
	public CardDetails() {
		legal = new ArrayList<>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCmc() {
		return cmc;
	}
	public void setCmc(Integer cmc) {
		this.cmc = cmc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public List<Legality> getLegal() {
		return legal;
	}
	public void setLegal(List<Legality> legal) {
		this.legal = legal;
	}
	public Integer getLowestPrint() {
		return lowestPrint;
	}
	public void setLowestPrint(Integer lowestPrint) {
		this.lowestPrint = lowestPrint;
	}
	public String getOracle() {
		return oracle;
	}
	public void setOracle(String oracle) {
		this.oracle = oracle;
	}
	public String getPwrtgh() {
		return pwrtgh;
	}
	public void setPwrtgh(String pwrtgh) {
		this.pwrtgh = pwrtgh;
	}
	public Boolean getReserved() {
		return reserved;
	}
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getSupertype() {
		return supertype;
	}
	public void setSupertype(String supertype) {
		this.supertype = supertype;
	}
	public String[] getSplitcost() {
		return splitcost;
	}
	public void setSplitcost(String[] splitcost) {
		this.splitcost = splitcost;
	}
	
	
	
}
