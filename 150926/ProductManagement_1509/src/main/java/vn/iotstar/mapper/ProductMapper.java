package vn.iotstar.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import vn.iotstar.dto.ProductDTO;
import vn.iotstar.entity.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    // Chuyển từ Entity sang DTO (Bỏ qua trường MultipartFile image vì DB không có)
    @Mapping(target = "image", ignore = true)
    ProductDTO toDTO(Product entity);

    // Chuyển từ DTO sang Entity
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductDTO dto);

    // Cập nhật dữ liệu từ DTO đè lên Entity có sẵn (Dùng cho chức năng Edit)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntity(ProductDTO dto, @MappingTarget Product entity);
}