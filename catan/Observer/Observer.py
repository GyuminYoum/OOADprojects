

# SINGLETON AND OBSERVER
class Observer:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

    def update(self, message):
        # TODO: implement this method
        pass
