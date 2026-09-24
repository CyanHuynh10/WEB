package vn.iotstar.springsecurity.dto;
import lombok.Data;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String images;
    private String roleName;
    private boolean enabled;
}
