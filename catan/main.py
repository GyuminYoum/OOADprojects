from sim import *
from Commands.Trade import *
from Commands.Invoker import *


sim1 = sim()
sim1.initialize()

invoker = Invoker()
# build = Build()
# invoker.set_command(build)
trade = Trade()
invoker.set_command(trade)

sim1.set_invoker(invoker)
sim1.invoker.execute_command(sim1)
