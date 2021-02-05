package com.bee.leetcode.db.bean;

//忘记密码

public class ForgetPassword {
      private String token;
      private UserBean user;

       public String getToken() {
           return token;
       }

       public void setToken(String token) {
           this.token = token;
       }

       public UserBean getUser() {
           return user;
       }

       public void setUser(UserBean user) {
           this.user = user;
       }
   }




