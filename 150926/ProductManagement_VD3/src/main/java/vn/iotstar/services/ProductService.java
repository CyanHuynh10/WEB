package vn.iotstar.services;

import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import vn.iotstar.dto.ProductDTO;
import vn.iotstar.entity.Product;

public interface ProductService {
    Page<ProductDTO> findAll(String keyword, int page, int size);
    ProductDTO findById(Long id);
    ProductDTO create(ProductDTO dto);
    ProductDTO update(Long id, ProductDTO dto);
    void delete(Long id);
    
    // Xử lý hình ảnh[cite: 1]
    String saveImage(MultipartFile file) throws IOException;
    void saveImages(Product product, List<MultipartFile> files) throws IOException;
    Long deleteImage(Long imageId);
    void deleteFile(String fileName);
}