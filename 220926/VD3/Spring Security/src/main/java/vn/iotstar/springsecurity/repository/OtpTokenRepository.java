package vn.iotstar.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iotstar.springsecurity.entity.OtpToken;
import java.util.Optional;

public interface OtpTokenRepository extends JpaRepository<OtpToken, Long> {
    Optional<OtpToken> findByEmailAndOtpCodeAndType(String email, String otpCode, String type);
}