package bg.softuni.water_app.service;

import bg.softuni.water_app.model.dto.user.UserRegistrationDto;
import bg.softuni.water_app.model.entity.User;

import java.math.BigDecimal;

public interface UserService {

    void register (UserRegistrationDto userRegistrationDto);

    void addFunds(BigDecimal moneyToAdd, String username);

    BigDecimal getCurrentWallet(String username);

}
