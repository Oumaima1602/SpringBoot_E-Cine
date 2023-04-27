package com.hendisantika.adminlte.controller;

import com.hendisantika.adminlte.model.Genre;
import com.hendisantika.adminlte.service.GenreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("genre")
public class GenreController {
	
private GenreService genreService;
	
	@Autowired
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }
	
	@GetMapping
    public String index() {
        return "redirect:/genre/1";
    }
	
	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Genre> page = genreService.getList(pageNumber);
		
		int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        
        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        
        return "genre/list";
	}
	
	 @GetMapping("/add")
	    public String add(Model model) {

	        model.addAttribute("genre", new Genre());
	        return "genre/form";

	    }

	    @GetMapping("/edit/{id}")
	    public String edit(@PathVariable Long id, Model model) {

	        model.addAttribute("genre", genreService.get(id));
	        return "genre/form";

	    }

	    @PostMapping(value = "/save")
	    public String save(Genre genre, final RedirectAttributes ra) {

	    	Genre save = genreService.save(genre);
	        ra.addFlashAttribute("successFlash", "Cliente foi salvo com sucesso.");
	        return "redirect:/genre";

	    }

	    @GetMapping("/delete/{id}")
	    public String delete(@PathVariable Long id) {

	    	genreService.delete(id);
	        return "redirect:/genre";

	    }

}
