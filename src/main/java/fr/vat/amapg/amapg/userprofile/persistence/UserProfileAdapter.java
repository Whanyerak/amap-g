package fr.vat.amapg.amapg.userprofile.persistence;

import fr.vat.amapg.amapg.userprofile.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProfileAdapter {

    private final UserProfileMongoDao userProfileMongoDao;

    public void saveUserProfile(UserProfile userProfile, String userIdentitySeed) {
        var userProfileMongoDto = new UserProfileMongoDto(
                UUID.nameUUIDFromBytes(userProfile.getIdentitySeed().getBytes()),
                UUID.nameUUIDFromBytes(userIdentitySeed.getBytes()),
                userProfile.firstName(),
                userProfile.lastName(),
                userProfile.address(),
                userProfile.postalCode(),
                userProfile.city(),
                userProfile.phoneNumber(),
                userProfile.email()
        );

        userProfileMongoDao.save(userProfileMongoDto);
    }

}
