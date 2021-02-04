package com.bee.leetcode.db.bean;

public class ForgetPassword {
    private static int number;
    private Data data;
    private static String message;

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        ForgetPassword.number = number;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        ForgetPassword.message = message;
    }

    public static class Data{
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



}
