// Victor Lejon 200210315552
// u3_main.cpp

#include <iostream>
#include <string>
#include "creature.h"
#include "trilloch.cpp"
#include "quraphon.cpp"
#include <vector>


void add_creature(string odjurstyp, std::vector<Creature *>& creatures)
{
    if (odjurstyp == "trilloch"){
        creatures.push_back(Trilloch::getInstance()); // Skapa ny instans av monster
    }
    else if(odjurstyp == "quraphon"){
        creatures.push_back(Quraphon::getInstance());
    }
    else
        return;
    std::cout << "Nytt odjur skapas!\n";
}

void attack_all()
{
    std::cout << "Alla odjur attackerar!\n";
}

void print_all(std::vector<Creature *>& creatures)
{
    std::cout << "Skriver ut status på alla odjur! (odjurstyp och status)\n";
    for (Creature* c : creatures){
        std::cout << c->print_creature() << std::endl;
    }
}

void remove_creature() 
{
    std::cout << "Tar bort alla förekomster av odjurstypen!\n";
}

int main(int argc, const char * argv[])
{
    // Här deklareras samlingen "creatures"
  std::vector<Creature *> creatures;
    
  char kommando;
  std::string odjurstyp;
  
  do {
    /*
     Kommandon:
     'n' - Nytt odjur: odjurstyp ("quaraphon"/"trilloch")
     's' - Skriv ut status på alla odjur (odjurstyp och status)
     'a' - Låt alla odjur attackera
     'r' - Ta bort alla förekomster av en odjurstyp ("quaraphon"/"trilloch")
     'q' - Avsluta
     */
    std::cout << "Kommando: ";
    std::cin >> kommando;
    switch (kommando) {
      case 'n':
        std::cin >> odjurstyp;
        add_creature(odjurstyp, creatures);
        break;
      case 'a':
        attack_all();
        break;
      case 's':
        print_all(creatures);
        break;
      case 'r':
        std::cin >> odjurstyp;
        remove_creature();
        break;
    }
  } while (kommando != 'q');
  
  std::cout << "Hejdå!" << std::endl;

  
}
