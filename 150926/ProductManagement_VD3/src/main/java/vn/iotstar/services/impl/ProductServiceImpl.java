package vn.iotstar.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
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
import vn.iotstar.entity.ProductImage;
import vn.iotstar.mapper.ProductMapper;
import vn.iotstar.repository.ProductImageRepository;
import vn.iotstar.repository.ProductRepository;
import vn.iotstar.services.ProductService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductImageRepository imageRepository;
    
    private final Path uploadDir = Paths.get("uploads/products");

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Product> products = (keyword == null || keyword.isBlank()) ? 
                productRepository.findAll(pageable) : 
                productRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        return products.map(productMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        try {
            Product product = productMapper.toEntity(dto);
            Product saved = productRepository.save(product);
            saveImages(saved, dto.getImageFiles());
            return productMapper.toDTO(productRepository.save(saved));
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload", e);
        }
    }

    @Override
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
            productMapper.updateEntity(dto, product);
            saveImages(product, dto.getImageFiles());
            return productMapper.toDTO(productRepository.save(product));
        } catch (IOException e) {
            throw new RuntimeException("Lỗi upload", e);
        }
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy"));
        if (product.getImages() != null) {
            for (ProductImage image : product.getImages()) {
                deleteFile(image.getImageUrl());
            }
        }
        productRepository.delete(product);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;
        Files.createDirectories(uploadDir);
        String originalName = file.getOriginalFilename();
        String ext = (originalName != null && originalName.contains(".")) ? originalName.substring(originalName.lastIndexOf(".")) : "";
        String fileName = UUID.randomUUID().toString() + ext;
        Files.copy(file.getInputStream(), uploadDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

    @Override
    public void saveImages(Product product, List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) return;
        int currentOrder = product.getImages().size();
        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) continue;
            String fileName = saveImage(file);
            boolean isPrimary = product.getImages().isEmpty();
            ProductImage image = ProductImage.builder()
                    .product(product)
                    .imageUrl(fileName)
                    .primary(isPrimary)
                    .displayOrder(currentOrder++)
                    .createdAt(LocalDateTime.now())
                    .build();
            product.getImages().add(image);
        }
    }

    @Override
    public Long deleteImage(Long imageId) {
        ProductImage image = imageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("Không thấy ảnh"));
        Product product = image.getProduct();
        boolean wasPrimary = Boolean.TRUE.equals(image.getPrimary());
        deleteFile(image.getImageUrl());
        product.getImages().remove(image);
        imageRepository.delete(image);
        if (wasPrimary && !product.getImages().isEmpty()) {
            product.getImages().get(0).setPrimary(true);
            product.getImages().get(0).setDisplayOrder(0);
        }
        return product.getId();
    }

    @Override
    public void deleteFile(String fileName) {
        if (fileName == null || fileName.isBlank()) return;
        try {
            Path file = uploadDir.toAbsolutePath().normalize().resolve(fileName).normalize();
            Files.deleteIfExists(file);
        } catch (IOException ignored) {}
    }
}