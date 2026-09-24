package vn.iotstar.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iotstar.springsecurity.entity.OtpToken;
import vn.iotstar.springsecurity.entity.Role;
import vn.iotstar.springsecurity.entity.User;
import vn.iotstar.springsecurity.repository.OtpTokenRepository;
import vn.iotstar.springsecurity.repository.RoleRepository;
import vn.iotstar.springsecurity.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OtpTokenRepository otpTokenRepository;
    @Autowired
    private EmailService emailService;

    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(999999));
    }

    public void registerUser(String username, String email, String password, String fullName) {
        if (userRepository.findByUsernameIgnoreCase(username).size() > 0 || userRepository.findByEmailIgnoreCase(email).isPresent()) {
            throw new RuntimeException("Username or Email already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFullName(fullName);
        user.setEnabled(false); // require OTP verification
        
        Role userRole = roleRepository.findByNameIgnoreCase("ROLE_USER").stream().findFirst()
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(userRole);
        userRepository.save(user);

        sendOtp(email, "REGISTER");
    }

    public void sendOtp(String email, String type) {
        String code = generateOtp();
        OtpToken otpToken = new OtpToken();
        otpToken.setEmail(email);
        otpToken.setOtpCode(code);
        otpToken.setType(type);
        otpToken.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpTokenRepository.save(otpToken);

        emailService.sendOtpEmail(email, code);
    }

    public boolean verifyOtp(String email, String otpCode, String type) {
        Optional<OtpToken> tokenOpt = otpTokenRepository.findByEmailAndOtpCodeAndType(email, otpCode, type);
        if (tokenOpt.isPresent()) {
            OtpToken token = tokenOpt.get();
            if (token.isUsed() || token.getExpiresAt().isBefore(LocalDateTime.now())) {
                return false; // Expired or used
            }
            token.setUsed(true);
            otpTokenRepository.save(token);

            if ("REGISTER".equals(type)) {
                User user = userRepository.findByEmailIgnoreCase(email).orElseThrow();
                user.setEnabled(true);
                userRepository.save(user);
            }
            return true;
        }
        return false;
    }

    public void forgotPassword(String email) {
        if (userRepository.findByEmailIgnoreCase(email).isEmpty()) {
            throw new RuntimeException("Email not found");
        }
        sendOtp(email, "FORGOT_PASSWORD");
    }

    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}