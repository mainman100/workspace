class Led:
    def __init__(self, index):
        self.index = index
        
    def light(self):
        print "Led {} lighting".format(self.index)

class DoorBell:
    def ring(self):
        print "Door ringing"
        
class Fan:
    def loop(self):
        print "Fan looping"

class LedGenerator:
    def __init__(self, range_):
        self.range_ = range_
        self.freeLeds = set(range(range_))
    def getNextLed(self):
        led = self.freeLeds.pop()
        if len(self.freeLeds) == 0:
            self.freeLeds = set(range(self.range_))
        return led

leds = [Led(i) for i in range(16)]
ledGen = LedGenerator(len(leds))
door = DoorBell()
fan = Fan()


for i in range(80):
    bin_ = ledGen.getNextLed()
    if i % 5 == 0:
        leds[bin_].light()

