#ifndef CREATURE_H
#define CREATURE_H

#include <string>
using namespace std;

class Creature
{
    public:
    virtual ~Creature(){};
    virtual string print_creature(){};
    string get_name(){
        return type;
    }
    
    
    protected:
    Creature(string type):type(type){} // TIllåt subklasser att skapa object
    void attack();
    void move();
    void stop();
    
    private:
    string type; // typnamn
    
    Creature(const Creature& other) = delete; // förbjud tilldelning/kopiering
    const Creature& operator=(const Creature& other) = delete;
};

#endif