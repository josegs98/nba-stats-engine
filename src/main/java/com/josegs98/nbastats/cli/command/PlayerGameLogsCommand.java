package com.josegs98.nbastats.cli.command;

public record PlayerGameLogsCommand(Long playerId, int season) implements Command {
}
