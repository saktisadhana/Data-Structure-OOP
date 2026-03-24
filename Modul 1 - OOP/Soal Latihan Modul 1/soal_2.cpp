#include <iostream>
#include <string>

using namespace std;

class Karakter {
protected:
    string nama;
    string ekspresi;
    int level;

    string getWajah() {
        if (ekspresi == "biasa") return "( o_o )";
        if (ekspresi == "senang") return "( ^_^ )";
        if (ekspresi == "sedih")  return "( T_T )";
        return "( -_- )";
    }

public:
    Karakter(string nama, string ekspresi, int level)
        : nama(nama), ekspresi(ekspresi), level(level) {}

    string getNama() const { return nama; }
    string getEkspresi() const { return ekspresi; }
    int getLevel() const { return level; }
    void setEkspresi(string e) { ekspresi = e; }

    virtual void displayKarakter() {
        cout << "       O      " << endl;
        cout << "    " << getWajah() << endl;
        cout << "     /|\\     " << endl;
        cout << "      |       " << endl;
        cout << "     / \\      " << endl;
    }

    virtual void tampilInfo() {
        cout << "[ " << nama << " | " << getTipe()
             << " | Lv." << level
             << " | " << ekspresi << " ]" << endl;
    }

    virtual string getTipe() const { return "Karakter"; }

    virtual ~Karakter() {}
};

class Manusia : public Karakter {
private:
    string perasaan;

public:
    Manusia(string nama, string ekspresi, int level, string perasaan)
        : Karakter(nama, ekspresi, level), perasaan(perasaan) {}

    void setPerasaan(string p) { perasaan = p; }

    void displayKarakter() override {
        cout << "       O      " << endl;
        cout << "    " << getWajah() << endl;

        if (perasaan == "Penasaran") {
            cout << "     /| |\\ ?  " << endl;
            cout << "      | |     " << endl;
            cout << "     _/ \\_    " << endl;
            cout << "       ?      " << endl;
        }
        else if (perasaan == "Jatuh Cinta") {
            cout << "     /| |\\ <3 " << endl;
            cout << "      | |     " << endl;
            cout << "     _/ \\_    " << endl;
            cout << "      <3      " << endl;
        }
        else {
            cout << "     /| |\\    " << endl;
            cout << "      | |     " << endl;
            cout << "     _/ \\_    " << endl;
            cout << "      </3     " << endl;
        }
    }

    void tampilInfo() override {
        cout << "[ " << nama
             << " | Manusia | Lv." << level
             << " | " << ekspresi
             << " | " << perasaan << " ]" << endl;
    }

    string getTipe() const override { return "Manusia"; }
};

class Vampir : public Karakter {
private:
    string tekad;

public:
    Vampir(string nama, string ekspresi, int level, string tekad)
        : Karakter(nama, ekspresi, level), tekad(tekad) {}

    void setTekad(string t) { tekad = t; }

    void displayKarakter() override {
        cout << "      _\\/_    " << endl;
        cout << "    " << getWajah() << endl;

        if (tekad == "Menyembunyikan") {
            cout << "    }|##|{    " << endl;
            cout << "      ||      " << endl;
            cout << "     _/\\_     " << endl;
            cout << "     . . .    " << endl;
        }
        else if (tekad == "Mengungkapkan") {
            cout << "    }|  |{>>  " << endl;
            cout << "      ||      " << endl;
            cout << "     _/\\_     " << endl;
            cout << "     <3 <3    " << endl;
        }
        else {
            cout << "    }|[]|{    " << endl;
            cout << "      ||      " << endl;
            cout << "     _/\\_     " << endl;
            cout << "    [====]    " << endl;
        }
    }

    void tampilInfo() override {
        cout << "[ " << nama
             << " | Vampir | Lv." << level
             << " | " << ekspresi
             << " | " << tekad << " ]" << endl;
    }

    string getTipe() const override { return "Vampir"; }
};

Karakter* daftarKarakter[100];
int jumlahKarakter = 0;


