package bg.softuni.water_app.service;

import bg.softuni.water_app.model.dto.user.UserLoginBindingModel;
import bg.softuni.water_app.model.dto.user.UserRegisterBindingModel;
import bg.softuni.water_app.model.entity.User;

public interface UserService {

    boolean register (UserRegisterBindingModel userRegisterBindingModel);

    boolean login (UserLoginBindingModel userLoginBindingModel);

    void logout();

    User getLoggedUser();
}
