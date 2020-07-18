## Instruction of priority points:
  *  1 priority point means this is one of the most important function of this project, this project must have this kind of function to satisfy the requirements.
  *  2 priority points means this is a important function that can make the project more flexible and more functional, but this project can still satisfy the requirements without this funtion.
  *  3 priority points means this is a function that would not affect this project a lot, it does not matter if this function is implemented or not.

## Instruction of story points:
  *  1 story point means this is the easiest function to implement (need 3 hours).
  *  2 story points means this function is a littele harder than than 1 story point functions (need 3-6 hours),
  *  3 story points means this function is twice harder than 1 story point functions (need 6-9 hours),
  *  4 story points means this function is one of the most complicated and complex functions to be implemented (need 9-12 hours). 
  


# Themes:
## Mode of Interaction:
* A player can use doors
* A player can use portals
* A player can use exit
* A player can destory enemies(sword, potion)
* A player can trigger floor switchs
## Item functionality:
* A wall can block examples(player, enemy, boulder)
* The player can pick up some items in the map (treasure, key, potion, sword)
## Game play:
* A player can have nice gameplay experience



# Epic User Stories:
* A player can have nice gameplay experience
* The player can pick up some items in the map (treasure, key, potion, sword)
* A wall can block examples(player, enemy, boulder)
* A player can trigger floor switchs
* A player can destory enemies(sword, potion)
* A player can use exit
* A player can use portals
* A player can use doors


## A player can have nice gameplay experience:

1.  As a User, I want to be able to set the goal of the game, so that the player can fight for the goal.  
    Acceptance Criteira:  
    *  After run the program,  
    *  There appears a window,  
    *  The user can choose from several goals (kill all enemies, get all treasure, open all doors, or just get to the exit)

2.  As a User, I want to be able to assign entity in its corresponding x-position and y-position, so that I can make up the entity in the game.  
    Acceptance Criteria:  
    * After run the program,
    * There appears a window,
    * The user can input the positions of sword, treasure, boulders and floor switches
    * The user can input the positions of keys and doors
    * The user can input the positions of invincible potions,
    * The user can input the positions of the player role and enemies,

3.  As a User, I want to be able to decide the game's size, and the entity in the game, so that I can make up the game.  
    Acceptance Criteria:  
    * After run the program,
    * There appears a window,
    * The user can input the number of columns and rows of the game,
    * The user can input the number of entities in this game.
     
4.  As a player, I want to know what do I have(treasure, sword, key, invincible time), so that I can make the next decision.  
    Acceptance Criteria:  
    * When the player is playing the game,
    * The player press " " as an input,
    * The appears a line: "You get x treasure, you get 0/1 sword, you get 0/1 key, you are invincible/ not invincible", 
     
5.  As a player, I want this game has a score system, so that I can know my perfenmance in one single game or round.  
    Acceptance Criteria:  
    * When the player is playing the game,
    * There shows a line: "Your score is xxx",
    
6.  As a player, I want to have some basic goals that can be easily understanded, so that I am able to know what to do for next step.  
    Acceptance Criteria:  
    * When the player is playing the game,
    * If the player press tab,
    * The goal of that game shows as a line : "You need to kill all enemies/ get all treasure/ open all doors/ or just get to the exit",    

## The player can pick up some items in the map (treasure, key, potion, sword)

7.  As a player, I want to get some points when I pick up a treasure, so that can know my perfenmance in one single game or round.  
    Acceptance Criteria:  
    * The treasure is in a square,
    * The player walk through the square containing the treasure,
    * The score of the player increase by 50,
    * The treasure disappears, 

8.  As a player, I want to know when I fail to pick up a sword(because I already have one), so that I don't waste time to try to pick up something.   
    Acceptance Criteria:  
    * The sword is contained in a square,
    * The player already has a sword,
    * The player walk through the sword in the square,
    * The sword won't disappear,
    * There shows a line: "You already have a sword"

9.  As a player, I want to know when I fail to pick up a key(because I already have one), so that I don't waste time to try to pick up something.  
    Acceptance Criteria:  
    * The key is contained in a square,
    * The player already has a key,
    * The player walk through the key in the square,
    * The key won't disappear,
    * There shows a line: "You already have a key"

