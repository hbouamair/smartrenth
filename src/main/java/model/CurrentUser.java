/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hamza
 */
public class CurrentUser {
    public static  String fullname ;
    public static int id;

    public static String getFullname() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        CurrentUser.fullname = fullname;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        CurrentUser.id = id;
    }
}
