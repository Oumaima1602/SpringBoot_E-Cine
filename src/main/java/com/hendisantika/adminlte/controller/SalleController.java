package com.hendisantika.adminlte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.hendisantika.adminlte.model.Salle;
import com.hendisantika.adminlte.service.SalleService;

@Controller
@RequestMapping("salle")
public class SalleController {
	
private SalleService salleService;

@Autowired
public void setSalleService(SalleService salleServices) {
	this.salleService = salleServices;
}
@GetMapping
public String index() {
    return "redirect:/salle/1";
}


@GetMapping(value = "/{pageNumber}")
public String list(@PathVariable Integer pageNumber, Model model) {
    Page<Salle> page = salleService.getList(pageNumber);

    int current = page.getNumber() + 1;
    int begin = Math.max(1, current - 5);
    int end = Math.min(begin + 10, page.getTotalPages());

    model.addAttribute("list", page);
    model.addAttribute("beginIndex", begin);
    model.addAttribute("endIndex", end);
    model.addAttribute("currentIndex", current);

    return "salle/list";

}

@GetMapping("/add")
public String add(Model model) {

    model.addAttribute("salle", new Salle());
    return "salle/form";

}

@GetMapping("/edit/{id}")
public String edit(@PathVariable Long id, Model model) {

    model.addAttribute("salle", salleService.get(id));
    return "salle/form";

}

@PostMapping(value = "/save")
public String save(Salle salle, final RedirectAttributes ra) {

    Salle save = salleService.save(salle);
    ra.addFlashAttribute("successFlash", "salle saved succesuflly.");
    return "redirect:/salle";

}

@GetMapping("/delete/{id}")
public String delete(@PathVariable Long id) {

	salleService.delete(id);
    return "redirect:/salle";

}

}
