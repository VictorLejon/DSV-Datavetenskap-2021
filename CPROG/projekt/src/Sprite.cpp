// Spelmotor
#include "Sprite.h"

void Sprite::set_movement(Vel v){
    this->velocity = v;
}

void Sprite::set_position(Pos p){
    this->position = p;
}

void Sprite::set_size(Size s){
    this->size = s;
}

void Sprite::set_tx(SDL_Texture* texture){
    this->tx = texture;
}

bool Sprite::checkSpriteCollision(Sprite* other){
    if (this->position._x + this->size.width >= other->get_position()._x && this->position._x + this->size.width <= other->get_position()._x + other->get_size().width) // X Interval
            if (this->position._y + this->size.height >= other->get_position()._y && this->position._y + this->size.height <= other->get_position()._y + other->get_size().width) // y interval
                return true;
    return false;
}

int Sprite::checkBorderCollision(int width, int height) const{

     // Touching left border
    if (this->position._x <= 0){
        return 1;
    }

    // Touching right border
    if (this->position._x + this->size.width >= width){
        return 2; 
    }

    // Touching top border
    if (this->position._y <= 0){
        return -1; 
    }
    
    // Touching bottom border
    if (this->position._y + this->size.height >= height){
        return -2; 
    }
    
    return 0; // Not touching border
}

void Sprite::move(){
    // New pos = current + speed; for x and y
    Pos newPos = {this->get_position()._x + this->get_velocity().xVel, this->get_position()._y + this->get_velocity().yVel};
    set_position(newPos); 
}

// Binds key to function
void Sprite::keybind(std::string key, std::function<void()> func){
    // If key is already bound, remove then set new
    if (this->keybind_functions.count(key) > 0){
        this->keybind_functions.erase(key);
    }
    this->keybind_functions.emplace(key, func);
}

// Runs function on key down if key is mapped
void Sprite::run_key_event(std::string key){
    if (this->keybind_functions.count(key) > 0)
        this->keybind_functions.find(key)->second();
}

// Binds collision to another type of sprite
void Sprite::collide(std::string other_type, std::function<void()> func){
        // If key is already bound, replace
        if (this->collision_functions.count(other_type) > 0){
            this->collision_functions.erase(other_type);
        }
        this->collision_functions.emplace(other_type, func);
}

// Runs collision function
void Sprite::run_collide(std::string other_type){
    if (this->collision_functions.count(other_type) > 0)
        this->collision_functions.find(other_type)->second();
}