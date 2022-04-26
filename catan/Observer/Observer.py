

# SINGLETON AND OBSERVER
class Observer:
    _instance = None

    # Singletons in Python can be made by editing the __new__ method.
    # Editing __new__ lets us make sure only 1 instance of Observer is instantiated.
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

    # Wipes the game logs clean when a new game is run
    def __init__(self):
        open('gameLogs.txt', 'w').close()

    # Observer publishes output to a game log text file
    def update(self, message):
        # any messages sent to the observer are appended to gameLogs.txt
        with open('gameLogs.txt', 'a') as file:
            file.write(message+"\n")
        print(message)

