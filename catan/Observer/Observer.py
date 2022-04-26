

# SINGLETON AND OBSERVER
class Observer:
    _instance = None

    # __new__
    # Singletons in Python can be made by editing the __new__ method.
    # Editing __new__ lets us make sure only 1 instance of Observer is instantiated.
    # __new__ returns the same _instance of Observer if there is already an existing instance
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

    # __init__
    # usage: Wipes the game logs clean when the Observer is initialized (at the start of the game).
    #        We can safely assume that the file will not be wiped clean while the game is running
    #        because our Observer is a Singleton
    def __init__(self):
        open('Observer/gameLogs.txt', 'w').close()

    # function update
    # usage: Publishes output to a game log text file and prints the output to console as well
    def update(self, message):
        # any messages sent to the observer are appended to gameLogs.txt
        with open('Observer/gameLogs.txt', 'a') as file:
            file.write(message)
            file.write('\n')
        print(message)

