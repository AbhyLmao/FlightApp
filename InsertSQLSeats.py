# created a simple loop for printing out sql insert operation because I don't want to manually type 90 lines so I copy and paste the output

seatNumber= 1
flightNumber = 1

for id in range(1, 91):
    print("(" + str(id) + ", " + str(seatNumber) +  ", " + str(flightNumber) + ", 1),")
    seatNumber += 1
    if (id % 30 == 0): # whatever is after % is number of seats per flight
        seatNumber = 1
        flightNumber+=1