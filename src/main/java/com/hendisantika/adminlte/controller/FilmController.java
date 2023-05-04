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

import com.hendisantika.adminlte.model.Film;
import com.hendisantika.adminlte.model.Personne;
import com.hendisantika.adminlte.service.FilmService;
import com.hendisantika.adminlte.service.GenreService;
import com.hendisantika.adminlte.service.MediaService;
import com.hendisantika.adminlte.service.NationaliteService;
import com.hendisantika.adminlte.service.PersonneService;
import com.hendisantika.adminlte.util.AddActors;
import com.hendisantika.adminlte.util.FileUploadUtil;

@Controller
@RequestMapping("film")
public class FilmController {
	
	@Autowired
	private PersonneService personneService;
	@Autowired
    private NationaliteService natService;
	@Autowired
    private GenreService genreService;
	@Autowired
    private FilmService filmService;
	
	@Autowired
    private MediaService mediaService;
    

	private final String UPLOAD_DIR = "/src/main/resources/static/photos/films/";

    
    @GetMapping
    public String index() {
        return "redirect:/film/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Film> page = filmService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("listFilms", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "film/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
    	List<Personne> listeActeurs = new ArrayList<Personne>();
    	List<Personne> listeRealisateurs = new ArrayList<Personne>();
    	listeActeurs = personneService.getActeurs();
    	listeRealisateurs =personneService.getDirector();
    	
        model.addAttribute("listActeurs", listeActeurs);
        model.addAttribute("listRealisateurs", listeRealisateurs);
        model.addAttribute("film", new Film());
        model.addAttribute("listeNationalites", natService.getListAll());
        model.addAttribute("listeGenres", genreService.getListAll());
        return "film/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
    	List<Personne> listeActeurs = new ArrayList<Personne>();
    	List<Personne> listeRealisateurs = new ArrayList<Personne>();
    	listeActeurs = personneService.getActeurs();
    	listeRealisateurs =personneService.getDirector();
    	model.addAttribute("listeNationalites",natService.getListAll());
    	model.addAttribute("listeGenres",genreService.getListAll());
    	model.addAttribute("listeRealisateurs",listeRealisateurs);
    	model.addAttribute("listeActeurs",personneService.getListAll());
        model.addAttribute("film", filmService.get(id));
        return "film/form";

    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("file") MultipartFile file,Film film, @RequestParam("acteur") String acteur, final RedirectAttributes ra) {
    	if(!file.isEmpty()) {
    		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
    		try {
				String uuid=UUID.randomUUID().toString();
				String uploadDir=UPLOAD_DIR;
				FileUploadUtil.saveFile(uploadDir,uuid+fileName,file);
				film.setCover("/photos/films/"+uuid+fileName);
				List<Personne> acteurs = AddActors.stringToPersonne(acteur, personneService);
            	film.setActeurs(acteurs);
            	filmService.save(film);
			} catch (IOException e) {
				System.out.println("####\nUpload Error:\n"+e);
				e.printStackTrace();
			}
    	}
        ra.addFlashAttribute("successFlash", "film saved successfuly !");
        return "redirect:/film";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

    	filmService.delete(id);
        return "redirect:/film";

    }
    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
    	model.addAttribute("listeMedias",mediaService.getListAll());
        model.addAttribute("film", filmService.get(id));
        return "film/details";

    }
    
    @GetMapping("/show/list")
    public String showFilms() {
        return "/film/list";
    }
    
    @GetMapping(path="/api/list", produces = "application/json")
    public @ResponseBody List<Film> getAllFilms() {
    	List<Film> allFilms = new ArrayList<Film>();
    	allFilms = filmService.getListAll();
    	System.out.println("Size of List allFilms : "+allFilms.size());
        return allFilms;
    }

}
