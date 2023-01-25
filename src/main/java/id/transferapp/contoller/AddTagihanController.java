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

import id.transferapp.entity.Tagihan;
import id.transferapp.service.admin.TagihanService;

@Controller
@RequestMapping("/admin/tagihan")
public class AddTagihanController {

    @Autowired
    TagihanService tagihanService;

    @GetMapping
    public String homeAddTagihan(Model model) {
        List<Tagihan> tagihans = tagihanService.getAllTagihan();
        model.addAttribute("tagihan", new Tagihan());
        model.addAttribute("listTagihan", tagihans);
        return "addTagihan";
    }

    @PostMapping
    public String addTiket(@ModelAttribute("tagihan") Tagihan tagihan, BindingResult result, Model model,
            RedirectAttributes redirectAttributes) {

        List<Tagihan> tagihans = tagihanService.getAllTagihan();
        if (result.hasErrors()) {
            model.addAttribute("listTagihan", tagihans);
            return "addTagihan";
        } else {
            tagihanService.saveTagihan(tagihan);
            redirectAttributes.addFlashAttribute("Success", "Success inserted ticket");
            return "redirect:/admin/tagihan";
        }

    }

    @GetMapping("/delete")
    public String deleteTiket(@RequestParam("id") Long id) {
        tagihanService.deleteTagihan(id);
        return "redirect:/admin/tagihan";
    }
}
