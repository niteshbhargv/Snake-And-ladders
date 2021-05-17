package com.niteshbhargv.demo.controller;

import com.niteshbhargv.demo.model.City;
import com.niteshbhargv.demo.model.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {


    @Autowired
    private
    GameService gameService;


    @PostMapping("/play")
    private void playgame() throws InterruptedException {
        gameService.playGame();
    }

    @GetMapping("/start")
    private void init() {
        gameService.initGame();
    }
}
