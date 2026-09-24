package vn.iotstar.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.iotstar.springsecurity.security.CustomUserDetails;
import vn.iotstar.springsecurity.entity.Product;
import vn.iotstar.springsecurity.entity.User;
import vn.iotstar.springsecurity.repository.ProductRepository;
import vn.iotstar.springsecurity.repository.UserRepository;
import vn.iotstar.springsecurity.service.CloudinaryService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String listProducts(@RequestParam(defaultValue = "0") int page, 
                               @RequestParam(defaultValue = "") String search, Model model) {
        Page<Product> productPage;
        if (search.isEmpty()) {
            productPage = productRepository.findAll(PageRequest.of(page, 5));
        } else {
            productPage = productRepository.findByNameContainingIgnoreCase(search, PageRequest.of(page, 5));
        }
        model.addAttribute("productPage", productPage);
        model.addAttribute("search", search);
        return "product/list";
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "product/form";
    }

    @PostMapping("/save")
    public String saveProduct(@RequestParam String name, @RequestParam String description,
                              @RequestParam BigDecimal price, @RequestParam(required = false) MultipartFile image,
                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        
        User user = userRepository.findByUsernameIgnoreCase(userDetails.getUsername()).get(0);
        product.setUser(user);

        if (image != null && !image.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadImage(image);
                product.setImageUrl(imageUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        productRepository.save(product);
        return "redirect:/products";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
}