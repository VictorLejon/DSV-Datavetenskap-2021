Spelet byggs med makefilen och kommandot ”make”. 
För att starta spelet kör man applikationen play som ligger i build/debug. Det är en simpel 
version av atari breakout. Bollen börjar röra sig när spelaren trycker på ”M” knappen och 
man använder sig av ”A” och ”D” för att röra sin platform höger respektive vänster. Målet är 
att slå sönder alla brickor på planen. Inget särskilt händer när man vinner eller förlorar, då 
får man starta om spelet själv. 
Om man vill bygga en egen applikation/spel med spelmotorn skapar man en instans av 
GameEngine, kör funktionen start som skapar fönster/renderare, sedan skapar man en loop 
som kallar på medlemsfunktionerna event_handler, update så länge spelet är på. I loopen 
kan man självklart lägga till fler funktioner som sköter de objekten man skapat i sin 
implementation. Update funktionen kommer uppdatera alla sprites tillstånd genom att ta 
hand om alla kollisioner samt köra den abstrakta metoden update som ska implementeras i 
varje sprite. 