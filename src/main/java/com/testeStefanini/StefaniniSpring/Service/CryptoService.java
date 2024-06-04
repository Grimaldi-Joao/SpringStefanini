package com.testeStefanini.StefaniniSpring.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptoService {
    
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
