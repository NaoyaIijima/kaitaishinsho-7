package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController<ValidatedSignupForm> {
    
    @Autowired
    private UserService userService;

    //ラジオボタン用変数
    private Map<String, String> radioMarriage;

    /**
     * ラジオボタンの初期化メソッド.
     */
    private Map<String, String> initRadioMarrige() {

        Map<String, String> radio = new LinkedHashMap<>();

        // 既婚、未婚をMapに格納
        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    /**
     * ユーザー登録画面のGETメソッド用処理.
     */
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {

        // ラジオボタンの初期化メソッド呼び出し
        radioMarriage = initRadioMarrige();

        // ラジオボタン用のMapをModelに登録
        model.addAttribute("radioMarriage", radioMarriage);

        // signup.htmlに画面遷移
        return "login/signup";
    }

    /**
     * ユーザー登録画面のPOSTメソッド用処理.
     */
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, 
                                BindingResult bindingResult, Model model) {
                                    
        if(bindingResult.hasErrors()){
            return getSignUp(form, model);
        }
        
        System.out.println(form);
        
        // insert用変数
        User user = new User();

        user.setUserId(form.getUserId()); //ユーザーID
        user.setPassword(form.getPassword()); //パスワード
        user.setUserName(form.getUserName()); //ユーザー名
        user.setBirthday(form.getBirthday()); //誕生日
        user.setAge(form.getAge()); //年齢
        user.setMarriage(form.isMarriage()); //結婚ステータス
        user.setRole("ROLE_GENERAL"); //ロール（一般）

        // ユーザー登録処理
        boolean result = userService.insert(user);

        // ユーザー登録結果の判定
        if (result == true) {
            System.out.println("insert成功");
        } else {
            System.out.println("insert失敗");
        }


        // login.htmlにリダイレクト
        return "redirect:/login";
    }
}