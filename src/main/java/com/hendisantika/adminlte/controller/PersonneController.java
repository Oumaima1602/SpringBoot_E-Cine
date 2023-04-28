package com.hendisantika.adminlte.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hendisantika.adminlte.model.Personne;
import com.hendisantika.adminlte.service.NationaliteService;
import com.hendisantika.adminlte.service.PersonneService;
import com.hendisantika.adminlte.util.FileUploadUtil;

@Controller
@RequestMapping("personne")
public class PersonneController {
	
    private PersonneService personneService;
    private NationaliteService natService;
    private final String UPLOAD_DIR = "/src/main/resources/static/photos/personnes/";

    @Autowired
    public void setCustomerService(PersonneService personneServices) {
        this.personneService = personneServices;
    }
    @Autowired
    public void setNationaliteService(NationaliteService nationaliteServices) {
        this.natService = nationaliteServices;
    }
    @GetMapping
    public String index() {
        return "redirect:/personne/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Personne> page = personneService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listPersonnes", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "personne/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
    	model.addAttribute("listeNationalites",natService.getListAll());
        model.addAttribute("personne", new Personne());
        return "personne/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	model.addAttribute("listeNationalites",natService.getListAll());
        model.addAttribute("personne", personneService.get(id));
        return "personne/form";

    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("file") MultipartFile file, Personne personne, final RedirectAttributes ra) {
    	if(!file.isEmpty()) {
    		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
    		try {
				String uuid=UUID.randomUUID().toString();
				String uploadDir=UPLOAD_DIR;
				FileUploadUtil.saveFile(uploadDir,uuid+fileName,file);
				personne.setPhoto("/photos/personnes/"+uuid+fileName);
			} catch (IOException e) {
				System.out.println("####\nUpload Error:\n"+e);
			}
    	}
        Personne save = personneService.save(personne);
        ra.addFlashAttribute("successFlash", "personne saved successfuly !");
        return "redirect:/personne";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

    	personneService.delete(id);
        return "redirect:/personne";

    }
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        model.addAttribute("personne", personneService.get(id));
        return "personne/details";

    }
    
    @GetMapping("/show/list")
    public String showPersons() {
        return "/personne/listNG";
    }
    
    @GetMapping(path="/api/list", produces = "application/json")
    public @ResponseBody List<Personne> getAllPersons() {
    	List<Personne> allPersons = new ArrayList<Personne>();
    	allPersons = personneService.getListAll();
    	System.out.println("Size of List allPersons : "+allPersons.size());
        return allPersons;
    }
}
