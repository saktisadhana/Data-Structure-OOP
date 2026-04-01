# Report Assignment 2

## Table of Contents

- [Report Assignment 2](#report-assignment-2)
- [Identity](#identity)
- [Reporting](#reporting)
  - [Case Description](#case-description)
  - [Class Diagram](#class-diagram)
  - [Java Code](#java-code)
    - [App](#app)
    - [User](#user)
    - [Player](#player)
    - [Admin](#admin)
    - [Team](#team)
    - [Leaderboard](#leaderboard)
    - [Challenge](#challenge)
    - [Attachment](#attachment)
    - [ZipAttachment](#zipattachment)
    - [FileAttachment](#fileattachment)
    - [WebsiteLinkAttachment](#websitelinkattachment)
    - [Connection](#connection)
    - [WebExChallenge](#webexchallenge)
    - [OsintChallenge](#osintchallenge)
    - [MiscChallenge](#miscchallenge)
    - [CryptoChallenge](#cryptochallenge)
    - [PwnChallenge](#pwnchallenge)
    - [RevEngChallenge](#revengchallenge)
    - [ForensicChallenge](#forensicchallenge)
    - [Submission](#submission)
  - [Output](#output)
  - [Individuality](#individuality)
## Identity


| Nama                     | NRP        | Class   |
| ------------------------ | ---------- | ------- |
| Putu Putra Sakti Sadhana | 5027251101 | Class A |

## Reporting

### Case Description

I was tasked to find a case or a problem around me that can be solved using the OOP paradigm. For this assignment i decided to make a pretty comprehensive CTF platform.

### Class Diagram

![Class Diagram](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/ADiagram_Assignment-2.svg)

Based on the instruction given, the diagram above was made using `Mermaid.AI` which i must admit is a pretty powerful AI that is capable of making any diagram based on the prompt that is given!

### Java Code

For this section I'll split up the code into multiple section (Mainly into their own respective classes)
I've also changed the code structure, in order for it to be readable in the report! But don't worry besides that i haven't changed anything! (The logic, variable name, class name, etc!!)
#### App

```java
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); 

        // mock data cuz im running out of time
        List<Challenge> challengeList = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        Leaderboard mainLeaderboard = new Leaderboard("test", 24);

        // hard coded cuz running out of time ;-;
        Admin testAdmin = new Admin("000", "admin", "admin@test.com", 0);
        Player testPlayer = new Player("001", "player", "player@test.com");
        playerList.add(testPlayer);
        mainLeaderboard.addPlayerToLeaderboard(testPlayer);
        boolean isAppRunning = true;
```

At this section of the code i invoke many of the variables that are needed for the programs menu. As you can see there's:

```java
List<Challenge> challengeList = new ArrayList<>();
```

Which is needed for the admin and user to be able to find the all of the CTF challenges.
The same can be applied to the other list 

```java
List<Player> playerList = new ArrayList<>();
```

Where this time this is needed so that all of the registered players shows up!
But because im running out of time...

```java
Admin testAdmin = new Admin("000", "admin", "admin@test.com", 0);
Player testPlayer = new Player("001", "player", "player@test.com");
```

I've added some hardcoded user to choose from, which is Admin & Player. These two users have their own completely unique menu panels, which i'll explain below!!! But before that 

```java
playerList.add(testPlayer);
mainLeaderboard.addPlayerToLeaderboard(testPlayer);
```

I added the `testPlayer` to the player list and leaderboard! Now onto the main menu:

```java
System.out.println("==========================================================");
System.out.println("                                                           \r\n" + //
                        " @@@@@@@  @@@@@@@@  @@@@@@@  @@@@@@@@  @@@@@@@@  @@@@@@@   \r\n" + //
                        "@@@@@@@@  @@@@@@@@  @@@@@@@  @@@@@@@@  @@@@@@@@  @@@@@@@@  \r\n" + //
                        "!@@       @@!         @@!    @@!       @@!       @@!  @@@  \r\n" + //
                        "!@!       !@!         !@!    !@!       !@!       !@!  @!@  \r\n" + //
                        "!@!       @!!!:!      @!!    @!!!:!    @!!!:!    @!@@!@!   \r\n" + //
                        "!!!       !!!!!:      !!!    !!!!!:    !!!!!:    !!@!!!    \r\n" + //
                        ":!!       !!:         !!:    !!:       !!:       !!:       \r\n" + //
                        ":!:       :!:         :!:    :!:       :!:       :!:       \r\n" + //
                        " ::: :::   :: ::::     ::     :: ::::   :: ::::   ::       \r\n" + //
                        " :: :: :  : :: ::      :     : :: ::   : :: ::    :        \r\n" + //
                        "                                                           ");
    while (isAppRunning) {
        System.out.println("==========================================================");
        System.out.println("> Welcome to CeTeEp, test your skillzz here...");
        System.out.println("> Before that plz login :3");
        System.out.println("> [1] : Login as Admin (" + testAdmin.getUsername() + ")");
        System.out.println("> [2] : Login as Player (" + testPlayer.getUsername() + ")");
        System.out.println("> [3] : EXIT");
        System.out.print("> ");
        int loginChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the "Enter" key leftover in the scanner buffer
        if (loginChoice == 3) {
            isAppRunning = false;
            System.out.println("> Exiting...");
            break;
        }
```

So the snippet of code above will be the first thing that'll be shown!! And again for simplicity the program for now can only log on into two existing account. 

```java
System.out.println("> [1] : Login as Admin (" + testAdmin.getUsername() + ")");
System.out.println("> [2] : Login as Player (" + testPlayer.getUsername() + ")");
```

In a real CTF platform, of course people will be able to create their own account.
To keep the menu looping, i choose to do a combination of while, if statements, and some switch cases. If i were to map the how the menu works in this program the diagram will roughly be like this :

```
Main Menu < --- While Loop
=   > Admin Panel < --- If Statements
	=   > Create a new Challenge < --- Switch Cases
		< Challenge Category is encapsulated by a While Loop!!
		=   > Webex < --- Switch Cases
			=   > Inputting Challenge Details
		=   > OSINT < --- Switch Cases
			=   > Inputting Challenge Details
		=   > Misc < --- Switch Cases
			=   > Inputting Challenge Details
		=   > Crypto < --- Switch Cases
			=   > Inputting Challenge Details
		=   > Pwn < --- Switch Cases
			=   > Inputting Challenge Details
		=   > Reveng < --- Switch Cases
			=   > Inputting Challenge Details
		=   > Forens < --- Switch Cases
			=   > Inputting Challenge Details
	=   > View all players < --- Switch Cases
	=   > View Leaderboard < --- Switch Cases
	=   < Back
=   > Player Panel < --- If Statements
	=   > View active challenges < --- Switch Cases
    =   > Submit a flag to a challenge < --- Switch Cases
    =   > View my profile and points < --- Switch Cases
    =   < Back
=   < Back
```

Now all of that is clear now onto the next section which is a snippet of code for the creation of challenges:

```java
if (loginChoice == 1) { 
    boolean inAdminPanel = true;
    while (inAdminPanel) {
        System.out.println("==========================================================");
        System.out.println("> [1] Create a new challenge");
        System.out.println("> [2] View all players");
        System.out.println("> [3] View Leaderboard");
        System.out.println("> [0] Back");
        System.out.println("==========================================================");
        System.out.print("> ");
        int adminChoice = scanner.nextInt();
        scanner.nextLine();
```

For the section where you can choose the category of the newly created challenge. I chose to have a switch case, as for the variable to check what the input is, i choose to name the input as `addingChallenges`

```java
switch (adminChoice) {
    case 1:
        boolean addingChallenges = true;
        while (addingChallenges) {
            System.out.println("==========================================================");
            System.out.println("Select Challenge Type:");
            System.out.println("> [1] Webex");
            System.out.println("> [2] OSINT");
            System.out.println("> [3] Misc");
            System.out.println("> [4] Crypto");
            System.out.println("> [5] Pwn");
            System.out.println("> [6] Reveng");
            System.out.println("> [7] Forens");
            System.out.println("==========================================================");
            System.out.print("> "); int typeChoice = scanner.nextInt(); scanner.nextLine();
```

Here i choose to make 7 separate category for all of the challenges, this is following the diagrams that i got!! And of course also based on the diagram the parent class for all of this challenge category is  called `Challenge`


```java
while (typeChoice < 1 || typeChoice > 7) {
    System.out.println("Invalid type entered. Please pick from 1-7.");
    System.out.print("> ");
    typeChoice = scanner.nextInt(); scanner.nextLine();
}
```

The snippets of code above is use to prevent the input that is not 1 thru 7! It'll loop this code over and over again! 

```java
System.out.print("> Enter title: ");
String title = scanner.nextLine();
System.out.print("> Enter the Description: ");
String desc = scanner.nextLine();
System.out.print("> Enter Flag: ");
String flag = scanner.nextLine();
String challId = "C" + (challengeList.size() + 1); // challenge id
Attachment webTest = new WebsiteLinkAttachment("test.com"); // dummy domain
Challenge newChall = null;
```

So the logic of making a challenge in the program, you first need to specify:

```java
System.out.print("> Enter title: ");
String title = scanner.nextLine();
```

The title of the challenge, the input is then saved to a String variable named `title`. Then the same is done to other field such as:

```java
System.out.print("> Enter the Description: ");
String desc = scanner.nextLine();
```

The description!!

```java
System.out.print("> Enter Flag: ");
String flag = scanner.nextLine();
```

And of course the most important field, the flag of the level! After that

```java
String challId = "C" + (challengeList.size() + 1); // challenge id
```

We set the challenge ID like this!

| Identifier | Index + 1 |
| ---------- | --------- |
| C          | 1         |

I also did a couple of stuff in the code:

```java
Attachment webTest = new WebsiteLinkAttachment("test.com"); // dummy domain
Challenge newChall = null;
```

In here i choose to invoke a variable named webTest that is the type of `Attachment`, this is needed for any  because all challenges needs it's own attachment, for now i am setting that every challenge has the webTest attachment. I also invoke a new variable named `newChall` of the type `Challenge`, then i set the value of the variable as null!

```java
switch (typeChoice) {
    case 1:
        System.out.println("==========================================================");
        System.out.print("> Enter Vulnerability Type (e.g. XSS, SQLi): ");
        String vuln = scanner.nextLine();
        newChall = new WebExChallenge(challId, title, desc, 500, flag, webTest, vuln);
        System.out.println("==========================================================");
        break;
```

For WebEx following the diagram the program ask for the vuln type!

```java
    case 2:
        System.out.println("==========================================================");
        System.out.print("> Enter Target Subject: ");
        String subject = scanner.nextLine();
        System.out.print("> Enter Starting Asset: ");
        String asset = scanner.nextLine();
        newChall = new OsintChallenge(challId, title, desc, 500, flag, webTest, subject, asset);
        System.out.println("==========================================================");
        break;
```

For OSINT following the diagram, the program ask for the Subject and the Starting Asset

```java
    case 3:
        System.out.println("==========================================================");
        System.out.print("> Enter Attachment File Name: ");
        String file = scanner.nextLine();
        newChall = new MiscChallenge(challId, title, desc, 500, flag, webTest, file);
        System.out.println("==========================================================");
        break;
```

The code above is for the class `MiscChallenge` and following the diagram it is asking for the attachment file name

```java
    case 4:
        System.out.println("==========================================================");
        System.out.print("> Enter Ciphertext: ");
        String cipher = scanner.nextLine();
        System.out.print("> Enter Host Address: ");
        String cryptoHost = scanner.nextLine();
        System.out.print("> Enter Port Number: ");
        int cryptoPort = scanner.nextInt();
        scanner.nextLine();
        newChall = new CryptoChallenge(challId, title, desc, 500, flag, webTest, cipher, cryptoHost, cryptoPort);
        System.out.println("==========================================================");
        break;
```

Comparedd to other challenges, only Crypto and Pwn that can connect to a server, that is why the program is asking for an address and a port number. This is 1:1 to the diagram.

```java
    case 5:
        System.out.println("==========================================================");
        System.out.print("> Enter LibC Version: ");
        String libc = scanner.nextLine();
        System.out.print("> Enter Host Address: ");
        String pwnHost = scanner.nextLine();
        System.out.print("> Enter Port Number: ");
        int pwnPort = scanner.nextInt();
        scanner.nextLine();
        newChall = new PwnChallenge(challId, title, desc, 500, flag, webTest, libc, pwnHost, pwnPort);
        System.out.println("==========================================================");
        break;
```

The cpde above is for the class `PwnChallenge`, it's also asking for a Host Address and port number!

```java
    case 6:
        System.out.println("==========================================================");
        System.out.print("> Enter Target Architecture: ");
        String arch = scanner.nextLine();
        newChall = new RevEngChallenge(challId, title, desc, 500, flag, webTest, arch);
        System.out.println("==========================================================");
        break;
```

Meanwhile for the class `RevEngChallenge` it only ask for the Target Architecture, this is also following the diagram.

```java
    case 7:
        System.out.println("==========================================================");
        System.out.print("> Enter Target MD5 Hash: ");
        String md5 = scanner.nextLine();
        newChall = new ForensicChallenge(challId, title, desc, 500, flag, webTest, md5);
        System.out.println("==========================================================");
        break;
```

Meanwhile this section is for the class `ForensicChallenge` where their unique attribute is `md5Hash`.

```java
    default:
        System.out.println("==========================================================");
        System.out.println(" > Invalid type entered");
        System.out.println("==========================================================");
        break;
}
```

This is the default case of the switch case!

```java
if (newChall != null) {
    testAdmin.createChallenge(newChall);
    challengeList.add(newChall);
    System.out.println("> Challenge Added Successfully! ID: " + challId);
}
System.out.print("> Add another challenge? (y/n): ");
String response = scanner.nextLine();
if (!response.equalsIgnoreCase("y")) {
    addingChallenges = false;
}
}
	break;
```

After succesfully creating the program, it'll ask if it wants to create another challenge. If n was inputted the program will set the value of addingChallenges to false which inturn stop the loop of the challenge creation menu!

```java
    case 2:
        System.out.println("==========================================================");
        System.out.println("> Player list:");
        for (Player p : playerList) {
            System.out.println("> Username: " + p.getUsername() + " | Points: " + p.getTotalPoints());
        }
        System.out.println("==========================================================");
        break;
```
Now for case 2 of the Admin panel menu, it's purpose is to show all of the players registered in the program. But because registration is not yet implemented it'll only show the dummy acoount that have been created!

How it shows the player list is by using a for loop and looping how many players are in the variable / list `playerList`
```java
    case 3:
        System.out.println("==========================================================");
        System.out.println("> CTF LEADERBOARD");
        mainLeaderboard.sortPlayerLeaderboard(); // sort the leaderboard :3
        for (Player p : playerList) { 
            System.out.println("> " + p.getUsername() + " : " + p.getTotalPoints() + " pts");
        }
        System.out.println("==========================================================");
        break;
```
Case 3 is where the player leaderboard are located! For now i only implemented a player leaderboard and not a team leaderboard, because im running out of time! As you can see

The program calls
``` java
mainLeaderboard.sortPlayerLeaderboard();
```

to sort the leaderboard in a descending order!

```java
    case 0:
        System.out.println("> Logging out...");
        inAdminPanel = false;
        break;
```

And following the main menu theme where 0 is use to go back to the last menu, i set the value for the variabel of `inAdminPanel` to false, in order to end the menu loop!

```java
    default:
        System.out.println("> Invalid option.");
}
}
}
```

And because switch cases needs to have a default case when the input value is not 1-3 nor 0 i decided to put a simple println

```java
else if (loginChoice == 2) { 
    boolean inPlayerPanel = true;
    Player activePlayer = testPlayer;
```

Before that i set the current active player as the dummy account that i've made before, in a real CTF Platform the newly registered account will be the active player.

```java
    while (inPlayerPanel) {
        System.out.println("==========================================================");
        System.out.println(" > Welcome " + activePlayer.getUsername() + " !");
        System.out.println("> [1] View active challenges");
        System.out.println("> [2] Submit a flag to a challenge");
        System.out.println("> [3] View my profile and points");
        System.out.println("> [0] Back");
        System.out.println("==========================================================");
        System.out.print("> ");
        int playerChoice = scanner.nextInt();
        scanner.nextLine();
```

This is the main menu for the Player Panel! As a player youll only be able to view and solve a challenge and ofcourse to see you stats! 

```java
    switch (playerChoice) {
        case 1:
            System.out.println("==========================================================");
            System.out.println("> Active Challenges");
            if (challengeList.isEmpty()) {
                System.out.println("> No challenges available yet.");
            } else {
                for (Challenge c : challengeList) {
                    System.out.println("> [" + c.getChallengeId() + "] " + c.getTitle() + " (" + c.getMaxPoints() + " points)");
                }
            }
            System.out.println("==========================================================");
            break;
```

For case 1 the player can see all of the challenge that is available, at the moment it only shows the Id, name / title of the challenge, and how many points it'll give if solved!!

```java
        case 2:
            if (challengeList.isEmpty()) {
                System.out.println("==========================================================");
                System.out.println("> No challenges!");
                System.out.println("==========================================================");
                break;
            }
            System.out.println("==========================================================");
            System.out.print("> Enter the Challenge ID: ");
            String targetId = scanner.nextLine();
            Challenge selectedChall = null;
            for (Challenge c : challengeList) {
                if (c.getChallengeId().equalsIgnoreCase(targetId)) {
                    selectedChall = c;
                    break;
                }
            }
            if (selectedChall != null) {
                System.out.print("> Enter your Flag answer for '" + selectedChall.getTitle() + "': ");
                String submittedFlag = scanner.nextLine();
                Submission sub = new Submission("S-" + System.currentTimeMillis(), submittedFlag, activePlayer, null, selectedChall);
                if (sub.isCorrect()) {
                    System.out.println("> CORRECT! You earned " + selectedChall.calculatePoints() + " points.");
                } else {
                    System.out.println("> WRONG FLAG!");
                }
            } else {
                System.out.println("> Invalid challenge ID.");
            }
            System.out.println("==========================================================");
            break;
```

The code above is the logic for flag checking! first the user needs to input the challengeId which can be seen in case 1!!! After that the program will check one by one in the challenge list (linearly) if it is the correct challenge.

In the code i've accounted for case sensitivity! If you type for example `c1` it'll automatically assume that you're searching for C1 because of this part `.equalsIgnoreCase` in the code.

If it didn't manage to find it then it'll go to this part of the code:
```java
else {
	System.out.println("> Invalid challenge ID.");
}
```

Once found and the selectedChall doesn't equal to null then it'll ask for the flag that is correct. It'll then log when it is sumitted thru this code:

```java 
Submission sub = new Submission("S-" + System.currentTimeMillis()
```

if sub is correct then it'll go to this part of the code:

```java
if (sub.isCorrect()) {
    System.out.println("> CORRECT! You earned " + selectedChall.calculatePoints() + " points.");
}
```

If not then it'll go into this part:

```java
else {
	System.out.println("> WRONG FLAG!");
}
```

Now after case 2 of course there'll be case 3!!!

```java
        case 3:
            System.out.println("==========================================================");
            System.out.println("> My Profile");
            System.out.println("> Username: " + activePlayer.getUsername());
            System.out.println("> Email: " + activePlayer.getEmail());
            System.out.println("> Total Points: " + activePlayer.getTotalPoints());
            System.out.println("==========================================================");
            break;
```

This is just a simple section where the player can easily look up the player username, email, and total points using the getter method that we've made for the class `Player`!

```java
        case 0:
            System.out.println("> Logging out...");
            inPlayerPanel = false;
            break;
```

Similar to other part of the menu, case 0 will always lead to the end for the menu loop! Which in this case for the Player panel menu!!]

```java
        default:
            System.out.println("> Invalid option.");
    }
```

This part is the default case for the Player Panel Menu switch case!! it'll appeared when the player inputs any number beside 1-3 or 0!

```java
}
} else {
    System.out.println("Invalid option. Please type 1, 2, or 3.");
}
}

scanner.close();
}

}
```

Now after all of that we close the scanner that is use to take our input so that there won't be any memory leaks :D

#### User

Now based on the diagram we'll need to have the class called User! It'll be the parent class for `Player` and `Admin` Interestingly based on the diagram i was told to make all of this!

```java
class User {
// based on the diagram all of the attributes needs to be private!
    private String userId;
    private String username;
    private String email;
    private LocalDateTime createdAt;
```

First the diagram have specified that it'll have these private atribute, thos atribute are:
UserId, username, email, and also a LocalDateTime variable called createdAt! 

![User Class Diagram](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/User.png)

Below are the getter setter that was in the diagram! And base on the symbol (+) all of it must be public!
```java
// basic parameterised constructor!!!
    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
// getter and setter for the class user!!! :3
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
```

#### Player

Following the diagram this class will be inheriting from `User`. This can be seen from the diagram as shown:
![User and Player Class Diagram](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/User_Player.png)

Oh and also we can see that based on that symbol (-) this class will have 2 new private attribute!

```java
class Player extends User {
// 2 new attributes for player!!
    private int playerRank;
    private int totalPoints;

    public Player(String userId, String username, String email) {
        super(userId, username, email);
        // ofcourse we need to initialize them :3
        this.playerRank = 0;
        this.totalPoints = 0;
    }
```
Moving on to the methods for this class!

```java
// getter and setter for this class too ofcourse :3
    public int getPlayerRank() {
        return playerRank;
    }
    public void setPlayerRank(int playerRank) {
        this.playerRank = playerRank;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void addPoints(int points) {
        this.totalPoints += points;
    }
    public void deductPoints(int points) {
        this.totalPoints -= points;
    }

}
```

#### Admin

Similar to `Player` this class will be inheriting from the class `User`. This can be seen in the diagram below:

![Admin Class Diagram](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325151120.png)

As seen aboved the class admin extends from user!!! Also it has two new private variabel !! Let's implement them!

```java
class Admin extends User {
// 2 new attributes for admin based on the diagramm...!!!
    private int adminLevel; // maybe 0 is like top acces and 1 is like the probsettter (?)
    private List<String> permissions;

    public Admin(String userId, String username, String email, int adminLevel) {
        super(userId, username, email);
        this.adminLevel = adminLevel;
        this.permissions = new ArrayList<>();
```
As shown, there's a list called `permissions`!!!

```Java
        // placeholder or default prem
    switch (adminLevel) {
        case 0:
            this.permissions.add("CREATE_CHALLENGE");
            this.permissions.add("EDIT_USERS");
            this.permissions.add("EDIT_TEAMS");
            this.permissions.add("VIEW_FLAGS");
            this.permissions.add("EDIT_CHALLENGE");
            break;
        case 1:
            this.permissions.add("CREATE_CHALLENGE");
            this.permissions.add("EDIT_CHALLENGE");
            break;
        default:
            break;
    }
    }
```
Now i need to be honest here i don't know what is the function of admin level, so i ended up interpreting it where the admin / CTF organizers sometimes need to have the problem setter (the people that makes the challenges) have access to update their own challenge!

Here i made a simple switch case where 0 is the highest priority capable of doing everything and level 1 is where the prob setter can have the permission to create challenge and edit their challenge. 

Sadly because of time i ended up scraping this ;-;
```java
    public int getAdminLevel() {
        return adminLevel;
    }
    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public void createChallenge(Challenge challenge) {
        if (this.permissions.contains("CREATE_CHALLENGE")) {
            System.out.println(challenge.getTitle() + " added.");
        } else {
            System.out.println("No Permission.");
        }
    }
}
```


#### Team

```java
class Team {
// a brand new class, the attribute are following the diagrams!! :D
    private String teamId;
    private String teamName;
    private int teamScore;
    private LocalDateTime createdAt;
    private List<Player> members;

    public Team(String teamId, String teamName, int teamScore) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamScore = teamScore;
        this.createdAt = LocalDateTime.now();
        this.members = new ArrayList<>();
    }

    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamScore() {
        return teamScore;
    }
    public void setTeamScore(int teamScore) {
        this.teamScore = teamScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Player> getMembers() {
        return members;
    }
    // diffrent from other classes, we add and remove individually!
    public void addMember(Player member) {
        this.members.add(member);
    }
    public void removeMember(Player member) {
        this.members.remove(member);
    }
}
```

#### Leaderboard

```java
class Leaderboard {
    private LocalDateTime contestEnd; // this is for when we need to freeze the leaderboards!!
    private String leaderboardId;
    private List<Player> playerLeaderboard;
    private List<Team> teamLeaderboard;

    public Leaderboard(String leaderboardId, int durationContest) {
        this.leaderboardId = leaderboardId;
        this.contestEnd = LocalDateTime.now().plusHours(durationContest); // the contest end will be spesified in hours thru durationContest
        this.playerLeaderboard = new ArrayList<>();
        this.teamLeaderboard = new ArrayList<>();
    }

    public String getLeaderboardId() {
        return leaderboardId;
    }
    public void setLeaderboardId(String leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    // a helper func for just checking contest time end status :D
    public boolean isContestActive() {
        return LocalDateTime.now().isBefore(contestEnd);
    }

    public void addPlayerToLeaderboard(Player player) {
        this.playerLeaderboard.add(player);
    }
    public void removePlayerFromLeaderboard(Player player) {
        this.playerLeaderboard.remove(player);
    }
    // Sorting Leaderboard Player
    public void sortPlayerLeaderboard() {
        // sorts in descending order based on total points!
        playerLeaderboard.sort((p1, p2) -> Integer.compare(p2.getTotalPoints(), p1.getTotalPoints()));
    }

    public void addTeamToLeaderboard(Team team) {
        this.teamLeaderboard.add(team);
    }
    public void removeTeamFromLeaderboard(Team team) {
        this.teamLeaderboard.remove(team);
    }
    // sorts in descending order based on total points!
    public void sortTeamLeaderboard() {
        teamLeaderboard.sort((t1, t2) -> Integer.compare(t2.getTeamScore(), t1.getTeamScore()));
    }

}
```

#### Challenge

```java
abstract class Challenge {
    private String challengeId;
    private String title;
    private String description;
    private int maxPoints;
    private String flag;
    private int minPoints;
    private double decayRate;
    private int solveCount;
    private LocalDateTime createdAt;
    protected Attachment attachment;

    public Challenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment) {
        this.challengeId = challengeID;
        this.title = title;
        this.description = description;
        this.maxPoints = 500;
        this.flag = flag;
        this.attachment = attachment;
        this.minPoints = 100;
        this.decayRate = 0.3;
        this.createdAt = LocalDateTime.now();
    }

    public String getChallengeId() {
        return challengeId;
    }
    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
// this is the class own spesific method
    public abstract boolean verifyFlag(String submittedFlag);

    public int calculatePoints() {
        // the more solve that chal has the point will decreade
        double points = minPoints + (maxPoints - minPoints) * Math.exp(-decayRate * solveCount);
        // turns double into int  to cut decimal
        return (int) points;
    }

    public void viewAttachment() {
        this.attachment.download();
    }

}
```

#### Attachment

```java
interface Attachment {
    void download(); // To indicate that the attachment are being used

}
```

#### ZipAttachment

```java
class ZipAttachment implements Attachment {
    private String ZipLink;

    public ZipAttachment(String ZipLink) {
        this.ZipLink = ZipLink;
    }

    @Override
    public void download() {
        System.out.println("Downloading " + ZipLink);
    }
}
```

#### FileAttachment

```java
class FileAttachment implements Attachment {
    private String FileLink;

    public FileAttachment(String FileLink) {
        this.FileLink = FileLink;
    }

    @Override
    public void download() {
        System.out.println("Downloading " + FileLink);
    }
}
```

#### WebsiteLinkAttachment

```java
class WebsiteLinkAttachment implements Attachment {
    private String WebsiteLink;

    public WebsiteLinkAttachment(String WebsiteLink) {
        this.WebsiteLink = WebsiteLink;
    }

    @Override
    public void download() {
        System.out.println("Downloading " + WebsiteLink);
    }
}
```

#### Connection

```java
interface Connection {
    void serverConnect();
}
```

#### WebExChallenge

```java
class WebExChallenge extends Challenge {
    private String vulnerabilityType;

    public WebExChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String vulnerabilityType) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.vulnerabilityType = vulnerabilityType;
    }

    public String getVulnerabilityType() {
        return vulnerabilityType;
    }
    public void setVulnerabilityType(String vulnerabilityType) {
        this.vulnerabilityType = vulnerabilityType;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
}
```

#### OsintChallenge

```java
class OsintChallenge extends Challenge {
    private String targetSubject;
    private String startingAsset;

    public OsintChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String targetSubject, String startingAsset) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.targetSubject = targetSubject;
        this.startingAsset = startingAsset;
    }

    public String getTargetSubject() {
        return targetSubject;
    }
    public void setTargetSubject(String targetSubject) {
        this.targetSubject = targetSubject;
    }

    public String getStartingAsset() {
        return startingAsset;
    }
    public void setStartingAsset(String startingAsset) {
        this.startingAsset = startingAsset;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
}
```

#### MiscChallenge

```java
class MiscChallenge extends Challenge {
    private String attachmentFile;

    public MiscChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String attachmentFile) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.attachmentFile = attachmentFile;
    }

    public String getAttachmentFile() {
        return attachmentFile;
    }
    public void setAttachmentFile(String attachmentFile) {
        this.attachmentFile = attachmentFile;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
}
```

#### CryptoChallenge

```java
class CryptoChallenge extends Challenge implements Connection { 
    private String ciphertext;
    private String connectionAddress;
    private int connectionPort;

    public CryptoChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String ciphertext, String hostAddress, int portNumber) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.ciphertext = ciphertext;
        this.connectionAddress = hostAddress;
        this.connectionPort = portNumber;
    }

    public String getCiphertext() {
        return ciphertext;
    }
    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getConnectionAddress() {
        return connectionAddress;
    }
    public void setConnectionAddress(String connectionAddress) {
        this.connectionAddress = connectionAddress;
    }

    public int getConnectionPort() {
        return connectionPort;
    }
    public void setConnectionPort(int connectionPort) {
        this.connectionPort = connectionPort;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
    public void serverConnect() {
        System.out.println("Connecting to " + connectionAddress + ":" + connectionPort);
    }
}
```

#### PwnChallenge

```java
class PwnChallenge extends Challenge implements Connection {
    private String libcVersion;
    private String connectionAddress;
    private int connectionPort;

    public PwnChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String libcVersion, String hostAddress, int portNumber) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.libcVersion = libcVersion;
        this.connectionAddress = hostAddress;
        this.connectionPort = portNumber;
    }

    public String getLibcVersion() {
        return libcVersion;
    }
    public void setLibcVersion(String libcVersion) {
        this.libcVersion = libcVersion;
    }

    public String getConnectionAddress() {
        return connectionAddress;
    }
    public void setConnectionAddress(String connectionAddress) {
        this.connectionAddress = connectionAddress;
    }

    public int getConnectionPort() {
        return connectionPort;
    }
    public void setConnectionPort(int connectionPort) {
        this.connectionPort = connectionPort;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
    public void serverConnect() {
        System.out.println("Connecting to " + connectionAddress + ":" + connectionPort);
    }
}
```

#### RevEngChallenge

```java
class RevEngChallenge extends Challenge {
    private String architecture;

    public RevEngChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String architecture) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.architecture = architecture;
    }

    public String getArchitecture() {
        return architecture;
    }
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
}
```

#### ForensicChallenge

```java
class ForensicChallenge extends Challenge {
    private String md5Hash;

    public ForensicChallenge(String challengeID, String title, String description, int maxPoints, String flag,
            Attachment attachment, String md5Hash) {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.md5Hash = md5Hash;
    }

    public String getMd5Hash() {
        return md5Hash;
    }
    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }

    @Override
    public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
}
```

#### Submission

```java
class Submission {
    private String submissionId;
    private LocalDateTime submissionTime;
    private String submissionFlag;
    private boolean isCorrect;
    private Player player; private Team team;
    private Challenge challenge;

    public Submission(String submissionId, String submissionFlag, Player player, Team team, Challenge challenge) {
        this.submissionId = submissionId;
        this.submissionTime = LocalDateTime.now();
        this.submissionFlag = submissionFlag;
        this.player = player;
        this.team = team;
        this.challenge = challenge;
        this.isCorrect = challenge.verifyFlag(submissionFlag);
    // logic for flag submission checker
    if (this.isCorrect) {
            int pointsEarned = challenge.calculatePoints();

            // increase player points
            if (this.player != null) {
                this.player.addPoints(pointsEarned);
            }

            // increase team points
            if (this.team != null) {
                this.team.setTeamScore(this.team.getTeamScore() + pointsEarned);
            }
        }
    }

    public String getSubmissionId() {
        return submissionId;
    }
    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public String getSubmissionFlag() {
        return submissionFlag;
    }
    public void setSubmissionFlag(String submissionFlag) {
        this.submissionFlag = submissionFlag;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    public Challenge getChallenge() {
        return challenge;
    }
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

}
```

### OOP Principles

In this section i'll be explaining all of the OOP Principles that are used in the program! But before that... Lets start with four main pillars of OOP!!

#### Encapsulation

Encapsulation is a practice where each class has a method to use a specific private variable that it has, while all of the classes in the program uses this principle, i'll use the class `Submission` as an example

```java
class Submission {
    private String submissionId;
    private LocalDateTime submissionTime;
    private String submissionFlag;
    private boolean isCorrect;
    private Player player; private Team team;
    private Challenge challenge;
```

As you can see the class has a couple of private variable, if the program suddenly needs to know if wether or not a `Player` submitted the correct flag, it'll need a way to get the value / string of the correct flag. To solve that issue we can easily create a getter setter for that private variable, and this is indicated in the diagram too!

```java
public String getSubmissionFlag() {
        return submissionFlag;
    }
```

First we need a method for the flag that is submitter by the `Player`

```java
public boolean isCorrect() {
        return isCorrect;
    }
```

Then we create another method to check if it is the correct one! This is one of many examples on how the program uses encapsulation!

#### Abstraction

Now for the next principle we have Abstraction. The concept of this is having a class that is only showing the essential features of an object. For this program there is only one abstract class which is!

```java
abstract class Challenge {
...
}
```

This is because in the diagram it specifically mentions that it is an abstract class!

![Pasted image 20260401113016.png](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260401113016.png)

The text `<<abstract>>` is considered a UML stereotype. Now besides being specifically told that it is an abstract class, it makes sense that this class will be considered as one. This is because other challenge categories wouldn't have to implement all of the complicated methods for flag checking, etc. Now this leads nicely into the next pillar of OOP which is

#### Inheritance

Inheritance is a principle where a class can inherit another class's variables and methods. The class that is inheriting is called a child class, whilst the class that gets inherited is called a parent class. In the world of OOP, there are two types of parent class which are:

##### Abstract Class

Besides the function that has been mentioned, having an abstract class as a parent class has its benefits, which are:

1. The inability to create an object from an abstract class

	An example that i can give to explain this benefit is to imagine the class `Challenge` as a concrete class. A code like this can be use to create a new challenge.
	
	```java
	Challenge myChallenge = new Challenge();
	```

	By doing this you won't be able to know what the category of the challenge and all of the necessary attachment that is needed for it.

2. The ability to create an abstract method

	An abstract method is a method where you can have a function that can be tailored suited for its own child. This can be seen in the `Challenge` class.
	
	```java
	public abstract boolean verifyFlag(String submittedFlag);
	```

	Each challenge will have its own flag, that's why we need to make this as an abstract class!

##### Concrete Class

Now for a concrete class, it's basically the exact opposite of a abstract class. In the program class `User` is considered as a concrete one! An this can also be seen from the diagram

![User Class Diagram](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/User.png)

As you can see compared to the other class, the diagram doesn't mention that it is an abstract class.

#### Polymorphism

And then we have Polymorphism. This is the principle that allows different child classes to share the same method name as their parent, but execute their own entirely different, specific code when that method is actually called.

This principle is mainly use in the child class for the parent class `Challenge` and also in the child class for that implements a interface such as `Attachment` and `Connection`.

##### Challenge

For this case @Override is mentioned in all of the challenge category, this is because an abstract class must be implemented by it's own child class.

```java
public boolean verifyFlag(String submittedFlag) {
        return this.getFlag().equals(submittedFlag);
    }
```

##### Attachment & Connection

Similar to the class `Challenge` @Override is mentioned in all of the challenge category, this is because a class that implements a interface must Override (Implements) the abstract methods defined in that interface.

-  Attachment

```java
interface Attachment {
    void download(); // To indicate that the attachment are being used
}
```

In the code that interface is being implemented like this:

```java
class ZipAttachment implements Attachment {
    private String ZipLink;
    public ZipAttachment(String ZipLink) {
        this.ZipLink = ZipLink;
    }
    @Override
    public void download() {
        System.out.println("Downloading " + ZipLink);
    }
}
```

### Output

**1. Main Menu**
![Main Menu](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325153716.png)

**2. Admin Panel**
![Admin Panel](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325153742.png)

**3. Create a new challenge**
![Create a new challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325153833.png)

**4. Create another Challenge with a different category (Pwn)**
![Create Pwn Challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154002.png)

**5. Creating an OSINT Chall**
![Create OSINT Challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154146.png)

**6. Creating a Misc Chall**
![Create Misc Challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154220.png)

**7. Creating a Crypto Chall**
![Create Crypto Challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154306.png)

**8. Creating a Reveng Chall**
![Create Reveng Challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154340.png)

**9. Creating a Forens Chall**
![Create Forens Challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154421.png)

**10. View all players**
![View all players](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154503.png)

**11. View Leaderboard**
![View Leaderboard](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154520.png)

**12. Back from admin panel**
![Back from admin panel](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154550.png)

**13. Logging into player**
![Logging into player](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154609.png)

**14. View active challenge**
![View active challenge](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154652.png)

**15. View active challenge, when there is none**
![View active challenge empty](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154804.png)

**16. Submitting correct flag**
![Submitting correct flag](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154853.png)

**17. Submitting wrong flag**
![Submitting wrong flag](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154924.png)

**18. View stats with points**
![View stats with points](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325154946.png)

**19. View stats with no points**
![View stats with no points](https://raw.githubusercontent.com/saktisadhana/Data-Structure-OOP/main/Assignment%202/Assets/Pasted%20image%2020260325155018.png)

### Individuality

Lately i've been pretty obsessed with CTF, and i'll be suprised if there's someone that'll make a CTF Platform. If that were to happened i'll be sure that they won't seperate OSINT (Which is usually grouped into misc) into it's own class!

