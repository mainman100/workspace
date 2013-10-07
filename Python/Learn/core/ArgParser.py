'''
Created on Jan 20, 2013

@author: loai
'''


import argparse

parser = argparse.ArgumentParser()
# optional arguments are identified by prefixing the argument with -, otherwise it is 
# positional argument
# this is a positional argument, all positional arguments are required, not optional
parser.add_argument("positional", help="a positional argument")
# the optional argument is optional, as the name suggests, but whenever supplied you 
# have to supply some value for it, any value (the value is considered a string)
parser.add_argument("--optional", help="an optional argument")
# you can enforce the expected argument type using the type kwargs argument
parser.add_argument("--optinoalInt", help="an optional int argument", type=int)
# we can even make an option that doesn't require a value(actually doesn't permit),
# that is whenever the option is specified, its value (args.option) will be True,
# False otherwise, this is accomplished using action keyword and store_true value
parser.add_argument("--debug",
                    help="an option that sets its value to True whenever specified",
                    action="store_true")
# we can add short version, the value will be stored in args.log (the longer version)
parser.add_argument("-l", "--log", help="example of combining short and long versions",
                    action="store_true")

# we can limit the values of an argument
parser.add_argument("-t", "--threads", help="number of threads", type=int, choices=[1, 2, 3])

# specify a default value, doesn't apply to positional arguments except with nargs and some stuff
parser.add_argument("-o", "--output", help="output file", default="output.txt")

args = parser.parse_args()

print args.positional
if args.optional:  # will be None if not supplied
    print "Optional argument supplied {}".format(args.optional)
if args.debug:
    print "debugging"
if args.log:
    "logging"
print args.output
