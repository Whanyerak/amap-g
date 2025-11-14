package fr.vat.amapg.amapg.security.persistence;

import fr.vat.amapg.amapg.security.register.entity.User;
import fr.vat.amapg.amapg.security.register.port.RegisterUserPort;
import lombok.RequiredArgsConstructor;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.passay.IllegalCharacterRule.ERROR_CODE;

@Component
@RequiredArgsConstructor
public class RegisterUserAdapter implements RegisterUserPort {

    private final UserMongoDao userMongoDao;
    private final UserProfileMongoDao userProfileMongoDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void registerUser(User user, String userIdSeed) {
        var userMongoDto = new UserMongoDto(
                UUID.nameUUIDFromBytes(userIdSeed.getBytes()),
                user.email(),
                generatePassword(),
                Set.of("USER")
        );

        String seed = user.firstName() + user.lastName();

        var userProfileMongoDto = new UserProfileMongoDto(
                UUID.nameUUIDFromBytes(seed.getBytes()),
                userMongoDto.getId(),
                user.firstName(),
                user.lastName(),
                user.address(),
                user.postalCode(),
                user.city(),
                user.phoneNumber(),
                user.email()
        );

        userMongoDao.save(userMongoDto);
        userProfileMongoDao.save(userProfileMongoDto);
    }

    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        return passwordEncoder.encode(gen.generatePassword(
            10,
            List.of(
                splCharRule,
                lowerCaseRule,
                upperCaseRule,
                digitRule)
            )
        );
    }

}
