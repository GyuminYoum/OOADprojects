from Commands.Command import Command


class buyCard(Command):
    def __init__(self):
        super().__init__()

    # TODO: use sim.observer.update(message) to send messages from Build to the observer
    # MVC
    def execute(self, sim):
        done = False
        while not done:
            print(sim.current_player.name, sim.current_player.resources)
            if not len(sim.deck):
                print("No more development card available for purchase")
                break
            if sim.current_player.canBuyCard():
                response = input('Do you want to buy a development card? (Y/N) \n')
                if response == 'Y':
                    card1 = sim.buyCard(sim.current_player)
                    if card1 is not None:
                        sim.update(f'{sim.current_player.name} successfully bought a {card1.name} card.')
                        print(sim.current_player.name, sim.current_player.AvailableCardSummary(sim.turn))

                elif response == 'N':
                    break
                else:
                    print("Invalid Input")

                if sim.current_player.checkifWin():
                    sim.done = True
                    done= True
                    break
            else:
                print(f'{sim.current_player.name} doesn\'t have enough resources to buy a development card')
                print(f'Exiting buy CardPhase for {sim.current_player.name}.')
                print("")
                break








