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

    @GetMapping
    public String listProducts(@RequestParam(defaultValue = "") String keyword,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size, Model model) {
        model.addAttribute("products", productService.findAll(keyword, page, size));
        model.addAttribute("keyword", keyword);
        model.addAttribute("size", size);
        return "products/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("formTitle", "Thêm sản phẩm");
        return "products/form";
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public String create(@Valid @ModelAttribute("product") ProductDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Thêm sản phẩm");
            return "products/form";
        }
        productService.create(dto);
        redirectAttributes.addFlashAttribute("success", "Thêm sản phẩm thành công");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("formTitle", "Cập nhật sản phẩm");
        return "products/form";
    }

    @PostMapping(value = "/edit/{id}", consumes = "multipart/form-data")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("product") ProductDTO dto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("formTitle", "Cập nhật sản phẩm");
            return "products/form";
        }
        productService.update(id, dto);
        redirectAttributes.addFlashAttribute("success", "Cập nhật thành công");
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Xóa thành công");
        return "redirect:/products";
    }

    // Endpoint xóa 1 ảnh bằng AJAX[cite: 1]
    @PostMapping("/image/delete/{imageId}")
    public String deleteImage(@PathVariable Long imageId, RedirectAttributes redirectAttributes) {
        try {
            Long productId = productService.deleteImage(imageId);
            redirectAttributes.addFlashAttribute("success", "Xóa ảnh thành công");
            return "redirect:/products/edit/" + productId;
        } catch (Exception e) {
            return "redirect:/products";
        }
    }
}