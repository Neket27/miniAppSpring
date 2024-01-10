package app.miniappspring.utils.jwtToken;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncoderPassword  {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(String password){
        return passwordEncoder.encode(password);
    }

}
