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
import com.hendisantika.adminlte.model.Media;
import com.hendisantika.adminlte.service.MediaService;

@Controller
@RequestMapping("media")
public class MediaController {
private MediaService mediaService;
	
	@Autowired
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }
	
	@GetMapping
    public String index() {
        return "redirect:/media/1";
    }
	
	@GetMapping(value = "/{pageNumber}")
	public String list(@PathVariable Integer pageNumber, Model model) {
		Page<Media> page = mediaService.getList(pageNumber);
		
		int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());
        
        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        
        return "media/list";
	}
	
	 @GetMapping("/add")
	    public String add(Model model) {

	        model.addAttribute("media", new Media());
	        return "genre/form";

	    }

	    @GetMapping("/edit/{id}")
	    public String edit(@PathVariable Long id, Model model) {

	        model.addAttribute("media", mediaService.get(id));
	        return "media/form";

	    }

	    @PostMapping(value = "/save")
	    public String save(Media media, final RedirectAttributes ra) {

	    	Media save = mediaService.save(media);
	        ra.addFlashAttribute("successFlash", "Media foi salvo com sucesso.");
	        return "redirect:/media";

	    }

	    @GetMapping("/delete/{id}")
	    public String delete(@PathVariable Long id) {

	    	mediaService.delete(id);
	        return "redirect:/media";

	    }
}
