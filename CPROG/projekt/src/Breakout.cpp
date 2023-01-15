// Spel
#include "GameEngine.h"
#include "Player.h"
#include "Brick.h"
#include "Ball.h"


GameEngine* gEngine = nullptr;
Ball* ball = nullptr;
Player* player = nullptr;
std::vector<Brick *> bricks;

void create_player(){
	player = Player::getInstance("Player", "Player.bmp", gEngine->get_width()/15, 10, gEngine->get_width()/2 - 50, gEngine->get_height()-100);
	gEngine->add_sprite(player);
}

void remove_brick(Brick* brick){
	gEngine->remove_sprite(brick);
	delete brick;
}

void set_map(){
	// Create 4 columns with different colours
	std::vector<std::string> brick_images = {"Blue_Brick.bmp", "Orange_Brick.bmp", "Pink_Brick.bmp", "Green_Brick.bmp"};
	
	for (int i = 0; i < 4; i++){
		for (int j = 0; j < gEngine->get_width()/80; j++){
			Brick* brick = Brick::getInstance("Brick", brick_images[i], 80, 30, j*80, 30 + 50*i);
			bricks.push_back(brick);
			brick->collide("Ball", [brick](){remove_brick(brick);});
			gEngine->add_sprite(brick);
		}
	}
}

void player_bounce(){
	if (ball->get_velocity().xVel > 0){
		if (player->get_direction() == 1)
			ball->bounce(1, -1);
		else
			ball->bounce(-1, -1);
	}
	else{
		if (player->get_direction() == 1)
			ball->bounce(-1, -1);
		else
			ball->bounce(1, -1);
	}
}

void ball_bounce(){
	ball->bounce(1, -1);
}

void create_ball(){
	ball = Ball::getInstance("Ball", "Ball.bmp", 10, 10, gEngine->get_width()/2-10, gEngine->get_height()/4);
	ball->collide("Player", [](){player_bounce();});
	ball->collide("Brick", [](){ball_bounce();});
	gEngine->add_sprite(ball);
}

void setup(){
	// Set fps
	gEngine->set_fps(30);

	// Create sprites
	create_player();
	set_map();
	create_ball();
}

void check_ball_bounce(){
	int border = ball->checkBorderCollision(gEngine->get_width(), gEngine->get_height());
	if (border == -2)
		std::cout << "loss";
	else if (border != 0)
		ball->border_collide(border, gEngine->get_width());
}

void stop_player(){
	player->stop_at_border(gEngine->get_width(), gEngine->get_height());
}

int main(int argc, char *argv[]) {

	gEngine = new GameEngine();
	
	gEngine->start("Breakout", 1920, 1080, 0);
	setup();
	while (gEngine->running())
	{
		gEngine->event_handler(); // Hanterar events
		gEngine->update(); // Uppdaterar alla sprites
		check_ball_bounce();
		stop_player();
		gEngine->render(); // Renderar ut sprites
	}
	
	gEngine->stop();
	delete gEngine;
	return 0;
}


