#include <iostream> 
#include <string>
#define MAX 100
using namespace std;

class Karakter 
{
protected: 
// Attribut
    string name;
    char tier;
    int hp,attackPower;
public: 
    int input;
// Constructor "Name" "Tier" "Hp" & "attackPower"
    Karakter (string n, char t, int h, int a)
    {
        name = n; tier = t; hp = h; attackPower = a;
    }
    // Setter & Getter "Name"
    void setName (string n)
    {
        name = n;
    }
    string getName()
    {
        return name;
    }
    // Setter & Getter "Tier"
    void setTier (char t)
    {
        tier = t;
    }
    char getTier()
    {
        return tier;
    }
    // Setter & Getter "HP"
    void setHP (int h)
    {
        hp = h;
    }
    int getHP()
    {
        return hp;
    }
    // Setter & Getter "attackPower"
    void setattackPower (int a)
    {
        attackPower = a;
    }
    int getattackPower()
    {
        return attackPower;
    }
    // Virtual Method displayInfo
    virtual void displayInfo()
    {
        cout << "Pick an Attribute!" << endl;
        cout << "1. Name" << endl;
        cout << "2. Tier" << endl;
        cout << "3. HP" << endl;
        cout << "4. Attack Power" << endl;
        cout << ">"; cin >> input;
    switch (input)
    {
    case 1:
        cout << name;
        break;
    case 2:
        cout << tier;
        break;
    case 3:
        cout << hp;
        break;
    case 4:
        cout << attackPower;
        break;
    
    default:
        cout << "Invalid Option!" << endl;
        break;
    }
    }
    // Virtual Methodd useSkill()
    virtual void useSkill()
    {
        cout << "Skill is Used B-bb-b-Baka! >_<" << endl;
    }
};
// inheritance
class Warrior : public Karakter 
{
private:
int stamina;

public:
Warrior(string name, char tier, int hp, int attackPower, int stamina) : Karakter(name, tier, hp, attackPower) 
{
    this->stamina = stamina;
}

  // Polymorphism (override)
void displayInfo() override 
{
    Karakter::displayInfo();
    cout << "Stamina: " << stamina << endl;
}

void useSkill() override 
{
    if (stamina >= 20) 
    {
    stamina -= 20;
    cout << "Karakter " << name << " menebas musuh dengan pedang!" << endl;
    } else 
    {
    cout << "Stamina " << name << " tidak cukup!" << endl;
    }
}
};

class Mage : public Karakter 
{
private:
int mana;

public:
Mage(string name, char tier, int hp, int attackPower, int mana) : Karakter(name, tier, hp, attackPower) 
{
    this->mana = mana;
}

void displayInfo() override 
{
    Karakter::displayInfo();
    cout << "Mana: " << mana << endl;
}

void useSkill() override 
{
    if (mana >= 30) 
    {
    mana -= 30;
    cout << "Character " << name << " Used Elemental Burst!" << endl;
    } else 
    {
    cout << "Mana " << name << " is 0!!!" << endl;
    }
}
};

// array of pointer
Karakter* daftarKarakter[MAX];
int jumlahKarakter = 0;

// CRUD Karakter
void createKarakter() 
{
    string name;
    char tier;
    int hp;
    int attackPower;
    int type;
    cout << "> Input Name: ";
    cin.ignore();
    getline(cin, name);
    for (int i = 0; i < jumlahKarakter; i++) 
    {
        if (daftarKarakter[i]->getName() == name) 
        {
            cout << "Character already exist!" << endl;
            return;
        }
    }
    cout << "> Input Tier: ";
    cin >> tier;
    if (tier != 'A' && tier != 'B' && tier != 'C' && tier != 'D' && tier != 'E') 
    {
        cout << "Invalid Tier!!!" << endl;
        return;
    }
    cout << ">Input HP: ";
    cin >> hp;
    cout << "> Input Attack Power: ";
    cin >> attackPower;
    cout << "> Input Type (1) Warrior, (2) Mage: ";
    cin >> type;
    if (type == 1) 
    {
        int stamina;
        cout << "> Input stamina: ";
        cin >> stamina;
        daftarKarakter[jumlahKarakter] = new Warrior(name, tier, hp, attackPower, stamina);
        jumlahKarakter++;
    } else if (type == 2) 
    {
        int mana;    
        cout << "> Input mana: ";
        cin >> mana;
        daftarKarakter[jumlahKarakter] = new Mage(name, tier, hp, attackPower, mana);
        jumlahKarakter++;
    } else 
    {
        cout << "Invalid Type" << endl;
    }
}

