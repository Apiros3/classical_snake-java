# Usage

This repo consists of a simple Java Snake Game that can run on most Java environments.

## Installation

Simply downloading the jar file is enough to play the game, but if you want to adjust game settings (like the size of the board / speed of the snake) please follow below:

```java
//Under main/MainGame.java lines 27-29
    height = 20;
    width = 30;
    int refresh_speed = 90;
```
Update the three values to whatever is preferred. Height/width are the number of "blocks" you see on screen, and the refresh_speed is the number of milliseconds the computer waits before updating its position.

If you want to make this into a jar file again, simply type the following in the main directory:

```bash
jar cmf manifest.mf snake-game.jar ./
jar tf snake-game.jar 
```

The second line is optional, and simply allows for a check that the necessary files are there. The jar file will be created in the main folder, to not override the one in the root directory. 

## Contributing

Pull requests are always welcome.
For major changes (if any exist) however, please open an issue first.
