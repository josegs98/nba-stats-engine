package com.josegs98.nbastats.cli;

import com.josegs98.nbastats.cli.command.Command;
import com.josegs98.nbastats.cli.command.ExitCommand;
import com.josegs98.nbastats.cli.command.PlayerGameLogsCommand;
import com.josegs98.nbastats.cli.command.SearchPlayerCommand;
import com.josegs98.nbastats.cli.command.UnknownCommand;
import com.josegs98.nbastats.domain.model.GameStats;
import com.josegs98.nbastats.domain.model.Player;
import com.josegs98.nbastats.service.PlayerService;
import com.josegs98.nbastats.service.StatsIngestionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CliRunner implements CommandLineRunner {

    private final PlayerService playerService;
    private final StatsIngestionService statsIngestionService;

    public CliRunner(PlayerService playerService, StatsIngestionService statsIngestionService) {
        this.playerService = playerService;
        this.statsIngestionService = statsIngestionService;
    }

    @Override
    public void run(String... args) {
        try (var scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                var input = scanner.nextLine().trim();
                var command = parseInput(input, scanner);
                running = handleCommand(command, scanner);
            }
        }
        System.out.println("Goodbye! 🏀");
    }

    private void printMenu() {
        System.out.println("\n===== NBA Stats Engine =====");
        System.out.println("1. Search player");
        System.out.println("2. View player game logs");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");
    }

    private Command parseInput(String input, Scanner scanner) {
        return switch (input) {
            case "1" -> {
                System.out.print("Enter player name: ");
                var name = scanner.nextLine().trim();
                yield new SearchPlayerCommand(name);
            }
            case "2" -> {
                System.out.print("Enter player ID: ");
                var playerId = Long.parseLong(scanner.nextLine().trim());
                System.out.print("Enter season (e.g. 2024): ");
                var season = Integer.parseInt(scanner.nextLine().trim());
                yield new PlayerGameLogsCommand(playerId, season);
            }
            case "3" -> new ExitCommand();
            default -> {
                System.out.println("Invalid option. Please try again.");
                yield new UnknownCommand();
            }
        };
    }

    private boolean handleCommand(Command command, Scanner scanner) {
        return switch (command) {
            case SearchPlayerCommand cmd -> {
                handleSearchPlayer(cmd);
                yield true;
            }
            case PlayerGameLogsCommand cmd -> {
                handlePlayerGameLogs(cmd);
                yield true;
            }
            case ExitCommand ignored -> false;
            case UnknownCommand ignored -> true;
        };
    }

    private void handleSearchPlayer(SearchPlayerCommand command) {
        System.out.println("\nSearching for players matching: " + command.playerName());
        List<Player> players = playerService.searchPlayers(command.playerName());
        if (players.isEmpty()) {
            System.out.println("No players found.");
            return;
        }
        System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "First Name", "Last Name", "Position");
        System.out.println("-".repeat(60));
        for (var player : players) {
            System.out.printf("%-8d %-20s %-20s %-10s%n",
                    player.getId(),
                    player.getFirstName(),
                    player.getLastName(),
                    player.getPosition() != null ? player.getPosition() : "-");
        }
    }

    private void handlePlayerGameLogs(PlayerGameLogsCommand command) {
        System.out.printf("%nFetching game logs for player %d (season %d)...%n",
                command.playerId(), command.season());
        List<GameStats> logs = statsIngestionService.getGameLogs(command.playerId(), command.season());
        if (logs.isEmpty()) {
            System.out.println("No game logs found.");
            return;
        }
        System.out.printf("%-12s %-6s %-6s %-6s %-6s %-6s %-8s%n",
                "Date", "PTS", "REB", "AST", "STL", "BLK", "MIN");
        System.out.println("-".repeat(56));
        for (var log : logs) {
            System.out.printf("%-12s %-6d %-6d %-6d %-6d %-6d %-8s%n",
                    log.getGameDate(),
                    log.getPoints(),
                    log.getRebounds(),
                    log.getAssists(),
                    log.getSteals(),
                    log.getBlocks(),
                    log.getMinutesPlayed() != null ? log.getMinutesPlayed() : "-");
        }
    }
}
