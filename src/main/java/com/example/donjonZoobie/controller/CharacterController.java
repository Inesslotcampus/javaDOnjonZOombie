package com.example.donjonZoobie.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.donjonZoobie.model.CharacterResponse;
import com.example.donjonZoobie.CharacterDao;
import com.example.donjonZoobie.model.CharacterForm;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class CharacterController {
    private final CharacterDao characterDao;
    @Value("${error.message}")
    private String errorMessage;
    static final String URL_CHARACTER = "http://localhost:8081/api/characters/";
    public RestTemplate restTemplate = new RestTemplate();
    public CharacterController(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
    ;
    @GetMapping("/")
    String getCharacter(Model model) {

        List<CharacterResponse> result = Arrays.asList(restTemplate.getForObject(URL_CHARACTER, CharacterResponse[].class));

        model.addAttribute("characters", result);
        model.addAttribute("characterForm", new CharacterForm());

        return "character";
    }

    @GetMapping("/{id}")
    public String getCharacterById(Model model, @PathVariable("id") int id){

        CharacterResponse result = restTemplate.getForObject(URL_CHARACTER+id,CharacterResponse.class);
            model.addAttribute("characters",result);
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

    }
    @GetMapping("/delete/{id}")
    public void deleteCharacterById( Model model, @PathVariable("id") int id){
        restTemplate.delete(URL_CHARACTER, id);
//    model.addAttribute("characters",characterDao.delete(id));
//return "redirect:/";
    }
}
