package com.cartisan.management.modules.sys.controller;

import com.cartisan.management.modules.sys.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 *
 * @author colin
 */
@Controller
@Slf4j
public class LoginController {
    @Autowired
    private Producer producer;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        final String text = producer.createText();
        ShiroUtils.setSessionattribute(Constants.KAPTCHA_SESSION_KEY, text);

        final BufferedImage image = producer.createImage(text);
        final ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @ResponseBody
    @PostMapping(value = "/sys/login")
    public HashMap<String, Object> login(String username, String password, String captcha) {
        final String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);

        final HashMap result = new HashMap();
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            result.put("code", "1");
            result.put("msg", "验证码不正确。");
        }
        else {

            try {

                final Subject subject = ShiroUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);

                result.put("code", "0");
            } catch (UnknownAccountException e) {
                result.put("code", "500");
                result.put("msg", e.getMessage());
            } catch (IncorrectCredentialsException e) {
                result.put("code", "500");
                result.put("msg", "账号或密码不正确。");
            } catch (LockedAccountException e) {
                result.put("code", "500");
                result.put("msg", "账号已被锁定，请联系管理员。");
            } catch (AuthenticationException e) {
                result.put("code", "500");
                result.put("msg", "账户验证失败。");
            }
        }



        return result;
    }

    @GetMapping("logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:login.html";
    }
}
