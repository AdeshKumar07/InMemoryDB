package org.example;


import org.example.entity.Command;
import org.example.entity.CommandType;
import org.example.exception.InvalidCommandException;
import org.example.exception.InvalidKeyException;
import org.example.service.CommandService;
import org.example.service.DbService;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        DbService dbService = new DbService();
        CommandService commandService = new CommandService();

        String[] commands = {
                "PUT 1 hello 6000",
                "PUT 2 100 3000",
                "GET 1",
                "GET 2",
                "DELETE 1",
                "GET 1",
                "EXIT"
        };


        System.out.println("================================");
        System.out.println(" In-Memory DB Service Started ");
        System.out.println("================================");

        for (String input : commands) {

            try {
                System.out.println("> " + input);

                Command command = commandService.parse(input);

                switch (command.type) {

                    case PUT:
                        if (command.ttl > 0) {
                            dbService.put(
                                    command.key,
                                    parseValue(command.rawValue),
                                    command.ttl
                            );
                        } else {
                            dbService.put(
                                    command.key,
                                    parseValue(command.rawValue)
                            );
                        }
                        System.out.println("OK");
                        break;

                    case GET:
                        Object value = dbService.get(command.key);
                        System.out.println("VALUE = " + value);
                        break;

                    case DELETE:
                        dbService.delete(command.key);
                        System.out.println("DELETED");
                        break;

                    case EXIT:
                        System.out.println("Shutting down DB...");
                        return;

                    case START:
                        System.out.println("DB already running");
                        break;

                    case STOP:
                        System.out.println("DB stopped");
                        break;
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static Object parseValue(String value) {
        try { return Integer.parseInt(value); } catch (Exception ignored) {}
        try { return Double.parseDouble(value); } catch (Exception ignored) {}
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return Boolean.parseBoolean(value);
        }
        return value;
    }
}