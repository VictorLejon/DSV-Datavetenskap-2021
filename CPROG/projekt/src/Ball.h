// Spel
#ifndef BALL_H
#define BALL_H

#include "Sprite.h"
#include <random>

class Ball : public Sprite{

    public:
        static Ball* getInstance(std::string type, std::string img_path, int width, int height, float _x, float _y); // Fabrik

        void update();

        bool get_bounced(){return bounced;};
        void border_collide(int, int);
        void bounce(int, int);

        void set_bounced(bool b){bounced = b;};
        

    private:
        bool isMoving = false;
        bool bounced = false;

        void init(){
            this->set_start_movement();
        }
        
        void set_collisions();
        void set_start_movement();
        void start_movement();
        Ball(std::string type, std::string img_path, int width, int height, float _x, float _y):
                            Sprite(type, img_path, width, height, _x, _y){
                                                this->init();
                                            } ; // Förbjud objektskapande annt än genom getInstance, inga subklasser
};

#endif