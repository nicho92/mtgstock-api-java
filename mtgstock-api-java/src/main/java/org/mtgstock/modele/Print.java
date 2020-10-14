package org.mtgstock.modele;

import java.util.ArrayList;
import java.util.List;

public class Print {

	
	protected Integer id;
	protected String name;
	protected String detailledName;
	protected String image;
	protected String iconClass;
	protected List<Legality> legal;
	protected String rarity;
	protected Boolean reserved;
	protected Integer setId;
	protected String setName;
	protected String setType;
	protected Boolean includeDefault;
	protected boolean extendedArt;
	protected boolean oversized;
	protected boolean borderless;
	protected boolean showcase;
	
	@Override
	public String toString() {
		return getCleanName();
	}
	
	public Print() {
		legal = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCleanName()
	{
		return getName().replaceAll("\\([^()]*\\)", "").trim();
	}
	
	public String getDetailledName() {
		return detailledName;
	}

	public void setDetailledName(String detailledName) {
		this.detailledName = detailledName;
	}
	
	public boolean isShowcase() {
		return showcase;
	}

	public void setShowcase(boolean showcase) {
		this.showcase = showcase;
	}

	public boolean isBorderless() {
		return borderless;
	}

	public void setBorderless(boolean borderless) {
		this.borderless = borderless;
	}


	public boolean isExtendedArt() {
		return extendedArt;
	}

	public void setExtendedArt(boolean extendedArt) {
		this.extendedArt = extendedArt;
	}

	public boolean isOversized() {
		return oversized;
	}

	public void setOversized(boolean oversized) {
		this.oversized = oversized;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public List<Legality> getLegal() {
		return legal;
	}
	public void setLegal(List<Legality> legal) {
		this.legal = legal;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public Boolean getReserved() {
		return reserved;
	}
	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}
	public Integer getSetId() {
		return setId;
	}
	public void setSetId(Integer setId) {
		this.setId = setId;
	}
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}
	public String getSetType() {
		return setType;
	}
	public void setSetType(String setType) {
		this.setType = setType;
	}
	public Boolean getIncludeDefault() {
		return includeDefault;
	}
	public void setIncludeDefault(Boolean includeDefault) {
		this.includeDefault = includeDefault;
	}
}
