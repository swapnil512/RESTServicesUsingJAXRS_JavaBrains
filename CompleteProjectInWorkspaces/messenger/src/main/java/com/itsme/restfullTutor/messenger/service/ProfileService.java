package com.itsme.restfullTutor.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.itsme.restfullTutor.messenger.database.DatabaseClass;
import com.itsme.restfullTutor.messenger.model.Profile;

public class ProfileService {
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("ITSME", new Profile(1, "ITSME", "Swapnil", "Sable"));
		profiles.put("Hello Jersey", new Profile(2, "Hello Jersey", "Jersey", "Banzos"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profilename) {
		return profiles.get(profilename);
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profilename) {
		return profiles.remove(profilename);
	}

}