void displayKarakter() {
    if (jumlahKarakter == 0) 
    {
        cout << "No Characters!!!" << endl;
    } else {
        cout << "==== List of Characters ====" << endl;
        for (int i = 0; i < jumlahKarakter; i++) 
        {
            cout << i + 1 << ". ";
            daftarKarakter[i]->displayInfo();
        }
    }
}

void promote() 
{
    string name;
    cout << "> Input the name of the character to promote: ";
    cin.ignore();
    getline(cin, name);
    bool found = false;
    for (int i = 0; i < jumlahKarakter; i++) {
        if (daftarKarakter[i]->getName() == name) 
        {
            found = true;
            char curTier;
            curTier = daftarKarakter[i]->getTier();
            if (curTier == 'A') 
            {
                cout << "Character " << name << " is already tier A!" << endl;
                break;
            }
            daftarKarakter[i]->setTier(curTier - 1); // Character Arithmetic (kalau minus naik urutan B -> A, sedangkan plus turun urutan A-> B) 
            cout << "Character " << name << " successfully promoted to tier " << daftarKarakter[i]->getTier() << endl;
            break;
        }
    }
    if (!found) 
    {
        cout << "Character " << name << " not found !! " << endl;
    }
}

void deleteKarakter() 
{
    string name;
    cout << "> Input the name of the character to delete: ";
    cin.ignore();
    getline(cin, name);
    bool found = false;
    for (int i = 0; i < jumlahKarakter; i++) 
    {
        if (daftarKarakter[i]->getName() == name) 
        {
            found = true;
            delete daftarKarakter[i];
            for (int j = i; j < jumlahKarakter - 1; j++) 
            {
                daftarKarakter[j] = daftarKarakter[j + 1];
            }
            jumlahKarakter--;
            cout << "Character " << name << " successfully deleted!" << endl;
            break;
        }
    }
    if (!found) 
    {
        cout << "Character " << name << " not found!! " << endl;
    }
}

void trySkill() 
{
    string name;
    cout << "> Input the name of the character: ";
    cin.ignore();
    getline(cin, name);
    bool found = false;
    for (int i = 0; i < jumlahKarakter; i++) 
    {
        if (daftarKarakter[i]->getName() == name) 
        {
            found = true;
            daftarKarakter[i]->useSkill();
            break;
        }
    }
    if (!found) 
    {
        cout << "Character " << name << " not found!! " << endl;
    }
}

void menu() {
    cout << "==== Main Menu ====" << endl;
    cout << "(1) Create a Character" << endl;
    cout << "(2) Check List of Characters" << endl;
    cout << "(3) Promote Character" << endl;
    cout << "(4) Delete Character" << endl;
    cout << "(5) Simulate Skill" << endl;
    cout << "(6) Exit" << endl;
    cout << "==================" << endl;
}

int main() {
while (true) {
    menu();
    int choice;
    cout << "> INPUT: ";
    cin >> choice;
    switch (choice) 
    {
        case 1:
            system("cls");
            createKarakter();
            break;
        case 2:
            system("cls");
            displayKarakter();
            break;
        case 3:
            system("cls");
            promote();
            break;
        case 4:
            system("cls");
            deleteKarakter();
            break;
        case 5:
            system("cls");
            trySkill();
            break;
        case 6:
            system("cls");
            cout << "Thx 4 playing" << endl;
            return 0;
        default:
            break;
    }
}
}