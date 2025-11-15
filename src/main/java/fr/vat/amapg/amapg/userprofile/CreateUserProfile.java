package fr.vat.amapg.amapg.userprofile;

import fr.vat.amapg.amapg.userprofile.entity.UserProfile;
import fr.vat.amapg.amapg.userprofile.persistence.UserProfileAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserProfile {

    private final UserProfileAdapter userProfileAdapter;

    public void execute(UserProfile userProfile) {
        String userIdentitySeed = userProfile.firstName() + userProfile.lastName() + userProfile.email();

        userProfileAdapter.saveUserProfile(userProfile, userIdentitySeed);
    }

}
