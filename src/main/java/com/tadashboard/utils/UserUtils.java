package com.tadashboard.utils;

import com.google.gson.JsonObject;
import com.tadashboard.helpers.Helpers;
import com.tadashboard.helpers.JsonHelper;
import com.tadashboard.objects.User;

public class UserUtils {

    public static User getUser(String condition) {
        User user;
        String jsonPath = Helpers.getCurrentDir() + "/src/test/resources/dataset/login.json";
        JsonObject userObj = JsonHelper.getJsonObject(jsonPath);
        user = JsonHelper.getJsonAsClass(userObj.getAsJsonObject(condition), User.class);
        return user;
    }

    public static User getValidAccount(){
       return UserUtils.getUser("validAccount");
    }
    public static User getInvalidUser(){
        return UserUtils.getUser("incorrectUsernameAccount");
    }
    public static User getInvalidPassword(){
        return UserUtils.getUser("incorrectPasswordAccount");
    }

    public static User getEmptyAccount(){
        return UserUtils.getUser("blankAccount");
    }
    public static User getOtherRepo(){
        return UserUtils.getUser("otherRepository");
    }

}