void tampilMenu() {
    cout << "\n========================================\n";
    cout << "        HUIMANG SECRET DIARY\n";
    cout << "========================================\n";
    cout << "1. Tambah Kisah\n";
    cout << "2. Lihat Semua\n";
    cout << "3. Ubah Data\n";
    cout << "4. Hapus Kisah\n";
    cout << "5. Keluar\n";
    cout << "Pilihan: ";
}


void tambahKisah() {

    if (jumlahKarakter >= 100) return;

    int tipe;
    cout << "Tipe karakter (1.Manusia 2.Vampir): ";
    cin >> tipe;
    cin.ignore();

    string nama, ekspresi, atribut;
    int level;

    cout << "Nama: ";
    getline(cin, nama);

    cout << "Ekspresi: ";
    getline(cin, ekspresi);

    cout << "Level: ";
    cin >> level;
    cin.ignore();

    if (tipe == 1) {
        cout << "Perasaan: ";
        getline(cin, atribut);
        daftarKarakter[jumlahKarakter++] =
            new Manusia(nama, ekspresi, level, atribut);
    }
    else {
        cout << "Tekad: ";
        getline(cin, atribut);
        daftarKarakter[jumlahKarakter++] =
            new Vampir(nama, ekspresi, level, atribut);
    }

    cout << "\nKarakter berhasil ditambahkan!\n";
}


void lihatDiary() {

    if (jumlahKarakter == 0) {
        cout << "Diary masih kosong.\n";
        return;
    }

    for (int i = 0; i < jumlahKarakter; i++) {

        cout << "\n--- Data #" << i + 1 << " ---\n";

        daftarKarakter[i]->displayKarakter();
        daftarKarakter[i]->tampilInfo();
    }
}


void ubahData() {

    string target;

    cout << "Masukkan nama karakter: ";
    cin.ignore();
    getline(cin, target);

    for (int i = 0; i < jumlahKarakter; i++) {

        if (daftarKarakter[i]->getNama() == target) {

            cout << "\nData lama:\n";
            daftarKarakter[i]->displayKarakter();
            daftarKarakter[i]->tampilInfo();

            int pilihan;
            cout << "\n1.Ubah ekspresi\n2.Ubah atribut khusus\nPilihan: ";
            cin >> pilihan;
            cin.ignore();

            if (pilihan == 1) {

                string e;
                cout << "Ekspresi baru: ";
                getline(cin, e);

                daftarKarakter[i]->setEkspresi(e);
            }
            else {

                string a;
                cout << "Atribut baru: ";
                getline(cin, a);

                if (daftarKarakter[i]->getTipe() == "Manusia")
                    ((Manusia*)daftarKarakter[i])->setPerasaan(a);
                else
                    ((Vampir*)daftarKarakter[i])->setTekad(a);
            }

            cout << "\nData berhasil diperbarui.\n";

            daftarKarakter[i]->displayKarakter();
            daftarKarakter[i]->tampilInfo();

            return;
        }
    }

    cout << "Karakter tidak ditemukan.\n";
}


void hapusData() {

    string target;

    cout << "Nama karakter yang ingin dihapus: ";
    cin.ignore();
    getline(cin, target);

    for (int i = 0; i < jumlahKarakter; i++) {

        if (daftarKarakter[i]->getNama() == target) {

            delete daftarKarakter[i];

            for (int j = i; j < jumlahKarakter - 1; j++)
                daftarKarakter[j] = daftarKarakter[j + 1];

            jumlahKarakter--;

            cout << "Data berhasil dihapus.\n";
            return;
        }
    }

    cout << "Nama tidak ditemukan.\n";
}


int main() {

    int pilihan;

    while (true) {

        tampilMenu();

        cin >> pilihan;

        if (pilihan == 1) tambahKisah();
        else if (pilihan == 2) lihatDiary();
        else if (pilihan == 3) ubahData();
        else if (pilihan == 4) hapusData();
        else if (pilihan == 5) break;
        else cout << "Pilihan tidak valid.\n";
    }

    return 0;
}