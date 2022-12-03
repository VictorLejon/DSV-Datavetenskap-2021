// Victor Lejon
#include <iostream>
#include <vector>

struct Person
{
  std::string fnamn;
  std::string enamn;
  std::string nummer;
};

void visa_person (std::vector<Person>& telefonreg, std::string& fnamn)
{
    // Denna funktion tar emot ett telefonregister och ett förnamn på den person-post som skall visas. 
    
    // Om det finns en eller flera person-poster med detta förnamn i registret, 
    // så skall telefonnumret till dessa visas, på formen: "förnamn efternamn: telefonnummer".

    // Check för om en person hittats
    bool hittadePerson = false;

    for (int i = 0; i < telefonreg.size(); i++)
    {
        if (telefonreg[i].fnamn == fnamn)
        {
            std::string enamn = telefonreg[i].enamn;
            std::string nummer = telefonreg[i].nummer;
            std::cout << fnamn << " " << enamn << ": " << nummer << std::endl;

            hittadePerson = true;
        }
    }

    // Om ingen person-post hittas med det sökta förnamnet, så skall följande skrivas ut.
    if (!hittadePerson)
    {
    std::cout << "Hittade inget nummer!" << std::endl;
    }
    
}

void skriv_hela_telefonreg (std::vector<Person>& telefonreg)
{
    // Denna funktion tar emot ett telefonregister och visar samtliga poster i 
    // telefonregistret på formen: "förnamn efternamn: telefonnummer".
    
    for (int i = 0; i < telefonreg.size(); i++)
    {
        std::string enamn = telefonreg[i].enamn;
        std::string nummer = telefonreg[i].nummer;
        std::string fnamn = telefonreg[i].fnamn;
        std::cout << fnamn << " " << enamn << ": " << nummer << std::endl;
    }
}

void ny_person (std::vector<Person>& telefonreg, std::string& fnamn, std::string enamn, std::string& nummer)
{
    // Denna funktion tar emot ett telefonregister och ett förnamn, ett efternamn och 
    // ett telefonnummer till den nya person-post som skall skapas och läggas till i telefonboken, 
    // därefter läggs person-posten till registret.
    Person p;
    p.fnamn = fnamn;
    p.enamn = enamn;
    p.nummer = nummer;

    telefonreg.push_back(p);
}

void ta_bort_person (std::vector<Person>& telefonreg, std::string& fnamn)
{
    // Denna funktion tar emot ett telefonregister och ett förnamn på den person-post 
    // som skall tas bort ur registret.
    // Om det finns fler person-poster med samma förnamn, så tas alla dessa bort.
    
    // När en person-post tas bort så skrivs namnet på person-posten ut på skärmen (förnamn efternamn), 
    // men ingen användardialog skall finnas, dvs. användaren skall inte bekräfta borttag.
    
    for (int i = 0; i < telefonreg.size(); i++)
    {
        if (telefonreg[i].fnamn == fnamn)
        {
            std::string enamn = telefonreg[i].enamn;
            std::string nummer = telefonreg[i].nummer;
            std::cout << fnamn << " " << enamn << " tas nu bort." << std::endl;
            telefonreg.erase(telefonreg.begin()+i);
        }
    }
}

int main ()
{
    // Dessa personer skall finnas med som person-poster i telefonregistret.
    /*
    "Christina", "Nyberg","0707423653"
    "Josefin", "Danielsson", "0705463245"
    "Ellen", "Lindgren", "0705643229"
    "Stig", "Ek", "0705673247"
    "Linus", "Jonasson", "0703457923"
    "Adam", "Nordin", "0203456297"
    */
    
    // Här ska du deklarera den variabel som skall innehålla ditt register!
    std::vector<Person> telefonreg = { 
        {"Christina", "Nyberg","0707423653"},
        {"Josefin", "Danielsson", "0705463245"},
        {"Ellen", "Lindgren", "0705643229"},
        {"Stig", "Ek", "0705673247"},
        {"Linus", "Jonasson", "0703457923"},
        {"Adam", "Nordin", "0203456297"}
        };
    
    // Här skall en liten kommandostyrd huvudmeny skrivas. Observera att vissa kommandon
    // behöver fler värden som skall matas in. I menyn skall följande val 
    // av Kommandon finnas:
    //  Ny person               - 'n' förnamn efternamn telefonnummer
    //  Visa en person          - 'v' förnamn
    //  Skriv hela telefonboken - 's'
    //  Ta bort en person       - 'd' förnamn
    //  Avsluta                 – 'q'

    while (true)
    {
        std::cout << "Kommando: ";
        char kommando;
        std::cin >> kommando;
        std::string fnamn, enamn, nummer;

        switch (kommando)
        {
        case 'n':
            std::cin >> fnamn >> enamn >> nummer;
            ny_person(telefonreg, fnamn, enamn, nummer);
            break;
        case 'v':
            std::cin >> fnamn;
            visa_person(telefonreg, fnamn);
            break;
        case 's':
            skriv_hela_telefonreg(telefonreg);
            break;
        case 'd':
            std::cin >> fnamn;
            ta_bort_person(telefonreg, fnamn);
            break;
        case 'q':
            std::cout << "Hejdå!";
            return 0;
            break;
        default:
            break;
        }
    }
    
    return 0;
}

