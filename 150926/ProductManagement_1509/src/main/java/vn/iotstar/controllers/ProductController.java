package vn.iotstar.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.iotstar.dto.ProductDTO;
import vn.iotstar.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // =====================================
    // 1. HIỂN THỊ DANH SÁCH (KÈM TÌM KIẾM & PHÂN TRANG)
    // =====================================
    @GetMapping
    public String listProducts(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {

        Page<ProductDTO> productPage = productService.findAll(keyword, page, size);

        model.addAttribute("products", productPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        return "products/list";
    }

    // =====================================
    // 2. HIỂN THỊ FORM THÊM MỚI
    // =====================================
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("formTitle", "Thêm sản phẩm");
        return "products/form";
    }

    // =====================================
    // 3. XỬ LÝ LƯU THÊM MỚI (CÓ UPLOAD ẢNH)
    // =====================================
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public String create(
            @Valid @ModelAttribute("product") ProductDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Thêm sản phẩm");
            return "products/form";
        }

        try {
            productService.create(dto);
            redirectAttributes.addFlashAttribute("success", "Thêm sản phẩm thành công");
            return "redirect:/products";
        } catch (Exception e) {
            model.addAttribute("formTitle", "Thêm sản phẩm");
            model.addAttribute("error", "Không thể thêm sản phẩm: " + e.getMessage());
            return "products/form";
        }
    }

    // =====================================
    // 4. HIỂN THỊ FORM CHỈNH SỬA
    // =====================================
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        ProductDTO product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("formTitle", "Cập nhật sản phẩm");
        return "products/form";
    }

    // =====================================
    // 5. XỬ LÝ LƯU CHỈNH SỬA (CÓ UPLOAD ẢNH)
    // =====================================
    @PostMapping(value = "/edit/{id}", consumes = "multipart/form-data")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("product") ProductDTO dto,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Cập nhật sản phẩm");
            return "products/form";
        }

        try {
            productService.update(id, dto);
            redirectAttributes.addFlashAttribute("success", "Cập nhật sản phẩm thành công");
            return "redirect:/products";
        } catch (Exception e) {
            model.addAttribute("formTitle", "Cập nhật sản phẩm");
            model.addAttribute("error", "Không thể cập nhật sản phẩm: " + e.getMessage());
            return "products/form";
        }
    }

    // =====================================
    // 6. XỬ LÝ XÓA SẢN PHẨM (VÀ ẢNH ĐI KÈM)
    // =====================================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Xóa sản phẩm thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể xóa sản phẩm");
        }
        return "redirect:/products";
    }
}