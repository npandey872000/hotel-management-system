# Hotel Management System

A beginner-friendly Java console project for practicing object-oriented programming, collections, input validation, and sorting.

## Features

- View all rooms and see whether each is available or occupied.
- Sort rooms by room number or nightly price.
- Check a guest into an available room.
- Check a guest out and make the room available again.

The project starts with a few sample rooms. It stores information in memory, so changes reset when the program exits.

## Requirements

- Java 8 or later

## Run

Open a terminal in this directory and run:

```sh
javac -d out src/main/java/hotel/management/*.java
java -cp out hotel.management.HotelManagementApp
```

Use the numbered menu to view or sort rooms, check guests in and out, or exit.
