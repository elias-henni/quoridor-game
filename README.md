# Quoridor Game Setup

Welcome to Quoridor! This README file will guide you through the necessary steps to set up and run the game on your system.

## Prerequisites

Before you can run the Quoridor game, please make sure you have the following:

1. Java Development Kit (JDK) installed on your system.
2. Git installed on your system.

## Getting Started

To set up and run the Quoridor game, please follow these steps:

### Step 1: Create a Folder

1. Create a new folder on your computer where you want to store the game and its libraries.
2. Choose a suitable name for the folder.

### Step 2: Clone the Repository

1. Open a terminal or command prompt.
2. Navigate to the folder you created in Step 1 using the `cd` command.
3. Run the following command to clone the Quoridor repository into the folder:

```
git clone <repository_url>
```
### Step 3: Download JavaFX SDK

1. Open your web browser and go to [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/).
2. Download the JavaFX SDK by selecting the following options:
- OS: Choose your preferred operating system.
- Version: Select **17.0.7**.
- Architecture: Choose the appropriate architecture for your system.
- Type: Select **SDK**.
3. Once the download is complete, locate the downloaded JavaFX SDK file and unzip it.
4. Move the unzipped file to the folder you created in Step 1.

### Step 4: Run the Game

1. Open a terminal or command prompt.
2. Navigate to the folder where you stored the game and JavaFX SDK using the `cd` command.
3. Run the following command to start the Quoridor game:
```
java --module-path ./javafx-sdk-17.0.7/lib --add-modules javafx.controls,javafx.fxml -jar quoridor-game/quoridor-game.jar
```


This command will launch the game and allow you to start playing.

## Conclusion

You have successfully set up and launched the Quoridor game! Enjoy playing and have fun!

This game is from a first year college project so it is possible that there are some bugs.

