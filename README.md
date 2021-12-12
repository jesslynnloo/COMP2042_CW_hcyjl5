# Brick_Destroy
<u>Refactoring</u>:
1. Do some basic refactoring, organize files in meaningful packages, breaking up large classes in a meaningful way so that it adheres to single responsibility and improves the encapsulation.
2. Split the Crack class out from the Brick class.
3. Remove some replicated code and put it in a method so that it is more maintainable and extensible. It was done for the drawText and drawButton method in HighScoreview class, HomeMenuView class, and InfoView class.
4. I also deleted unused resources such as unused fields and methods.
5. Refactor the code so that it adheres to the MVC pattern. I split the classes into three packages, which are model, controller, and view.
6. I've written some JUnit tests for classes of model and controller.
7. I didn't do the JUnit test for view because most of the method in the classes of view displays the GUI components to the user. We can test it by running the application and see if all the components are displayed correctly instead of doing JUnit test.

<u>Addition</u>:
1. I've added simple features such as an info page and background images for home menu, high score page and info page.
2. The ball count is changed to be carried forward to the next level. The score for every level is accumulated until the user loses three balls. The user has to achieve as many levels as he can to get a higher score, rather than resetting the ball count every level.
3. I've made a high score page to display the accumulated score and the high score after the game is over or the user has completed all the levels.
4. The score will be reset to 0 if the user chooses to restart the game.
5. I've saved the score in a file and extract the high score from the file like a mini database.
6. I also made a special brick, which will increase the player face's width instantly by 50 when the user hits the brick. No points will be awarded if the user hit the special brick.
7. I've implemented a timer in the game and the player face's width will be decreased by 20 every 1 minute after the user starts the game.
8. A minimum length is set for the player face to prevent the player face to be too short for the user to play. The minimum length of the player face is 70.
9. After the player face's width is decreased, if the user hits a special brick, the player face's width can still be increased by 50 and will be decreased again based on the timer.
10. The player face's width will be reset when the user proceeds to the next level.
11. I've also added a new brick, wooden brick in the game. It haas 40% probability to be broken when it is hit by the ball.
12. I've made 3 additional levels into the game using the wooden brick and the special brick.