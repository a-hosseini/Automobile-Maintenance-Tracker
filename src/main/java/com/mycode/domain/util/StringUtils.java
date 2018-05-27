/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycode.domain.util;

import java.util.Random;

/**
 *
 * @author ahossein
 */
public class StringUtils {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public static String generateARandomString(Random rnd, int length) {

        StringBuilder salt = new StringBuilder();

        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            salt.append(CHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}
