# Command-Driven In-Memory Database

## OverView
This project is a command-driven, in-memory database implemented in Java.
It supports Methods, integer keys, generic values, TTL (time-to-live)

## Features
- String-based Method
- Genric value storage
- Command-based input(PUT, GET, DELETE, START, STOP, EXIT)
- TTL (Time-to-Live) support
- Lazy expiration on GET.
- Background cleanup thread
- Thread-safe operations
- START / STOP database lifecycle

## Project structure

src/main/java
└── org.example
├── entity
│ ├── Command.java
│ ├── CommandType.java
│ └── Entry.java
├── service
│ ├── CommandService.java
│ ├── DbService.java
│ └── IDbservices.java
└── exception
  ├── DbUnavaiableException.java
  ├── InvalidCommandException.java
  └── InvalidCommandexception

---

## Supported command

PUT <key> <value>
PUT <key> <value> <ttlMillis>
GET <key>
DELETE <key>
START
STOP
EXIT

---

## TTL Design 
- TTL is stored as an expiry timestamp: expiryTime = currentTimeMillis + ttlMillis
- Expired entries are:
- Removed lazily on GET
- Removed periodically by a background cleanup thread

---

## Volatile Usages
A `volatile boolean running` flag is used to control database lifecycle
(START / STOP) and ensure visibility across threads.

## Exception Handling
Custom exceptions are used for:
- Invalid commands
- Invalid keys
- Database stopped state
- Invalid TTL values

---

## Sample Exections 
PUT 1 Hello
PUT 2 World 3000
GET 1
DELETE 1
STOP
START
GET 2

## How to Run
1. Clone the repository
2. Open project in IntelliJ IDEA
3. Run `Main.java`
4. Enter commands in console

---

## Key Learnings
- HashMap vs ConcurrentHashMap
- Multithreading and synchronization
- TTL-based expiration design
- Command parsing and validation
- Clean OOP architecture

---

## 👨‍💻 Author
Adesh Kumar  
Java FullStack Developer 

