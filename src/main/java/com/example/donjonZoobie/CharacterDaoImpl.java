package com.example.donjonZoobie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.example.donjonZoobie.model.CharacterResponse;


@Repository
public class CharacterDaoImpl implements CharacterDao {
    public static List<CharacterResponse> characteresResponse = new ArrayList<>();

    static {
        characteresResponse.add(new CharacterResponse(1, "david", "mage",1));
        characteresResponse.add(new CharacterResponse(2, " david","mage", 2));
        characteresResponse.add(new CharacterResponse(3, "david", "mage",3));
    }
    @Override
    public List<CharacterResponse> findAll() {
        return characteresResponse;
    }

    @Override
    public CharacterResponse findById(int id) {
        for (CharacterResponse characterResponse : characteresResponse){
            if (characterResponse.getId() == id){
                return characterResponse;
            }
        }
        return null;
    }
    @Override
    public Long getMaxId(){
         return characteresResponse.stream().map(el -> el.getId()).max(Long::compare).get();
    }

    @Override
    public CharacterResponse save(CharacterResponse characterResponse) {
        characteresResponse.add(characterResponse);
        return characterResponse;
    }

    @Override
    public boolean delete(int id) {
         characteresResponse= characteresResponse.stream().filter(el->el.getId()!=id).collect(Collectors.toList());
        return true;
    }
}
