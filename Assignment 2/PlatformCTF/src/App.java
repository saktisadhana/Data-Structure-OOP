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
                                
                                while (typeChoice < 1 || typeChoice > 7) {
                                    System.out.println("Invalid type entered. Please pick from 1-7.");
                                    System.out.print("> ");
                                    typeChoice = scanner.nextInt(); scanner.nextLine();
                                }

                                System.out.print("> Enter title: ");
                                String title = scanner.nextLine();
                                System.out.print("> Enter the Description: ");
                                String desc = scanner.nextLine();
                                System.out.print("> Enter Flag: ");
                                String flag = scanner.nextLine();
                                String challId = "C" + (challengeList.size() + 1); // challenge id
                                Attachment webTest = new WebsiteLinkAttachment("test.com"); // dummy domain
                                Challenge newChall = null;

                                switch (typeChoice) {
                                    case 1:
                                        System.out.println("==========================================================");
                                        System.out.print("> Enter Vulnerability Type (e.g. XSS, SQLi): ");
                                        String vuln = scanner.nextLine();
                                        newChall = new WebExChallenge(challId, title, desc, 500, flag, webTest, vuln);
                                        System.out.println("==========================================================");
                                        break;
                                    case 2:
                                        System.out.println("==========================================================");
                                        System.out.print("> Enter Target Subject: ");
                                        String subject = scanner.nextLine();
                                        System.out.print("> Enter Starting Asset: ");
                                        String asset = scanner.nextLine();
                                        newChall = new OsintChallenge(challId, title, desc, 500, flag, webTest, subject, asset);
                                        System.out.println("==========================================================");
                                        break;
                                    case 3:
                                        System.out.println("==========================================================");
                                        System.out.print("> Enter Attachment File Name: ");
                                        String file = scanner.nextLine();
                                        newChall = new MiscChallenge(challId, title, desc, 500, flag, webTest, file);
                                        System.out.println("==========================================================");
                                        break;
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
                                    case 6:
                                        System.out.println("==========================================================");
                                        System.out.print("> Enter Target Architecture: ");
                                        String arch = scanner.nextLine();
                                        newChall = new RevEngChallenge(challId, title, desc, 500, flag, webTest, arch);
                                        System.out.println("==========================================================");
                                        break;
                                    case 7:
                                        System.out.println("==========================================================");
                                        System.out.print("> Enter Target MD5 Hash: ");
                                        String md5 = scanner.nextLine();
                                        newChall = new ForensicChallenge(challId, title, desc, 500, flag, webTest, md5);
                                        System.out.println("==========================================================");
                                        break;
                                    default:
                                        System.out.println("==========================================================");
                                        System.out.println(" > Invalid type entered");
                                        System.out.println("==========================================================");
                                        break;
                                }
                                
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
                            
                        case 2:
                            System.out.println("==========================================================");
                            System.out.println("> Player list:");
                            for (Player p : playerList) {
                                System.out.println("> Username: " + p.getUsername() + " | Points: " + p.getTotalPoints());
                            }
                            System.out.println("==========================================================");
                            break;
                            
                        case 3:
                            System.out.println("==========================================================");
                            System.out.println("> CTF LEADERBOARD");
                            mainLeaderboard.sortPlayerLeaderboard(); // sort the leaderboard :3
                            for (Player p : playerList) { 
                                System.out.println("> " + p.getUsername() + " : " + p.getTotalPoints() + " pts");
                            }
                            System.out.println("==========================================================");
                            break;
                            
                        case 0:
                            System.out.println("> Logging out...");
                            inAdminPanel = false;
                            break;
                            
                        default:
                            System.out.println("> Invalid option.");
                    }
                }
            } 

            else if (loginChoice == 2) { 
                boolean inPlayerPanel = true;
                Player activePlayer = testPlayer; 
                
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
                            
                        case 3:
                            System.out.println("==========================================================");
                            System.out.println("> My Profile");
                            System.out.println("> Username: " + activePlayer.getUsername());
                            System.out.println("> Email: " + activePlayer.getEmail());
                            System.out.println("> Total Points: " + activePlayer.getTotalPoints());
                            System.out.println("==========================================================");
                            break;
                            
                        case 0:
                            System.out.println("> Logging out...");
                            inPlayerPanel = false;
                            break;
                            
                        default:
                            System.out.println("> Invalid option.");
                    }
                }
            } else {
                System.out.println("Invalid option. Please type 1, 2, or 3.");
            }
        }
        
        scanner.close();
    }
    
}

class User {
// based on the diagram all of the attributes needs to be private!
    private String userId;
    private String username;
    private String email;
    private LocalDateTime createdAt;
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

class Admin extends User {
// 2 new attributes for admin based on the diagramm...!!!
    private int adminLevel; // maybe 0 is like top acces and 1 is like the probsettter (?)
    private List<String> permissions;

    public Admin(String userId, String username, String email, int adminLevel) {
        super(userId, username, email);
        this.adminLevel = adminLevel;
        this.permissions = new ArrayList<>();
    
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

// interface for challenge attachment
// links, file links, zip links etc
interface Attachment {
    void download(); // To indicate that the attachment are being used

}
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

// interface for challenge that needs connection to server, like pwn and crypto
interface Connection {
    void serverConnect();
}

// the 6 types of challs
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

// crypto challs can connect to a server!!
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

// same with crypto, pwn can connect to a server
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