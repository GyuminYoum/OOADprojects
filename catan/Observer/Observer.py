

# SINGLETON AND OBSERVER
class Observer:
    _instance = None

# add comments about singleton
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

    def update(self, message):
        # any messages sent to the observer are appended to gameLogs.txt
        with open('gameLogs.txt', 'a') as file:
            file.write(message)
        print(message)

