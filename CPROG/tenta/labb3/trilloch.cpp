#include "creature.cpp"

class Trilloch : public Creature
{
     
    public:
        static Trilloch* getInstance(){
            return new Trilloch("trilloch"); // Fabrik
        } 
        
    private:
        bool isFlying = false;
        Trilloch(std::string type):Creature(type){} // Tillåt bara konstruktion från fabrik
};