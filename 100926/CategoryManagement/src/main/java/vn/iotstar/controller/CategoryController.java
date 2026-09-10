package vn.iotstar.controller;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // 1. Hiển thị danh sách kết hợp Phân trang & Tìm kiếm
    @GetMapping("")
    public String listAndSearch(Model model,
                                @RequestParam(name = "keyword", required = false) String keyword,
                                @RequestParam(name = "size", defaultValue = "5") int size,
                                @RequestParam(name = "page", defaultValue = "1") int page) {
        
        // Mặc định sắp xếp theo tên danh mục tăng dần
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("name").ascending());
        Page<Category> categoryPage = categoryService.searchByName(keyword, pageable);

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("keyword", keyword); // Giữ lại keyword trên giao diện

        // Xử lý tạo mảng số trang hiển thị (Ví dụ: 1 2 3)
        int totalPages = categoryPage.getTotalPages();
        if (totalPages > 0) {
            // Giới hạn hiển thị tối đa 5 số trang ở thanh phân trang
            int start = Math.max(1, page - 2);
            int end = Math.min(page + 2, totalPages);
            if (totalPages > 5) {
                if (end == totalPages) start = end - 4;
                else if (start == 1) end = start + 4;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "category/list"; // Đường dẫn tới file html
    }

    // 2. Hiển thị Form Thêm Mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add New Category");
        return "category/form";
    }

    // 3. Hiển thị Form Chỉnh Sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Category> optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            model.addAttribute("category", optCategory.get());
            model.addAttribute("pageTitle", "Edit Category");
            return "category/form";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "⚠ Không tìm thấy Category.");
        return "redirect:/categories";
    }

    // 4. Xử lý Lưu dữ liệu (Cho cả Thêm và Sửa)
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        try {
            boolean isNew = (category.getCategoryId() == null);
            categoryService.save(category);
            
            String message = isNew ? "✓ Thêm Category thành công." : "✓ Cập nhật Category thành công.";
            redirectAttributes.addFlashAttribute("successMessage", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "✕ Dữ liệu không hợp lệ hoặc đã xảy ra lỗi.");
        }
        return "redirect:/categories";
    }

    // 5. Xử lý Xóa
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "✓ Xóa Category thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "⚠ Không thể xóa Category này.");
        }
        return "redirect:/categories";
    }
}