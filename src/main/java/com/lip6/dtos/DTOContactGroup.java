package com.lip6.dtos;

import java.util.Set;

public class DTOContactGroup {

    private long groupId;
    private String groupName;
    private Set<DTOContact> contacts;

    public DTOContactGroup(long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public DTOContactGroup(long groupId, String groupName, Set<DTOContact> contacts) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.contacts = contacts;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<DTOContact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<DTOContact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "DTOContactGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
