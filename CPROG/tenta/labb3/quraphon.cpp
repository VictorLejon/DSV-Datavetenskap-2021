#include "creature.cpp"

class Quraphon : public Creature
{
     
    public:
        static Quraphon* getInstance(){
            return new Quraphon("quraphon"); // Fabrik
        }
        string print_creature(){
            if (isBiting)
                return "quraphon biter";
            return "quraphon biter inte";
        }
        void attack(){
            isBiting = true;
        }
        void stop(){
            isBiting = false;
        }
        void moves(){
            isBiting = false;
        }
        
    private:
        bool isBiting = false;
        Quraphon(std::string type):Creature(type){} // Tillåt bara konstruktion från fabrik
};