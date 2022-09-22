package com.example.donjonZoobie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
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
    public CharacterResponse save(CharacterResponse characterResponse) {
        characteresResponse.add(characterResponse);
        return characterResponse;
    }
}
