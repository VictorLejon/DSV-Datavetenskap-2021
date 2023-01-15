// Spel
#include "Player.h"

Player* Player::getInstance(std::string type, std::string img_path, int width, int height, float _x, float _y){
    return new Player(type, img_path, width, height, _x, _y);
}

void Player::update(){
    this->move(); // needs to be called for sprite to act on movement
}

void Player::stop_at_border(int width, int height){
    if(this->checkBorderCollision(width, height) == 2){
        this->set_movement({ 0, this->get_velocity().yVel });
        this->set_position({width - this->get_size().width, this->get_position()._y});
        return;
    }
    if (this->checkBorderCollision(width, height) == 1){
        this->set_movement({ 0, this->get_velocity().yVel });
        this->set_position({0, this->get_position()._y});
        return;
    }
}

void Player::move_left(){
    direction = -1;
    this->set_movement({-15, 0});
}

void Player::move_right(){
    direction = 1;
    this->set_movement({15, 0});
}

void Player::set_controls(){
    this->keybind("A", [this](){this->move_left();}); // Wrappa funktionen du vill binda till lambda
    this->keybind("D", [this](){this->move_right();});
}



