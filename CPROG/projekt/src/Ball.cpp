// Spel
#include "Ball.h"

Ball* Ball::getInstance(std::string type, std::string img_path, int width, int height, float _x, float _y){
    return new Ball(type, img_path, width, height, _x, _y);
}

void Ball::update(){
    if (isMoving){
        this->move();
        bounced = false;
    }
}

void Ball::bounce(int xMultiplier, int yMultiplier){
    this->set_movement({xMultiplier * this->get_velocity().xVel, yMultiplier * this->get_velocity().yVel});
}

void Ball::border_collide(int border, int width){
    switch (border)
    {
    case 1:
        this->bounce(-1, 1);
        this->set_position({0, this->get_position()._y});
        break;
    case 2:
        this->bounce(-1, 1);
        this->set_position({width - this->get_size().width, this->get_position()._y});
        break;
    case -1:
        this->bounce(1, -1);
        this->set_position({this->get_position()._x, 0});
        break;
    default:
        break;
    }
    
}

void Ball::set_start_movement(){
    int xVel = 15;
    int yVel = 15;

    std::cout << xVel << " " << yVel;

    this->set_movement({xVel, yVel});
    this->keybind("M", [this](){start_movement();});
}

void Ball::start_movement(){
    isMoving = true;
}