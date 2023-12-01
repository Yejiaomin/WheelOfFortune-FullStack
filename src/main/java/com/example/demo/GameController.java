package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController

public class GameController {
    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @PostMapping("/saveGame")
    @CrossOrigin(origins = "*")
    public String saveGame(@RequestBody Game game) {
        if (game == null) {
            return "The game is invalid";
        }
        game.setDate(LocalDate.now());
        this.gameRepository.save(game);
        return "success";
    }



    @GetMapping("/findAllGames")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Game> findAllGames(@RequestParam int page, @RequestParam int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return gameRepository.findAllByOrderByScoreDesc(pageable).toList();
    }


    @GetMapping("/findGameByUserId")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<Game> findGameByUserId(@RequestParam String userId, @RequestParam int page, @RequestParam int size){
        PageRequest pageable = PageRequest.of(page, size);
        return this.gameRepository.findAllByUserIdOrderByScoreDesc(userId,pageable).toList();
    }

    @GetMapping("/deleteGameById")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public String deleteGameById(@RequestParam Long id){
        this.gameRepository.deleteById(id);
        return "success";
    }

    @GetMapping("/updatePlayerNameByUserId")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public void updatePlayerNameByUserId(@RequestParam String userId, @RequestParam String newPlayerName){
        Iterable<Game> games = this.gameRepository.findByUserId(userId);
        for(Game game: games){
            game.setPlayerName(newPlayerName);
        }
        this.gameRepository.saveAll(games);
    }

}
