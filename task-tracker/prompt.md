# Plan: Task-Tracker Java CLI Setup & Implementation

**TL;DR:** Set up a Maven-based Java 17 project with standard directory structure. Build an executable task management CLI with a shell wrapper script. Implement the core logic in phases: task model → JSON persistence → command parsing → individual commands.

## Steps

1. **Create Maven project structure**
   - Create `src/main/java/com/tasktracker/TaskCli.java` (main entry point)
   - Create `src/main/java/com/tasktracker/Task.java` (task model)
   - Create `src/main/java/com/tasktracker/TaskManager.java` (command handler)
   - Create `src/main/java/com/tasktracker/TaskStorage.java` (JSON file handling)
   - Create `pom.xml` with Java 17 compiler configuration

2. **Create shell script wrapper**
   - Create `task-cli` shell script in project root that compiles and runs the Java code
   - Make it executable and handle `./task-cli <command> [args]` invocation

3. **Implement Task model** (`Task.java`)
   - Fields: `id`, `description`, `status` (enum: TODO, IN_PROGRESS, DONE), `createdAt`, `updatedAt`
   - Getters/setters and toString method
   - Constructor for new tasks

4. **Implement JSON persistence** (`TaskStorage.java`)
   - Manual JSON parsing/generation (no external libraries)
   - Read `tasks.json` from current directory
   - Create file if missing
   - Handle parse errors gracefully
   - Methods: `loadTasks()`, `saveTasks(List<Task>)`, `getNextId()`

5. **Implement command parsing** (`TaskCli.java` main method)
   - Parse positional arguments (first arg = command)
   - Route to `TaskManager` methods
   - Handle unknown commands and argument validation

6. **Implement command handlers** (`TaskManager.java`)
   - `add(description)` - create task with auto-incremented ID
   - `update(id, newDescription)` - modify existing task
   - `delete(id)` - remove task
   - `markInProgress(id)`, `markDone(id)` - change status
   - `listTasks(filter)` - return filtered list (all/todo/in-progress/done)
   - Print formatted output for each command

7. **Test and refine**
   - Build with Maven
   - Test each command via shell wrapper
   - Verify JSON file creation and updates
   - Handle edge cases (invalid IDs, missing args, etc.)

## Verification

- Build: `mvn clean package` completes successfully
- Run: `./task-cli add "Test task"` creates task and outputs ID
- Verify: `tasks.json` created in current directory with proper JSON structure
- All 7 commands (add, update, delete, mark-in-progress, mark-done, list, list with filters) work as documented

## Decisions

- Chose Maven for standard Java project structure and easier build management
- Java 17 enables modern features (records, sealed classes if needed)
- Shell wrapper allows `./task-cli` usage matching README examples perfectly
- Manual JSON handling avoids external dependencies per requirements

## Implementation Order

1. Create Maven pom.xml with Java 17 compiler target
2. Create directory structure: `src/main/java/com/tasktracker/`
3. Implement `Task.java` (model)
4. Implement `TaskStorage.java` (JSON I/O)
5. Implement `TaskManager.java` (business logic)
6. Implement `TaskCli.java` (CLI argument parsing & entry point)
7. Create `task-cli` shell script wrapper
8. Test all commands
