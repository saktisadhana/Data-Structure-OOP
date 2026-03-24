#include <iostream>
#include <string>
using namespace std;

class Pemain
{
protected:
    string nama;
    int koin;
    int exp;
    int tier;

public:
    Pemain(string n, int t)
    {
        nama = n;
        tier = t;
        koin = 0;
        exp = 0;
    }

    string getNama()
    {
        return nama;
    }

    virtual void hunt(int baseKoin, int baseExp, int jumlahParty) = 0;

    virtual void info()
    {
        cout << "Nama: " << nama
             << " | Koin: " << koin
             << " | EXP: " << exp << endl;
    }

    virtual ~Pemain() {}
};

class Solo : public Pemain
{
public:
    Solo(string n, int t) : Pemain(n, t) {}

    void hunt(int baseKoin, int baseExp, int jumlahParty) override
    {
        koin += baseKoin;
        exp += baseExp * (1 + tier);
    }

    void info() override
    {
        cout << "[Solo] ";
        Pemain::info();
    }
};

class Party : public Pemain
{
public:
    Party(string n, int t) : Pemain(n, t) {}

    void hunt(int baseKoin, int baseExp, int jumlahParty) override
    {
        if (jumlahParty > 0)
        {
            koin += baseKoin / jumlahParty;
        }

        exp += baseExp * (1 + tier);
    }

    void info() override
    {
        cout << "[Party] ";
        Pemain::info();
    }
};

int main()
{
    int n;

    cout << "=== REGISTRASI ===" << endl;
    cout << "Jumlah pemain: ";
    cin >> n;

    Pemain **daftar = new Pemain*[n];

    for (int i = 0; i < n; i++)
    {
        int tipe, tt;
        string nama;

        cout << "\nData pemain ke-" << i + 1 << endl;
        cout << "Tipe (1 Solo / 2 Party): ";
        cin >> tipe;

        cout << "Nama: ";
        cin >> nama;

        cout << "Tier tambahan: ";
        cin >> tt;

        if (tipe == 1)
        {
            daftar[i] = new Solo(nama, tt);
        }
        else
        {
            daftar[i] = new Party(nama, tt);
        }
    }

    int sesi;

    cout << "\nJumlah sesi hunt: ";
    cin >> sesi;

    for (int i = 0; i < sesi; i++)
    {
        string target;
        int baseKoin, baseExp, party;

        cout << "\nSesi hunt ke-" << i + 1 << endl;
        cout << "Nama pemain: ";
        cin >> target;

        cout << "Base koin: ";
        cin >> baseKoin;

        cout << "Base exp: ";
        cin >> baseExp;

        cout << "Jumlah party: ";
        cin >> party;

        for (int j = 0; j < n; j++)
        {
            if (daftar[j]->getNama() == target)
            {
                daftar[j]->hunt(baseKoin, baseExp, party);
                cout << target << " berhasil hunt!" << endl;
                break;
            }
        }
    }

    cout << "\n=== STATUS AKHIR ===" << endl;

    for (int i = 0; i < n; i++)
    {
        daftar[i]->info();
    }

    for (int i = 0; i < n; i++)
    {
        delete daftar[i];
    }

    delete[] daftar;

    return 0;
}

