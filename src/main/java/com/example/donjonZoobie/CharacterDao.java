package com.example.donjonZoobie;
import com.example.donjonZoobie.model.CharacterResponse;
import java.util.List;
public interface CharacterDao {
    List<CharacterResponse> findAll();
    CharacterResponse findById(int id);
    CharacterResponse save(CharacterResponse characterResponse);
    boolean delete(int id);
    Long getMaxId();
 }
