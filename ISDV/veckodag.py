# Victor Lejon (200210315552)


def solve():
    d_list = ["Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"]

    ## Get input
    (iy, im, id) = getInputs()

    ## Check if Jan or Feb
    if (im == 1 or im == 2):
        im += 12
        iy += -1

    ## Insert into Zeller's Congruence
    d = (id + 13*(im+1)//5 + iy + iy//4 - iy//100 + iy//400)%7

    print("\nIt is a " + d_list[d])
    return


## Returns:
#
#   (int) id: Day user input
#   (int) im: Month user input
#   (int) iy: Year user input
def getInputs():

    ## Set standard limits
    y_limit_min = 1583
    y_limit_max = 9999

    m_limit_min = 1
    m_limit_max = 12

    d_limit_min = 1
    d_limit_max = 31

    iy = getInput("Year", y_limit_max, y_limit_min)
    im = getInput("Month", m_limit_max, m_limit_min)

    ## Regulates day max
    if (im == 2):
        # Leap year check
        if ((iy%400 == 0) or (iy%4 == 0 and iy%100 != 0)):
            d_limit_max = 29
        else:
            d_limit_max = 28
    elif (im in [4, 6, 9, 11]):
        d_limit_max = 30
    id = getInput("Day", d_limit_max, d_limit_min)
    
    return (iy, im, id)

## Parameters:
#
#   (str) input_name: Text showing user what input is needed
#   (int) limit_max: Max input size limit
#   (int) linit_min: Min input size limit
#
## Returns:
#
#   (int) i: User input
def getInput(input_name, limit_max, limit_min):
    ## Loop for continuous asking on incorrect input
    while True:

        ## Check for integer
        try:
            i = int(input(input_name+": "))
        except:
            ## If not integer set to 0 leading to fail
             i = 0   

        ## Check limits
        if (i >= limit_min and i <= limit_max):
            ## Break loop on correct interval
            break
        else:
            print("Out of allowed range " + str(limit_min) + " to " + str(limit_max))
    return i

solve()
