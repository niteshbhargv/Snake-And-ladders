package com.niteshbhargv.demo.model.service;

import com.niteshbhargv.demo.model.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Data
@Service
public class GameService {

    @Autowired
    BoardConfiguration board;

    @Value("${game.players}")
    Integer players;

    @Value("${playerNames}")
    List<String> playerNames;

    @Value("${game.dice}")
    private Integer diceCount;

    List<Snake> snakesList;
    List<Ladder> ladders;

    Deque<Player> playerList;

    @Bean
    public void initGame() {
        System.out.println("initializing game");
        snakesList = new ArrayList<>();
        ladders = new ArrayList<>();
        playerList = new ArrayDeque<>();

        for (int i = 0; i < players; i++) {
            playerList.add(Player.builder()
                    .name(playerNames.get(i))
                    .position(1)
                    .won(false)
                    .build());
        }

        Set<String> slSet = new HashSet<>();
        for (int i = 0; i < board.getSnakes(); i++) {
            while (true) {
                int snakeStart = (int) (Math.random() * board.getSize() + board.getStart());
                int snakeEnd = (int) (Math.random() * board.getSize() + board.getStart());
                if (snakeEnd >= snakeStart)
                    continue;
                String startEndPair = String.valueOf(snakeStart) + snakeEnd;
                if (!slSet.contains(startEndPair)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakesList.add(snake);
                    slSet.add(startEndPair);
                    break;
                }
            }
        }
        for (int i = 0; i < board.getLadders(); i++) {
            while (true) {
                int ladderStart = (int) (Math.random() * board.getSize() + board.getStart());
                int ladderEnd = (int) (Math.random() * board.getSize() + board.getStart());
                if (ladderEnd <= ladderStart)
                    continue;
                String startEndPair = String.valueOf(ladderStart) + ladderEnd;
                if (!slSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.add(ladder);
                    slSet.add(startEndPair);
                    break;
                }
            }
        }
    }

    int getNewPosition(int newPosition, Player player) {
        for (Snake snake : snakesList) {
            if (newPosition == snake.getMouth()) {
                System.out.println("snake attack: " + player.getName());
                newPosition = snake.getTail();
                return newPosition;
            }
        }
        for (Ladder ladder : ladders) {
            if (newPosition == ladder.getEntry()) {
                System.out.println( player.getName() + " used ladder");
                return ladder.getExit();
            }
        }
        return newPosition;
    }


    public void playGame() throws InterruptedException {
        while (true) {
            TimeUnit.MILLISECONDS.sleep(100);
            Player player = playerList.poll();
            Dice dice = new Dice(diceCount);
            int val = dice.roll();
            int newPosition = player.getPosition() + val;
            if (newPosition > board.getSize()) {
                player.setPosition(player.getPosition());
                if(val == 6) {
                    System.out.println("player: " + player.getName() + " got 6 continuing chance");
                    playerList.addFirst(player);
                } else {
                    playerList.offer(player);
                }
            } else {
                player.setPosition(getNewPosition(newPosition, player));
                if (player.getPosition() == board.getSize()) {
                    player.setWon(true);
                    System.out.println("Player " + player.getName() + " Won.");
                } else {
                    System.out.println("Setting " + player.getName() + "'s new position to " + player.getPosition());
                    playerList.offer(player);
                }
            }
            if(playerList.size()==1) {
                break;
            }
        }
    }
}
