// Spel
#ifndef PLAYER_H
#define PLAYER_H

#include <string>
#include "Sprite.h"

class Player : public Sprite{


    public:
        static Player* getInstance(std::string type, std::string img_path, int width, int height, float _x, float _y); // Fabrik
        void update();
        void stop_at_border(int, int); // Stops player when reaching border of screen
        int get_direction(){return direction;};
    private:
        int direction = 1;
        // Förbjud objektskapande annt än genom getInstance, inga subklasser
         Player(std::string type, std::string img_path, int width, int height, float _x, float _y):
                                Sprite(type, img_path, width, height, _x, _y){
                                    set_controls();
                                } 
        
        void set_controls();
        void move_left();
        void move_right();
        
};

#endif