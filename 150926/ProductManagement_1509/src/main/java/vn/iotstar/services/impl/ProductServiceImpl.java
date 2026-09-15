package vn.iotstar.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import vn.iotstar.dto.ProductDTO;
import vn.iotstar.entity.Product;
import vn.iotstar.mapper.ProductMapper;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.services.ProductService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    // Đường dẫn lưu file ảnh
    private final Path uploadDir = Paths.get("uploads/products");

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Product> products;
        
        if (keyword == null || keyword.isBlank()) {
            products = productRepository.findAll(pageable);
        } else {
            products = productRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        }
        
        // Sử dụng MapStruct để map list Entity -> DTO
        return products.map(productMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        try {
            // Xử lý lưu ảnh nếu có
            String fileName = saveImage(dto.getImage());
            dto.setImages(fileName);
            
            Product product = productMapper.toEntity(dto);
            Product saved = productRepository.save(product);
            
            return productMapper.toDTO(saved);
        } catch (IOException e) {
            throw new RuntimeException("Không thể upload ảnh", e);
        }
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        try {
            MultipartFile image = dto.getImage();
            
            // Nếu có upload ảnh mới
            if (image != null && !image.isEmpty()) {
                String oldImage = product.getImages();
                String newImage = saveImage(image);
                dto.setImages(newImage);
                
                // Xóa ảnh cũ đi cho nhẹ máy
                deleteImage(oldImage);
            } else {
                // Không upload ảnh mới thì giữ nguyên ảnh cũ
                dto.setImages(product.getImages());
            }
            
            // MapStruct tự động đè dữ liệu mới vào Entity cũ
            productMapper.updateEntity(dto, product);
            
            Product updated = productRepository.save(product);
            return productMapper.toDTO(updated);
            
        } catch (IOException e) {
            throw new RuntimeException("Không thể upload ảnh", e);
        }
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));
        
        // Nhớ xóa ảnh vật lý trước khi xóa data trong DB
        deleteImage(product.getImages());
        productRepository.delete(product);
    }

    // ==========================================
    // HÀM HỖ TRỢ XỬ LÝ FILE (Lưu và Xóa)
    // ==========================================
    
    private String saveImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }
        // Tạo thư mục nếu chưa có
        Files.createDirectories(uploadDir);
        
        String originalName = file.getOriginalFilename();
        String extension = "";
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        
        // Đổi tên file thành mã UUID để không bao giờ bị trùng tên
        String fileName = UUID.randomUUID().toString() + extension;
        Path target = uploadDir.resolve(fileName);
        
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }

    private void deleteImage(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return;
        }
        try {
            Path file = uploadDir.resolve(fileName);
            Files.deleteIfExists(file);
        } catch (IOException e) {
            System.err.println("Không thể xóa ảnh: " + fileName);
        }
    }
}