package com.tasktracker;

public class TaskCli {
    public static void main(String[] args) {
    if (args.length == 0 || isHelpFlag(args[0])) {
        printHelp();
        return;
        // Shows help when no command is given or the user asks for help
    }

    String command = args[0]; // first token is always the command
    switch (command) {
        case "add":
            if (args.length < 2) {
                System.err.println("Missing description for add.");
                printHelp();
                return;
            }
            System.out.println("Parsed: add \"" + args[1] + "\"");
            System.out.println("Not implemented yet.");
            return;
        case "update":
            if (args.length < 3) {
                System.err.println("Missing id or description for update");
                printHelp();
                return;
            }
            System.out.println("Parsed: update " + args[1] + "\"" + args[2] + "\"");
            System.out.println("Not implemented yet.");
            return;
        case "delete":
            if (args.length < 2) {
                System.err.println("Missing id  for delete");
                printHelp();
                return;
            }
            System.out.println("Parsed: delete " + args[1]);
            System.out.println("Not implemented yet.");
            return;
        case "mark-in-progress":
            if (args.length < 2) {
                System.err.println("Missing id for mark-in-progress.");
                printHelp();
                return;
            }
            System.out.println("Parsed: mark-in-progress " + args[1]);
            System.out.println("Not implemented yet.");
            return;
        case "mark-done":
            if (args.length < 2) {
                System.err.println("Missing id for mark-done.");
                printHelp();
                return;
            }
            System.out.println("Parsed: mark-done " + args[1]);
            System.out.println("Not implemented yet.");
            return;
        case "list":
            if (args.length >= 2) {
                System.out.println("Parsed: list " + args[1]);
            } else {
                System.out.println("Parsed: list");
            }
            System.out.println("Not implemented yet.");
            return;
        default:
            System.err.println("Unknown command: " + command);
            printHelp();
        }
    }

    private static boolean isHelpFlag(String value) {
        if (value == null) {
            return false;
        }
        return value.equals("--help") || value.equals("-h") || value.equals("help");
    }

