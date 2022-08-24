package org.greenem.pccontrol.initialization;

import java.util.ListIterator;

import org.greenem.pccontrol.data.References;
import org.greenem.pccontrol.permissions.Group;

public class Initialization {
	public static void initDefaultGroups() {
		String defaultUserName = "default";
		ListIterator<Group> iter = References.groups.listIterator();
		if(!References.groups.isEmpty()) {
			while(iter.hasNext()){
			    if(iter.next().getName().equals(defaultUserName)){
			        iter.remove();
			    }
			}
		}
		Group defaultGroup = new Group(defaultUserName);
		
		References.groups.add(defaultGroup);
	}
}
