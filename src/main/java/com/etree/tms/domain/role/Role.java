package com.etree.tms.domain.role;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"role\"")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3256297046101845323L;
	
	@Id
	@Column(name="roleid")
	private String roleId;
	
	@Column(name="name")
	private String name;
	
	/*@Column(name="menu")
	private Blob menu;*/
	
	/*@Column(name="isactive")
	private boolean isActive;
	
	
	@Column(name="organizationid")
	private String organizationId;
		
	@Column(name="modifieddate")
	private String modifiedDate;
	
	@Column(name="createddate")
	private String createdDate;
	*/
	
	/*public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}*/
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/*public Blob getMenu() {
		return menu;
	}
	public void setMenu(Blob menu) {
		this.menu = menu;}*/

	/*public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}*/
}


