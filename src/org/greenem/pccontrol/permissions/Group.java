package org.greenem.pccontrol.permissions;

import java.util.List;

public class Group {
	private String name;
	private List<Permission> permissions;
	private String password;
	
	public Group(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}
	public void addPermissions(List<Permission> permissions) {
		for(Permission permission : permissions) {
			this.permissions.add(permission);
		}
	}
	public void removePermission(Permission permission) {
		this.permissions.remove(permission);
	}
	public void removePermissions(List<Permission> permissions) {
		for(Permission permission : permissions) {
			this.permissions.remove(permission);
		}
	}
	public void clearPermissions() {
		this.permissions.clear();
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
