package fr.vat.amapg.amapg.security.register.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserToRegisterDto {

    private @NotNull String firstName;
    private @NotNull String lastName;
    private @NotNull String address;
    private @NotNull
    @Digits(integer = 5, fraction = 0) String postalCode;
    private @NotNull String city;
    private @NotNull
    @Digits(integer = 10, fraction = 0) String phoneNumber;
    private @NotNull
    @Email String email;

}
