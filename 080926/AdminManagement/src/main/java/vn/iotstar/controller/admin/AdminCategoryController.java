package vn.iotstar.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iotstar.entity.Category;
import vn.iotstar.service.CategoryService;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    // 1. Hiển thị danh sách & Tìm kiếm
    @GetMapping("")
    public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<Category> list = categoryService.searchCategory(keyword);
        model.addAttribute("categories", list);
        model.addAttribute("keyword", keyword);
        return "admin/category/list"; // Trả về file /WEB-INF/views/admin/category/list.jsp
    }

    // 2. Hiển thị Form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Thêm mới Danh mục");
        return "admin/category/form";
    }

    // 3. Hiển thị Form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Category> opt = categoryService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("category", opt.get());
            model.addAttribute("pageTitle", "Chỉnh sửa Danh mục");
            return "admin/category/form";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy danh mục!");
        return "redirect:/admin/categories";
    }

    // 4. Lưu dữ liệu (Add & Edit)
    @PostMapping("/save")
    public String save(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Lưu danh mục thành công!");
        return "redirect:/admin/categories";
    }

    // 5. Xóa danh mục
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công!");
        return "redirect:/admin/categories";
    }
}