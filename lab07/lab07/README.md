# Lab 07

## Due: Week 8, Sunday, 5:00 pm

**NOTE**: You have two weeks to do this lab

## Aim

* Learn how to implement a simple stateful model.
* Understand JUnit tests.
* Learn how to create basic UIs in JavaFX and connect them to a model.

## JavaFX videos

Please watch these videos related to JavaFX for further information:

[JavaFx_example_Thu_Wk07_Lect](https://d2xnkjysn6lg7q.cloudfront.net/files/unswVideo/sourceVideo/1594362116963-javafx_example_thu_wk07_lect.mp4) (16 mins, the week-06 lecture from 20T2)

[JavaFx_selfstudy_example_video](https://d2xnkjysn6lg7q.cloudfront.net/files/unswVideo/sourceVideo/1594361803294-javafx_selfstudy_example_video.mp4) (17 mins, from 19T3)

[JavaFx_Grade_example_video](https://d2xnkjysn6lg7q.cloudfront.net/files/unswVideo/sourceVideo/1594362656933-javafx_grade_example_video.mp4) (21 mins, **must** watch 10:00 mins onwards, from 19T3) 

[JavaFx_animation_example_video](https://d2xnkjysn6lg7q.cloudfront.net/files/unswVideo/sourceVideo/1594362993025-javafx_animation_example_video.mp4)  (13 mins, from 19T3)

Please make sure to watch "JavaFx_Grade_example_video (10:00 mins onwards)" which discusses **Wrapper** classes, **BooleanProperty** class along with it's **bidirectional** property - example of the Observer pattern. 

## Setup

An individual repository for this lab has been created for you here (replace z5555555 with your own zID):

https://gitlab.cse.unsw.edu.au/z5555555/20T2-cs2511-lab07

This exercise assumes that you've done the **Project setup** steps from the project spec. Once you've done them, you can open the root directory of this project in VSCode and start doing the exercise. These steps may include adding files under the *bin* and *lib* directories, and modifying *.vscode/launch.json* if you are not using a CSE machine.

Note that by installing the *Java Extension Pack* extension in VSCode as required, you will have installed the *Java Test Runner* extension to run tests with clickable "Run Test" links in the files.

On a non-CSE machine, you will need to download the standalone JUnit 5 Platform Console Standalone JAR, from the Maven repository (or another repository), and place it into the *lib* directory, and delete the symbolic link *symlink_junit5*. The link to the Maven repository for this is (download by clicking the link "jar (2.1 MB)" next to "Files"):

https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone/1.7.0-M1

## Preparation

You may find the documentation for the following classes helpful:

* [BooleanProperty](https://openjfx.io/javadoc/11/javafx.base/javafx/beans/property/BooleanProperty.html)
* [GridPane](https://openjfx.io/javadoc/11/javafx.graphics/javafx/scene/layout/GridPane.html)
* [CheckBox](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/CheckBox.html)
* [Timeline](https://openjfx.io/javadoc/11/javafx.graphics/javafx/animation/Timeline.html)
* [KeyFrame](https://openjfx.io/javadoc/11/javafx.graphics/javafx/animation/KeyFrame.html)

## Backend

Consider [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life): a zero-player game. The rules of the game (from Wikipedia) are:

> The universe of the *Game of Life* is an infinite, two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, *alive* or *dead*, (or *populated* and *unpopulated*, respectively). Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:
>
> * Any live cell with fewer than two live neighbours dies, as if by underpopulation.
> * Any live cell with two or three live neighbours lives on to the next generation.
> * Any live cell with more than three live neighbours dies, as if by overpopulation.
> * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
>
> The initial pattern constitutes the *seed* of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed; births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a *tick*. Each generation is a pure function of the preceding one. The rules continue to be applied repeatedly to create further generations.

For this lab you will be implementing a simplified version of this game. Instead of an infinite grid, the grid will be 10x10 with "wrap-around" behaviour such that cells at the edge of the grid are neighbours with cells on the opposite edge; e.g the cell at position `(9,9)` has neighbours `(8,8)`, `(9,8)`, `(0,8)`, `(0,9)`, `(0,0)`, `(9,0)`, `(8,0)`, and `(8,9)`.

Complete the definition of `unsw.automata.GameOfLife` so that it uses a 2D array of `boolean` to store the state of the game. **HINT:** For the `tick()` method, applying the rules in-place won't work; you will need to use a temporary array.

A basic JUnit test has been provided for you in `unsw.automata.GameOfLifeTest`. Make sure the test passes.

Commit your changes for this part of the lab before moving on to the next.

## Observer

Change the `boolean[][]` array in `GameOfLife` to a 2D array of `BooleanProperty` (from the `javafx.beans.property` package). Update the methods so that the class compiles and works correctly (rerun the test!).

Add a method with the signature

```java
public BooleanProperty cellProperty(int x, int y)
```

to the `GameOfLife` class so that the property can be accessed directly.

Uncomment the second test in `GameOfLifeTest` and fix up any needed imports. If your solution is correct, the test should now compile and pass.

Once again, commit your changes for this part of the lab before moving on to the next.

## JavaFX

Use JavaFX SceneBuilder to build a simple user interface for a game of life application.

SceneBuilder is available on the lab machines at `2511 scenebuilder` and on your own computer by download from [here](https://gluonhq.com/products/scene-builder/#download) (to ensure compatibility, it is best to use the Java 11 version of SceneBuilder as we are using Java 11 in this course).

The UI will consist of two buttons and a grid of checkboxes, like this:

![sample UI](screenshot.png)

A checkbox that is ticked corresponds to an alive cell. To create that in SceneBuilder:

* Use a `Pane` as the root element
* Add a `GridPane`.
* Add a button to *tick* the game.
* Add a second button to *play* the game.
* Assign an appropriate `fx:id` to the `GridPane` and *play* button.
* Add *On Action* handlers for both the buttons.
* Set the controller to `unsw.automata.GameOfLifeController`.
* Save it as `GameOfLifeView.fxml` in the `src/unsw/automata` directory.

Don't add the checkboxes in SceneBuilder. That is tedious. There are 100 of them!

Add the basic structure to the `GameOfLifeController` class. **HINT:** Like in the tutorial, use SceneBuilder to generate a skeleton.

You should now be able to run `GameOfLifeApplication` and see your UI. However, there are no checkboxes and neither of the buttons will do anything. To fix the first problen, create an `initialize()` method and use it to add 100 instances of `CheckBox` to the `GridPane`.

You can test your application again to see if all the checkboxes are present and correctly arranged.

Next, you will need to connect the UI to the model, similar to how it was done in the tutorial, and then add handlers for the buttons. More concretely:

* Initialise the model in the constructor for the controller.
* Create bidirectional bindings between the cells in the model and the `selected` property of each corresponding `CheckBox`.
* Add a handler for the tick button that triggers a single tick of the game.

Test your application to make sure the *tick* button behaves correctly.

When the *play* button is pressed the game should *tick* ever 0.5 seconds. Use a `Timeline` to implement the this. Specifically, you will need to:

* Create a `Timeline` in the constructor.
* Add a `KeyFrame` to the `Timeline` that calls the `tick()` method of `GameOfLife` ever 0.5 seconds (you'll need to read the documentation for these classes).
* Set the `Timeline` to repeat indefinitely (once again, see the documentation).
* Add a handler for the play button that, at a minimum:
  * Changes the button text to "Stop".
  * Calls the `play()` method of `Timeline`.

Naturally, clicking the button when it says "Stop" should cause the game to stop. You'll need to implement that too.

## Submission

Make sure that all your work has been pushed to GitLab then submit it with:

```bash
$ 2511 submit lab07
```
