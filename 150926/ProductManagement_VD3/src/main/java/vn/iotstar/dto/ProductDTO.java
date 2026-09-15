package vn.iotstar.dto;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = 200)
    private String name;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0")
    private BigDecimal price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0)
    private Integer quantity;

    @Size(max = 1000)
    private String description;

    // Danh sách ảnh hiển thị[cite: 1]
    private List<ProductImageDTO> images;

    // Các file nhận từ upload form (nhiều file)[cite: 1]
    private List<MultipartFile> imageFiles;
}