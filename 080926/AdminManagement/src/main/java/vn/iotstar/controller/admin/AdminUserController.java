package vn.iotstar.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iotstar.entity.User;
import vn.iotstar.service.UserService;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<User> list = userService.searchUser(keyword);
        model.addAttribute("users", list);
        model.addAttribute("keyword", keyword);
        return "admin/user/list"; // Trả về file /WEB-INF/views/admin/user/list.jsp
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Thêm mới Tài khoản");
        return "admin/user/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> opt = userService.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("user", opt.get());
            model.addAttribute("pageTitle", "Chỉnh sửa Tài khoản");
            return "admin/user/form";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy tài khoản!");
        return "redirect:/admin/users";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        // Kiểm tra trùng username khi thêm mới (ID == null)
        if (user.getId() == null && userService.isUsernameExists(user.getUsername())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên đăng nhập đã tồn tại!");
            return "redirect:/admin/users/add";
        }
        
        userService.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Lưu tài khoản thành công!");
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa tài khoản thành công!");
        return "redirect:/admin/users";
    }
}