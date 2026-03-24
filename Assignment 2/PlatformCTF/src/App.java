import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Hello, World!");
    }
}

class User {
// Atribute
    private String userId;
    private String username;
    private String email;
    private LocalDateTime createdAt;
// Constructor
    public User(String userId, String username, String email) 
    {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
// Getter & Setter
    // Getter & Setter createdAt
    public LocalDateTime getCreatedAt() 
    {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) 
    {
        this.createdAt = createdAt;
    }
    // Getter & Setter userId
    public String getUserId() 
    {
        return userId;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }
    // Getter & Setter username
    public void setUsername(String username) 
    {
        this.username = username;
    }
    public String getUsername() 
    {
        return username;
    }
    // Getter & Setter email
    public void setEmail(String email) 
    {
        this.email = email;
    }
    public String getEmail() 
    {
        return email;
    }
}

class Player extends User {
// Atribute
    private int playerRank;
    private int totalPoints;
// Constructor
    public Player(String userId, String username, String email) {
        super(userId, username, email);
        //Player-own variables constructor
        this.playerRank = 0;
        this.totalPoints = 0;
    }
// Getter & Setter
    // Getter & Setter playerRank
    public int getPlayerRank() 
    {
        return playerRank;
    }
    public void setPlayerRank(int playerRank) 
    {
        this.playerRank = playerRank;
    }
    // Getter & Setter totalPoints
    public int getTotalPoints()
    {
        return totalPoints;
    }
    public void setTotalPoints(int totalPoints) 
    {
        this.totalPoints = totalPoints;
    }
// Player-specific method
    public void addPoints (int points)
    {
        this.totalPoints += points;    }
    public void deductPoints (int points)
    {
        this.totalPoints -= points;    }

}

class Admin extends User {
// Atribute
    private int adminLevel;
    private List<String> permissions; // TODO : ADD SIMPLE PERMISSION LIST
// Constructor
    public Admin(String userId, String username, String email)
    {
        super(userId, username, email);
        this.adminLevel = 0;
        this.permissions = new ArrayList<>();
    }
// Getter & Setter
    // Getter & Setter adminLevel
    public int getAdminLevel() 
    {
        return adminLevel;
    }
    public void setAdminLevel(int adminLevel) 
    {
        this.adminLevel = adminLevel;
    }
    // Getter & Setter permissions
    public List<String> getPermissions() 
    {
        return permissions;
    }
    public void setPermissions(List<String> permissions) 
    {
        this.permissions = permissions;
    }
// Admin-specific method
    public void createChallenge(Challenge challenge) 
    {
        // TODO : ADD LOGIC FOR CHALL CREATION
    }

}

abstract class Challenge 
{
// Atribute
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
// Constructor
    public Challenge(String challengeID, String title, String description, int maxPoints, String flag, Attachment attachment)
    {
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
// Getter & Setter
    // Getter & Setter challengeId
    public String getChallengeId() 
    {
        return challengeId;
    }
    public void setChallengeId(String challengeId) 
    {
        this.challengeId = challengeId;
    }
    // Getter & Setter title
    public String getTitle() 
    {
        return title;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }
    // Getter & Setter description
    public String getDescription() 
    {
        return description;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }
    // Getter & Setter maxPoints
    public int getMaxPoints() 
    {
        return maxPoints;
    }
    public void setMaxPoints(int maxPoints) 
    {
        this.maxPoints = maxPoints;
    }
    // Getter & Setter flag
    public String getFlag() 
    {
        return flag;
    }
    public void setFlag(String flag) 
    {
        this.flag = flag;
    }
    // Getter & Setter createdAt
    public LocalDateTime getCreatedAt() 
    {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) 
    {
        this.createdAt = createdAt;
    }
// Challenge-specific method
    public abstract boolean verifyFlag (String submittedFlag);
    public int calculatePoints ()
    {
        double points = minPoints + (maxPoints - minPoints) * Math.exp(-decayRate * solveCount);
        return (int) points;  
    }
    public void viewAttachment()
    {
        this.attachment.download();
    }

}

// Attachment to chals, atm only zip, file, web link.
interface Attachment 
{
    void download(); // To indicate that the attachment are being used 
    
}

class ZipAttachment implements Attachment
{
    private String ZipLink;
    public ZipAttachment(String ZipLink)
    {
        this.ZipLink = ZipLink;
    }
    @Override
    public void download() {
        System.out.println("Downloading " + ZipLink);
    }
}

class FileAttachment implements Attachment 
{
    private String FileLink;
    public FileAttachment(String FileLink)
    {
        this.FileLink = FileLink;
    }
    @Override
    public void download() {
        System.out.println("Downloading " + FileLink);
    }
}

class WebsiteLinkAttachment implements Attachment 
{
    private String WebsiteLink;
    public WebsiteLinkAttachment(String WebsiteLink)
    {
        this.WebsiteLink = WebsiteLink;
    }
    @Override
    public void download() {
        System.out.println("Downloading " + WebsiteLink);
    }
}
// For creating port and stuff

// Types of challanges
class WebExChallenge extends Challenge
{
// Atribute
private String vulnerabilityType;
// Constructor
    public WebExChallenge (String challengeID, String title, String description, int maxPoints, String flag, Attachment attachment, String vulnerabilityType)
    {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.vulnerabilityType = vulnerabilityType;
    }
// Getter & Setter
    // Getter & Setter vulnerabilityType 
    public String getVulnerabilityType() 
    {
        return vulnerabilityType;
    }
    public void setVulnerabilityType(String vulnerabilityType) 
    {
        this.vulnerabilityType = vulnerabilityType;
    }
// Override
@Override
    public boolean verifyFlag (String submittedFlag)
    {
        return this.getFlag().equals(submittedFlag);
    }
}

class OsintChallenge extends Challenge
{
// Atribute
private String targetSubject;
private String startingAsset;
// Constructor
    public OsintChallenge (String challengeID, String title, String description, int maxPoints, String flag, Attachment attachment, String targetSubject, String startingAsset)
    {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.targetSubject = targetSubject;
        this.startingAsset = startingAsset;
    }
// Getter & Setter
    // Getter & Setter targetSubject
    public String getTargetSubject() 
    {
        return targetSubject;
    }
    public void setTargetSubject(String targetSubject) 
    {
        this.targetSubject = targetSubject;
    } 
    // Getter & Setter startingAsset
    public String getStartingAsset() 
    {
        return startingAsset;
    }
    public void setStartingAsset(String startingAsset) 
    {
        this.startingAsset = startingAsset;
    }
// Override
@Override
    public boolean verifyFlag (String submittedFlag)
    {
        return this.getFlag().equals(submittedFlag);
    }
}

class MiscChallenge extends Challenge
{
// Atribute
private String attachmentFile;
// Constructor
    public MiscChallenge (String challengeID, String title, String description, int maxPoints, String flag, Attachment attachment, String attachmentFile)
    {
        super(challengeID, title, description, maxPoints, flag, attachment);
        this.attachmentFile = attachmentFile;
    }
// Getter & Setter
    // Getter & Setter attachmentFile
    public String getAttachmentFile() 
    {
        return attachmentFile;
    }
    public void setAttachmentFile(String attachmentFile) 
    {
        this.attachmentFile = attachmentFile;
    }
// Override
@Override
    public boolean verifyFlag (String submittedFlag)
    {
        return this.getFlag().equals(submittedFlag);
    }
}

