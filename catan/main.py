from sim import *
from Build import *


sim1 = sim()
sim1.initialize()

invoker = Invoker()
build = Build()
invoker.set_command(build)
sim1.set_invoker(invoker)
sim1.invoker.execute_command(sim1)
