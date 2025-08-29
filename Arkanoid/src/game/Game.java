// Tamar Rosenzweig
package game;

import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import geometry_primitives.Velocity;
import listeners.BlockRemover;
import listeners.BallRemover;
import listeners.ScoreTrackingListener;
import listeners.Counter;
import sprites.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.SpriteCollection;
import sprites.Paddle;
import sprites.ScoreIndicator;
import collision_detection.GameEnvironment;
import collision_detection.Collidable;
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * The Game class represents the game environment and manages the game objects.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private biuoop.GUI gui;

    /**
     * Constructs a new Game object with the specified arguments.
     *
     * @param sprites     the SpriteCollection object to be used in the game
     * @param environment the GameEnvironment object to be used in the game
     * @param paddle      the Paddle object to be used in the game
     * @param gui         the GUI object to be used in the game
     */
    public Game(SpriteCollection sprites, GameEnvironment environment, Paddle paddle, GUI gui) {
        this.sprites = sprites;
        this.environment = environment;
        this.paddle = paddle;
        this.gui = gui;
    }

    /**
     * Constructs a new Game object with default values.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Ha", 800, 600);
    }

    /**
     * Adds a Collidable to the game environment.
     *
     * @param c the Collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a Sprite to the game.
     *
     * @param s the Sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new game by creating the blocks, ball, and paddle,
     * and adding them to the game.
     */
    public void initialize() {
        // block listeners and counters
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();
        blockCounter.increase(57);
        ballCounter.increase(3);
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);
        BallRemover ballRemover = new BallRemover(this, ballCounter);
        // score tracking stuff
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        // balls initializing
        Ball ball1 = new Ball(new Point(150, 70), 5, Color.WHITE, new Velocity(2, 2));
        Ball ball2 = new Ball(new Point(100, 200), 5, Color.WHITE, new Velocity(2, 2));
        Ball ball3 = new Ball(new Point(107, 120), 5, Color.WHITE, new Velocity(2, 2));
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
        // screen borders
        Rectangle left = new Rectangle(new Point(0, 0), 20, 600, Color.GRAY); // left
        Rectangle right = new Rectangle(new Point(780, 0), 20, 600, Color.GRAY); // right
        Rectangle top = new Rectangle(new Point(0, 0), 800, 50, Color.GRAY); // gray top
        Rectangle bottom = new Rectangle(new Point(0, 600), 800, 20, Color.GRAY); // bottom
        Block b1 = new Block(left);
        Block b2 = new Block(right);
        Block b3 = new Block(top);
        Block b4 = new Block(bottom);
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
        b4.addToGame(this);
        // implementing the ball remover listener
        b4.addHitListener(ballRemover);
        // paddle initializing
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        this.paddle = new Paddle(keyboard);
        this.paddle.addToGame(this);
        int screenWidth = gui.getDrawSurface().getWidth() - 20;
        for (int i = 12; i >= 1; i--) {
            Block block = new Block(new Rectangle(new Point(screenWidth - i * 50, 200), 50, 20,
                    new Color(242, 140, 40)));
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        for (int i = 11; i >= 1; i--) {
            Block block = new Block(new Rectangle(new Point(screenWidth - i * 50, 220), 50, 20,
                    new Color(127, 0, 255)));
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        for (int i = 10; i >= 1; i--) {
            Block block = new Block(new Rectangle(new Point(screenWidth - i * 50, 240), 50, 20,
                    new Color(63, 0, 255)));
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        for (int i = 9; i >= 1; i--) {
            Block block = new Block(new Rectangle(new Point(screenWidth - i * 50, 260), 50, 20,
                    new Color(0, 150, 255)));
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        for (int i = 8; i >= 1; i--) {
            Block block = new Block(new Rectangle(new Point(screenWidth - i * 50, 280), 50, 20,
                    new Color(147, 197, 114)));
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        for (int i = 7; i >= 1; i--) {
            Block block = new Block(new Rectangle(new Point(screenWidth - i * 50, 300), 50, 20,
                    new Color(218, 112, 214)));
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        scoreIndicator.addToGame(this);
        ball1.setGameEnvironment(environment);
        ball2.setGameEnvironment(environment);
        ball3.setGameEnvironment(environment);
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (blockCounter.getValue() > 0 && ballCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            Color color = Color.BLUE.darker().darker();
            d.setColor(color); // Set the background color to blue
            d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
        }
        gui.close();
    }

    /**
     * Removes the specified Collidable object from the collection of collidables.
     *
     * @param c the Collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes the specified Sprite object from the collection of sprites.
     *
     * @param s the Sprite object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}