10. As a player, I want be able to pick up another invincible potion if I am in the 'invincible time', so that I can extend my invincible time.  
    Acceptance Criteria:  
    * The invincible potion is contained in a square,
    * The player is in the 'invincible time', 
    * The player walk through the invincible potion,
    * The invincible potion in the square disappears,
    * There appears a line: "Invincible time!",

11. As a player, I don't want to pick up a sword if I already carrying a sword, so that I can only have one sword at a time.  
    Acceptance Criteria:  
    * The sword is contained in a square,
    * The player already has a sword,
    * The player walk through the sword in the square,
    * The sword won't disappear,

12. As a player, I don't want to pick up a key if I already carrying a key, so that I can only have one key at a time.  
    Acceptance Criteria:  
    * The key is contained in a square,
    * The player already has a key,
    * The player walk through the key in the square,
    * The key won't disappear,

13. As a player, I want the invincible potion only work for a short time, so that I won't be invincible all the time.  
    Acceptance Criteria:  
    * The invincible potion is contained in the square,
    * The player walk through the square containing the invincible potion,
    * The player turns to in the 'invincible time',
    * 10 seconds pass   
    * The player is not in the 'invincible time'.

## A wall can block entities:

14. As a player, I want this game has wall that can block entities movement, so that I can find a way to move.  
    Acceptance Criteria:  
    * The wall is contained in a square,
    * The player is in the adjacent square,
    * The player try to move towards the wall square,
    * The player stays in the old position, have no movement,

## A player can trigger floor switches:

15. As a floor switch, I want to be untriggered when a boulder is pushed off me, so that I can have some effect to the game.  
    Acceptance Criteria:  
    * The floor switch and the boulder are contained in the same square,
    * The floor switch is in the triggered condition
    * The player is in a adjacent square,
    * The player move towards the boulder,
    * The player is standing in the square containing the floor switch,
    * The floor switch is untriggered.

16. As a floor switch, I want to be triggered when a boulder is pushed onto me, so that I can have some effect to the game.  
    Acceptance Criteria:  
    * The floor switch is contained in a square,
    * The boulder is in a adjacent square,
    * The player push the boulder onto the floor switch,
    * The floor switch is triggered.

17. As a player, I want I am unable to push a boulder when there is boulder adjacent to the current boulder, so that I can only push one boulder once.  
    Acceptance Criteria:  
    * Two boulders are contained in two adjacent square 
    * The player try to push a boulder toward another boulder,
    * Both boulders don't move 

## A player can destroy enemies:

18. As a player, I want to get some points when I destory a enemy, so that can know my perfenmance in one single game or round.  
    Acceptance Criteria:  
    * The enemy is in a square,
    * The player is in a adjacent square,
    * The player and the enemy are try to move to the same square,
    * Check if the player is invincible,
    * If the player is invincible,
    * The enemy disappear,
    * The score of the player increase by 20,
    * The player stays in the old square,
    * If the player is not invincible,
    * Check if the player have a sword,
    * If the player have a sword,
    * The number of available hits minus 1,
    * The enemy disappear,
    * The score of the player increase by 20,
    * The player stays in the old square,
    * If the player dont have a sword,
    * The player disappear,
    * There shows a line "You died, game over",
    * There shows a line "Your score is xxx"
    * The window closes,

19. As a player, I want to know the exist enemies in the game, so that I will know if it is safe to do some actions.  
    Acceptance Criteria:  
    * There is a game window,
    * When the player stays in the same position for 2 seconds,
    * In the right-top of the window there is a line"Enemies: xxx"

20. As a player, I want to make the enemy run away from the me if I pick a invincible potion up, so that I will be safer to go to the exit.  
    Acceptance Criteria:  
    * The player move through a square that containing the invincible potion,
    * There shows a line "You are invincible now!",
    * The player become invincible to enemies in the map,  
    * Enemies in the map will run in a against way to run away from the player,   

