package id.transferapp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.transferapp.entity.Product;
import id.transferapp.entity.SellProduct;
import id.transferapp.service.admin.ProductService;
import id.transferapp.service.operator.SellProductService;

@Controller
@RequestMapping("/operator/commerce")
public class CommerceController {

    @Autowired
    SellProductService sellProductService;

    @Autowired
    ProductService productService;

    @GetMapping
    public String homeCommerce(Model model) {
        List<Product> products = productService.getAllProduct();
        JSONArray parent = new JSONArray();
        try {
            for (Product product : products) {
                JSONObject child = new JSONObject();
                child.put("id", product.getId());
                child.put("name", product.getName());
                child.put("price", product.getPrice());
                parent.put(child);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getLocalizedMessage().toString());
        }
        model.addAttribute("listProduct", products);
        model.addAttribute("datas", parent);
        model.addAttribute("sell", new SellProduct());
        return "operatorCommerce";
    }

    @GetMapping("/history")
    public String historyCommerce(Model model) {
        List<SellProduct> sellProducts = sellProductService.getAllProductSell();
        model.addAttribute("histories", sellProducts);
        return "historyCommerce";
    }

    @PostMapping
    public String addTiket(@ModelAttribute("sell") SellProduct product, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {

        List<Product> products = productService.getAllProduct();
        if (result.hasErrors()) {
            model.addAttribute("listProduct", products);
            return "operatorCommerce";
        } else {
            sellProductService.buyProduct(product);
            redirectAttributes.addFlashAttribute("Success", "Success inserted ticket");
            return "redirect:/operator/commerce/history";
        }

    }
}
