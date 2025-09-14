# Arkanoid Game 🎮

A classic brick-breaker arcade game built in Java with a unique color-matching twist!

## 🎯 Game Overview

Control a paddle to bounce a ball and destroy colored blocks in this modern take on the classic Arkanoid game. The twist? The ball can only destroy blocks of different colors and changes color when it hits a block!

## 🚀 Features

### Core Gameplay
- **Paddle Control**: Move the paddle left and right to keep the ball in play
- **Physics Simulation**: Realistic ball trajectory calculations with collision detection
- **Block Destruction**: Break blocks to earn points and clear the level

### Unique Color Mechanics
- **Color-Based Destruction**: Ball only destroys blocks that have a **different color** than itself
- **Dynamic Color Changing**: When the ball hits a block, it **changes to that block's color**
- **Strategic Gameplay**: Plan your moves to maximize block destruction with color combinations

### Game Systems
- **Scoring System**: Earn points for each block destroyed
- **Multiple Lives**: Start with multiple balls - lose them all and game over
- **Game Over Conditions**: Game ends when all balls are lost

## 🛠️ Technical Implementation

- **Language**: Java
- **Design Pattern**: Object-oriented programming with clean class structure
- **Physics Engine**: Custom collision detection and ball trajectory calculations
- **Game Loop Architecture**: Efficient rendering and update cycles
- **Color Management**: Dynamic color system for ball-block interactions

## 🎮 How to Play

1. **Launch the game** and use arrow keys or mouse to control the paddle
2. **Keep the ball in play** by bouncing it off your paddle
5. **Clear all blocks** to win, or lose all balls for game over

## 🏗️ Game Architecture

The game demonstrates key programming concepts:
- **Object-Oriented Design**: Separate classes for Ball, Paddle, Block, and Game components
- **Collision Detection**: Pixel-perfect collision between game objects
- **State Management**: Tracking ball colors, score, lives, and game state
- **Event Handling**: Responsive controls and game interactions

## 🎨 Color System Logic

The unique color mechanic adds strategic depth:
- Ball starts with an initial color
- Upon collision with a block of a different color:
  - Block is destroyed
  - Ball adopts the block's color
  - Player earns points
- If ball hits a block of the same color:
  - Block remains intact
  - Ball bounces off without destruction

---

*Built as part of Computer Science coursework.*

<p float="left">
  <img src="https://github.com/user-attachments/assets/ba921f35-294c-423a-87b7-1558f549641c"  alt="Screenshot 1" width="1000"/>
</p>
