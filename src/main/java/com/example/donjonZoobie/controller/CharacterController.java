package com.example.donjonZoobie.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.donjonZoobie.model.CharacterResponse;
import com.example.donjonZoobie.CharacterDao;
import com.example.donjonZoobie.model.CharacterForm;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class CharacterController {
    private final CharacterDao characterDao;
    @Value("${error.message}")
    private String errorMessage;
    static final String URL_CHARACTER = "http://localhost:8081/api/characters/";

    public CharacterController(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
    ;
    @GetMapping("/")
    String getCharacter(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        CharacterResponse[] result =restTemplate.getForObject(URL_CHARACTER,CharacterResponse[].class);
        if (result != null) {
            for (CharacterResponse e : result) {
                System.out.println("Employee: " + e.getName() );
            }
        }

        model.addAttribute("characters", characterDao.findAll());
        model.addAttribute("characterForm", new CharacterForm());

        return "character";
    }

    @GetMapping("/{id}")
    public String getCharacterById(Model model, @PathVariable("id") int id){

model.addAttribute("characters",characterDao.findById(id));
        return "characterById";
    }

    @PostMapping("/save")
    public String savePerson(Model model, @ModelAttribute CharacterForm characterForm) {
        String name = characterForm.getName();
        String type = characterForm.getType();
        int level = characterForm.getLevelLife();

        Long id = characterDao.getMaxId()+1;
        if (name != null && name.length() > 0 //
                && type != null && type.length() > 0
                && level > 0) {
            CharacterResponse characterResponse = new CharacterResponse(id, name, type, level);
            model.addAttribute("characters", characterDao.save(characterResponse));
        }
        return "redirect:/";
//        return "character";
    }
    @GetMapping("/delete/{id}")
    public String deleteCharacterById( Model model, @PathVariable("id") int id){
        model.addAttribute("characters",characterDao.delete(id));
return "redirect:/";
    }
}
