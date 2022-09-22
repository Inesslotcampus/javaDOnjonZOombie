package com.example.donjonZoobie.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.donjonZoobie.model.CharacterResponse;
import com.example.donjonZoobie.CharacterDao;
import com.example.donjonZoobie.model.CharacterForm;

import java.util.List;

@Controller
public class CharacterController {
    private final CharacterDao characterDao;
    @Value("${error.message}")
    private String errorMessage;

    public CharacterController(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
    ;

    @GetMapping("/")
    String getCharacter(Model model) {
        model.addAttribute("characters", characterDao.findAll());
        model.addAttribute("characterForm", new CharacterForm("test", "type", 6));
        return "character";
    }

    @RequestMapping(value={"/save"},method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute CharacterForm characterForm) {

        String name = characterForm.getName();
        String type = characterForm.getType();
        int level = characterForm.getLevelLife();
        int id = 5;

        if (name != null && name.length() > 0 //
                && type != null && type.length() > 0
                && level > 0) {
            CharacterResponse characterResponse = new CharacterResponse(id, name, type, level);
            model.addAttribute("characters", characterDao.save(characterResponse));

        }
        return "redirect:/";
//        return "character";
    }
}
