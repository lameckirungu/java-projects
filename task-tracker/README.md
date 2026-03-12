# Task Tracker

Task tracker is a project used to track and manage your tasks. Here, I build a simple CLI to track what I need to do, what I have already done, and what I'm currently working on. This will help me practice Java programming skills, including working with the filesystem, handling user inputs, and building a simple CLI application.

## Requirements

The app should run from the command line, accept user actions and inputs as arguments, and store the tasks in a JSON file. The user should be able to:

- Add, Update and Delete tasks
- Mark a task as in progress or done
- List all tasks
- List all tasks that are done
- List all tasks that are not done
- List all tasks that are in progress

### Constraints

- Use positional arguments in cmd line to accept user inputs
- Use a JSON file to store the tasks in the current directory
- The JSON file should be created if it doesn't exist
- Use the native file system module of the programming language to interact with the JSON file.
- Do not use any external libraries or frameworks to build this project.
- Ensure to handle errors and edge cases gracefully.

### Examples of a list of commands and their usage

```bash
# Adding a new task
task-cli add "Buy groceries"
# 3xpected output: Task added successfully (ID: 1)

# Updating and deleting tasks
task-cli update 1 "Buy groceries and cook dinner"
task-cli delete 1

# Marking a task as in progress or done
task-cli mark-in-progress 1
task-cli mark-done 1

# Listing all tasks
task-cli list

# Listing tasks by status
task-cli list done
task-cli list todo
task-cli list in-progress
```

### Task Properties

Each task should have the following properties

- `id`: A unique identifier for the task
- `description`: A short description of the task
- `status`: The status of the task (`todo`, `in-progress`, `done`)
- `createdAt`: The date and time when the task was created
- `updatedAt`: The date and time when the task was last updated