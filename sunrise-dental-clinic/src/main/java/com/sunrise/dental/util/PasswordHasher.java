package com.sunrise.dental.util;
import org.mindrot.jbcrypt.BCrypt;
public final class PasswordHasher {
    private PasswordHasher() {
    }
    public static String hash(String p) {
        return BCrypt.hashpw(p,BCrypt.gensalt(10));
    }
    public static boolean matches(String p,String h) {
        return h!=null&&BCrypt.checkpw(p,h);
    }
}
