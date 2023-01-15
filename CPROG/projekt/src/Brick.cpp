// Spel
#include "Brick.h"

Brick* Brick::getInstance(std::string type, std::string img_path, int width, int height, float _x, float _y){
    return new Brick(type, img_path, width, height, _x, _y);
}

void Brick::update(){
    
}
