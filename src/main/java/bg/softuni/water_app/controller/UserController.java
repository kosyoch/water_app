package bg.softuni.water_app.controller;

import bg.softuni.water_app.model.dto.user.UserRegistrationDto;
import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal){
        model.addAttribute("user", new User());
        if (principal != null){
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(
            @ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        if(!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("passwordMismatch", true);
            return new ModelAndView("redirect:register");
        }



        userService.register(userRegistrationDto);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("username", "");
        model.addAttribute("password", "");
        return "login";
    }
    @PostMapping("/login-error")
    public String onFailure(String username, String password, Model model){
        model.addAttribute("username",username);
        model.addAttribute("password", password);
        model.addAttribute("bad_credentials",true);
        return "login";
    }

    @GetMapping("/wallet/add")
    public String addFunds (Model model) {
        model.addAttribute("moneyToAdd", "");
        return ("wallet-add");
    }

    @PostMapping("/wallet/add")

    public ModelAndView addFunds(@AuthenticationPrincipal User user, @RequestParam BigDecimal moneyToAdd){
        String currentUsername = user.getUsername();
        userService.addFunds(moneyToAdd, currentUsername);
        return new ModelAndView("redirect:/home");
    }



}
