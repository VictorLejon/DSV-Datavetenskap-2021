// Spel
#ifndef BRICK_H
#define BRICK_H

#include "Sprite.h"

class Brick : public Sprite{

    public:
        static Brick* getInstance(std::string type, std::string img_path, int width, int height, float _x, float _y); // Fabrik

        void update();

    private:
        Brick(std::string type, std::string img_path, int width, int height, float _x, float _y):
                                Sprite(type, img_path, width, height, _x, _y){
                                    
                                            } ; // Förbjud objektskapande annt än genom getInstance, inga subklasser

};

#endif