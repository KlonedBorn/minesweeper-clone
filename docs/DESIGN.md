# Minesweeper Design Document

## Overview

Minesweeper is a classic game where the player uncovers cells on a grid to reveal numbers indicating the count of neighboring mines. The objective is to uncover all cells without hitting a mine.

## Implementation Details

### Data Structure

The minefield is represented by a 1D int array. Each element of the array corresponds to a cell in the grid, and the values represent different states:

- `0`: No mines nearby in the 8 neighboring cells.
- `1-8`: Number of mines in the 8 neighboring cells.
- `9`: A mine.

### Coordinate Mapping

To convert 2D coordinates to 1D array index, the following formula is used:

```java
index = row * columns + column;
Where row is the row number, column is the column number, and columns is the number of columns in the grid.

## User Interface (UI)
The UI is implemented using JavaFX, with each cell of the minefield represented as a button. Clicking on a button reveals its content based on the minefield state.

Game Logic
Initialization: The minefield is generated with random mine placements.
Cell Click Handling: When a cell is clicked, the game logic determines the state of the clicked cell and updates the UI accordingly.
Mine Sweeping: If a cell with no neighboring mines is clicked, the adjacent cells are recursively revealed.
Game End Conditions: The game ends when a mine is clicked (player loses) or when all non-mine cells are revealed (player wins).
Flags and Marking
Players can flag cells they suspect contain mines. Right-clicking a cell toggles the flag. This feature helps in strategic gameplay.

Remaining Mines Counter
A counter on the UI keeps track of the number of mines remaining, allowing the player to gauge their progress.

Future Enhancements
Timer: Implement a timer to track the duration of the game.
Difficulty Levels: Allow users to choose different difficulty levels, adjusting the grid size and number of mines accordingly.
High Scores: Keep track of the fastest completion times for different difficulty levels.
Dependencies
JavaFX for the user interface.
Standard Java libraries for game logic.
Running the Application
To run the Minesweeper application, follow these steps:

Clone the repository.
Open the project in your favorite Java IDE.
Compile and run the project.
Have fun playing Minesweeper!
```
