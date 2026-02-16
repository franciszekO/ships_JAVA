# Battleship Game

A simple turn-based strategy game implementation in Java with a Swing GUI.

## Project Description

This is a classic Battleship game where the player tries to sink hidden ships on a 5x5 grid. The logic is handled by a dedicated class, while the interface is built using Java Swing. The game features random ship placement and tracks the number of shots remaining, offering a simple yet engaging strategic challenge.

## Key Features

- **Interactive GUI**: A 5x5 grid of clickable buttons representing the sea.
- **Random Ship Placement**: 3 ships are placed at random coordinates at the start of each game.
- **Turn-Based Gameplay**: Players have a limited number of shots (10) to sink all ships.
- **Visual Feedback**: Buttons change color to **Green** for a HIT and **Red** for a MISS.
- **Win/Loss Conditions**: The game detects when all ships are sunk (Victory) or when shots run out (Defeat).

## Application Preview

### Game Start
The adventure begins with a clean 5x5 grid. You have 10 shots to locate and sink 3 hidden ships.
![Game Start](pending_screenshot_1)

### Gameplay
As you fire, the grid reveals the truth. **Green** marks a hit, bringing you closer to victory, while **Red** indicates a miss, consuming your valuable ammunition.
![Gameplay](pending_screenshot_2)

### Game Over
The conclusion of your mission. A distinct message informs you if you've successfully neutralized the enemy fleet or if you've depleted your resources.
![Game Over](pending_screenshot_3)

### How to Run

1.  **Compile**: 
    ```bash
    javac -d out src/GameWindow.java src/GameLogic.java
    ```
2.  **Run**: 
    ```bash
    java -cp out GameWindow
    ```

## Technologies Used

- **Java** (JDK 17+)
- **Swing** (GUI Framework)
- **IntelliJ IDEA** (development environment)
