package net.codejava;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class AppController {
 
	@Autowired
    private UserRepository repo;
     
	
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "signup_form";
    }
    
    @PostMapping("/process_register")
     
    public String processRegister(User user) {
     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
     String encodedPassword = encoder.encode(user.getPassword());
     user.setPassword(encodedPassword);
        repo.save(user);
         
        return "register_success";
    }
    
    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
    List <User> listUsers = repo.findAll();
    model.addAttribute("listUsers", listUsers);
    	return "users";
    }
    
//	@Autowired
//	private CategoryService service;
//	
//	@RequestMapping("/")
	
//	public String viewHomePage(Model model) {
//		List<Category> listCategory = service.listAll();
//		model.addAttribute("listCategory",listCategory);
//		return "Categoryindex";
//	}
//	
//@RequestMapping("/new")
//	
//	public String showCategoryForm(Model model) {
//
//		Category category = new Category();
//		model.addAttribute("Category",category);
//		return "new_category";
//	}
//    
//@RequestMapping(value = "/save", method = RequestMethod.POST)
//public String saveCategory(@ModelAttribute("category") Category category) {
//	service.save(category);
//	return "redirect:/";
//	
//}
}
