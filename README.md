# PetriNet-MIPS-simulation
This projects simulates the petri net mode for simplified MIPS processor 

The aim is to create a cycle by cycle snapshot of the petrinet simulation of the MIPS processor.
The first step is to fetch and decode the first instruction from the input file for intructions named "instructions.txt".
Depending on the register values to be read mentioned in the instruction, the data is fetched from the registers and these
initial values are initialized in another input file named "register.txt".
Lastly, we also have a data memory named "datamemory.txt" for load instructions from the main memory.

The simplified version of MIPS supports only 5 basic operations namely:
ADD,SUB,AND,OR and LOAD.

The output is written to a file named "simluation.txt". 
