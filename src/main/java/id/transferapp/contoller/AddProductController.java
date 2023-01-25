package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.Product;
import id.transferapp.service.admin.ProductService;

@Controller
@RequestMapping("/admin/product")
public class AddProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public String homeAddProduct(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("product", new Product());
        model.addAttribute("listProduct", products);
        return "addProduct";
    }

    @PostMapping
    public String addProduct(@ModelAttribute("product") Product product, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {

        List<Product> products = productService.getAllProduct();
        if (result.hasErrors()) {
            model.addAttribute("listProduct", products);
            return "addProduct";
        } else {
            productService.saveProduct(product);
            redirectAttributes.addFlashAttribute("Success", "Success inserted ticket");
            return "redirect:/admin/product";
        }

    }

    @GetMapping("/delete")
    public String deleteTiket(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }

}