21. As a player, I want to destory any enemy by one hit if I have a sword, so that I can be safer if destroy the enemy.  
    Acceptance Criteria:  
    * Check if the player have a sword,
    * If the player have a sword,
    * The number of available hits minus 1,
    * The enemy disappear,
    * The score of the player increase by 20,
    * The player stays in the old square,
                            
    * If the player dont have a sword,
    * The player disappear,
    * There shows a line "You died, game over",
    * There shows a line "Your score is xxx"
    * The window closes,

22. As a player, I want my sword disappear after 5 hits, so that I have to find another sword.  
    Acceptance Criteria:  
    * The player has a sword remains one hit,
    * The player collide with a enemy,
    * The number of available hits minus 1,
    * The enemy disappear,
    * The score of player increase by 20,
    * The sword disappear,
    * The player stays in the old square,

23. As a player, I want to the enemy constantly moves toward me, so that the game is more attractive to me.  
    Acceptance Criteria:  
    * The player is in a square in the map,
    * All enemies will walk toward the player constantly,

24. As a player, I want have access to pick up swords that can elimilate enemies, so that I can be safer in the rest of this game.  
    Acceptance Criteria:  
    * The sword is contained in a square,
    * The player walk through the sword,
    * Check if the player have a sword,
    * If the player dont have a sword,
    * The sword in the square disappear,
    * There appear a sword in the inventory of the player,
    * If the player already have a sword,
    * The player stays at the old position and nothing happen,

25. As a player, I want to lost the game if I collide with a enemy, so that the game is more attractive to me.  
    Acceptance Criteria:  
    * The player has no sword in inventory and is not invincible,
    * The player collide with a enemy,
    * The player disappear,
    * There shows a line : "You die, game over",
    * There shows a line : "Your score is xxx",

## A player can use exit

26. As a player, I want this game has a exit, so that I know I complete the game when I pass through it.  
    Acceptance Criteria:  
    * A exit is contained in a square,
    * The player move through the exit,
    * The exit is activated,
    * There appears a line: "You Are the Winner!!",
    * There appears a line: "Your score is xxx", 
    * The window closes

## A player can use portals

27. As a player, I want there are some floor switch, so that I can enable portal or exit to appeal when I trigger the switch.  
    Acceptance Criteria:  
    * A floor switch is contained in a square,
    * A boulder is pushed onto the sqaure that containing the floor switch
    * The floor switch is triggered,
    * Some portals appear,
    * A exit appear,

28. As a player, I want this game has portals that I can teleport between two corresponding portal, so that I don't need to spend a lot of time on the road.  
    Acceptance Criteria:  
    * A portal is contained in a square,
    * A player move through the sqaure containing the portal
    * The player teleport to the corresponding portal and stay in the position,
    * The player won't teleport again if he keep staying in the square containing the portals

## A player can use doors

29. 
As a player, I want this game has keys and I can pick them to open doors, so that I can find a new path after opening the door.  
    Acceptance Criteria:  
    * The key is contained by a square,
    * The player move into the square that containing the key,
    * Check if the player already has a key,
    * The key appear in the inventory if the player dont have a key before,
    * The key in the square disappears, 
    * The player stay in the square and nothing happen if the player already have a key

30. As a player, I want there are doors that can be open by keys I picked, so that I can find a new path after opening the door.  
    Acceptance Criteria:  
    * The door is contained by a square
    * The player in a adjacent square try to move through the square containing the door
    * Check if  the player have a key
    * If the player have a key,
    * Check if the key is the corresponding key to that door,
    * If the key is the corresponding key to the door
    * The player move to the another side of the door
    * the key in the player's inventory disappears,
    * the door change to opening status and remains so,
    * If the key is not corresponding to the door,
    * The player stay in the old position and nothing happen
    * If the player dont have a key,
    * The player stay in the old position and nothing will happen

31. As a player, I want I can only hold one key at anytime, so the game will be more challenging.  
    Acceptance Criteria:  
    * The key is contained by a square,
    * The player move into the square that containing the key,
    * The player stay in the square and nothing happen since the player already have a key