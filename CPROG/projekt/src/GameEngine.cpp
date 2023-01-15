// Spelmotor
#include "GameEngine.h"

GameEngine::~GameEngine(){

}

void GameEngine::start(const char* title, int width, int height, bool fullscreen){

    // Width / Height
    int flags = 0;
    if(fullscreen)
        flags = SDL_WINDOW_MAXIMIZED;
    
    else{
        this->win_width = width;
        this->win_height = height;
    }


    // Intialize sdl
    if (SDL_Init(SDL_INIT_EVERYTHING) == -1) {
		cerr << "SDL_Init-fel: " << SDL_GetError() << endl;
		exit(-1);
	}

    // Create window
	win = SDL_CreateWindow(title,
	SDL_WINDOWPOS_CENTERED,SDL_WINDOWPOS_CENTERED,
	width,height,flags);

    // Check for errors
	if (win == nullptr) {
		cerr <<"Fel: " <<SDL_GetError() <<endl;
		SDL_Quit();
		exit(-1);
	}

    // Set values if fullscreen
    if (fullscreen){
        SDL_DisplayMode DM;
        SDL_GetCurrentDisplayMode(0, &DM);
        this->win_height = DM.h;
        this->win_width = DM.w;
    }
	
    // Create renderer
	ren = SDL_CreateRenderer(win, -1,
	SDL_RENDERER_PRESENTVSYNC);

    // Check for errors
	if (ren == nullptr) {
		cerr << "Fel: " << SDL_GetError() << endl;
		SDL_DestroyWindow(win);
		SDL_Quit();
		exit(-1);
	}

    // Set is running to true
    std::cout << "Game started" << std::endl;
    isRunning = true;
}

void GameEngine::update(){

    nextTick = SDL_GetTicks() + tickInterval; // FPS

    // Uppdatera alla sprites tillstånd 
    for (Sprite* s : sprites)
        s->update();
    
    // Kollar efter kollisioner mellan sprites
    for (Sprite* s : sprites){
        for (Sprite* o : sprites){
            if (s == o)
                continue;
            if (s->checkSpriteCollision(o)){
                s->run_collide(o->get_type());
                o->run_collide(s->get_type());
            }
        }
    }

    delay = nextTick - SDL_GetTicks(); // FPS
    if (delay > 0)
        SDL_Delay(delay);
}

void GameEngine::event_handler(){

    // Handle events
    SDL_Event event;
    SDL_PollEvent(&event);
    switch (event.type)
    {
    case SDL_QUIT: // if close
        isRunning = false;
        break;
    case SDL_KEYDOWN:
        handle_keydown(event.key.keysym.sym);
        break;
    default:
        break;
    }

}

void GameEngine::render(){
    SDL_RenderClear(ren);

    // Render all sprites
    for (Sprite* s : sprites){
        SDL_Rect src = {0, 0, 50, 50};
        SDL_Rect dest = {s->get_position()._x, s->get_position()._y, s->get_size().width, s->get_size().height};
        SDL_RenderCopy(ren, s->get_texture(), &src, &dest);
    }
        
    SDL_RenderPresent(ren);
}

void GameEngine::stop(){
    SDL_DestroyWindow(win);
    SDL_DestroyRenderer(ren);
    SDL_Quit();
    std::cout << "Game stopped";
}

// Adds sprite and loads texture for sprite
void GameEngine::add_sprite(Sprite* sprite){
    sprite->set_tx(TextureLoader::load_texture(sprite->get_image_path(), ren)); // Loads image and sets texture
    this->sprites.push_back(sprite);
}

void GameEngine::remove_sprite(Sprite* sprite){
    // Remove/erase idiom
    sprites.erase(remove(sprites.begin(), sprites.end(), sprite), sprites.end());
}

void GameEngine::set_fps(int fps){
    std::cout << fps << std::endl;
    this->tickInterval = 1000/fps;
}

// Run key event to sprites
void GameEngine::handle_keydown(SDL_Keycode& key_code){
    for (Sprite* sprite : sprites){
        sprite->run_key_event(SDL_GetKeyName(key_code));
    }
}