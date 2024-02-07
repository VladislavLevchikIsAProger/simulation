# Simulation Project

![image-1](https://github.com/VladislavLevchikIsAProger/simulation/assets/153897612/2dfef6ec-0d86-49dd-9ed2-35352a4dd439)

The point of the project is a step-by-step simulation of a 2D world populated by herbivores and predators. In addition to creatures, the world contains resources (grass), which the herbivores feed on, and static objects with which you can't interact - they just fill the space.The idea for the project was taken from [here](https://zhukovsd.github.io/java-backend-learning-course/Projects/Simulation/)

## About
Functionality implemented in this project: 
  + Ability to create a world from 10x10 to 15x15 in size. All static objects and creatures will be added automatically.
  + The world simulation can be paused at any time.
  + While the simulation is paused, you can add, remove or replace different entities in the world.
  + Grass that has been eaten has a 50% chance to regrow.
  + Creatures can move on any object on the map. They move one cell.
  + For balance, automatic addition of grass is implemented if there is not enough grass in the world.

## Stack
![Static Badge](https://img.shields.io/badge/Java-%23F40D12?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/OOP-%2334567C?style=for-the-badge)

## Requirements
  + Java 17+
  + Apache Maven

## Installation and assembly
1. Clone the repository:
   ```
   git clone https://github.com/VladislavLevchikIsAProger/simulation.git
   ```
2. Navigate to the project catalog:
   ```
   cd simulation 
   ```
3. Run the build using Maven:
   ```
   mvn clean install
   ```
## Launching the application
1. Navigate to the directory of the built application:
   ```
   cd target
   ```
2. Run the JAR file:
   ```
   java -jar Simulation-1.0-SNAPSHOT.jar
   ```
## Communication
My Telegram - https://t.me/IamNotARapperr
