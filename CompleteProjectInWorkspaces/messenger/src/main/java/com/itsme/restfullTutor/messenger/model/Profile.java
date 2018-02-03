package com.itsme.restfullTutor.messenger.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private long id;
	private String profileName;
	private String firstname;
	private String lastname;
	private Date created;

	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profile(long id, String profileName, String firstname, String lastname) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.firstname = firstname;
		this.lastname = lastname;
		this.created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
