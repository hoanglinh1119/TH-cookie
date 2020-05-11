package com.hoanglinh.controller;

import com.hoanglinh.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes("user")
public class UserController {
    @ModelAttribute("user")
    public User setUpUserForm(){
        return new User();
    }
    @RequestMapping("/login")
    public String Index(@CookieValue(value = "setUser",defaultValue = "")String setUser, Model model){
        Cookie cookie=new Cookie("setUser",setUser);
        model.addAttribute("cookieValue",cookie);
        return "login";
    }
    @PostMapping("/dologin")
    public  String doLogin(@ModelAttribute("user") User user, Model model, @CookieValue(value = "setUser",defaultValue = "")String setUser,
                           HttpServletResponse response, HttpServletRequest request){
        String a = user.getEmail();
        String b = user.getPassword();

        if (a.equals("hoanglinh@gmail.com")&&b.equals("hoanglinh")){
            if (user.getEmail()!=null)
                setUser = user.getEmail();

                //create cookie and set it in response. tao cookie va set no trong reponse
                Cookie cookie = new Cookie("setUser", setUser);
                cookie.setMaxAge(24 * 60 * 60);
                response.addCookie(cookie);
                //lay mang cookie
                Cookie[] cookies = request.getCookies();
                for (Cookie ck : cookies) {
                    if (ck.getName().equals("setUser")) {
                        model.addAttribute("cookieValue", ck);
                        break;
                    } else {
                        ck.setValue("");
                        model.addAttribute("cookieValue", ck);
                        break;
                    }

            }
            model.addAttribute("message","login success!!!");
        }else {
            user.setEmail("");
            Cookie cookie=new Cookie("setUser",setUser);
            model.addAttribute("cookieValue",cookie);
            model.addAttribute("message","Login failed!!");
        }
        return "login";
    }
}
