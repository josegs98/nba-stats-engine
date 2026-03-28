package com.josegs98.nbastats.cli.command;

public sealed interface Command permits SearchPlayerCommand, PlayerGameLogsCommand, ExitCommand, UnknownCommand {
}
