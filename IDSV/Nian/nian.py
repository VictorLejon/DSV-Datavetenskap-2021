## Victor Lejon


## Parameters:
# (str) w: string
#
## Returns:
# (list): list of all chars in string
def split(w):
    return [l for l in w]

## Returns:
# (list) w_list: list of letters input by user
# (str) sc: special character
def getInput():
    w = input("Nian: ")
    w_list = split(w)
    sc = w_list[len(w_list)//2]
    return w_list, sc

## Parameters:
#   (str) p: path to file
#
## Returns:
# (2d list) d_w_list: 2d list containing letters in each word from dictionary 
def readFile(p):
    d_file = open(p)
    d = d_file.read().split("\n")
    d.pop()
    d_w_list = []
    for w in d:
        d_w_list.append(split(w))
    return d_w_list


w_list, sc = getInput()
d_w_list = readFile("svenskaOrdUTF-8.txt")

# List declarations
m_w_list = []
f_m_w_list = []

## Iterate thorugh words in dictionary
for d_w in d_w_list:
    c_w_list = list(w_list)
    m_len = len(c_w_list)
    correct = True
    ## Iterate thorugh letters in words
    for l in d_w:
        ## If letter exists in useable letters
        if (l not  in c_w_list):
            correct = False
            break
        else:
            c_w_list.remove(l)

    ## Add to correct words list if requirements met
    if ("s" in d_w and len(d_w) >= 4 and correct):
        m_w_list.append(d_w)
        if (len(d_w) == m_len):
            f_m_w_list.append(d_w)
            

# Print results
for w in m_w_list:
    print("".join(w))

print("\n")
print(str(len(f_m_w_list)) + " ord använder alla bokstäver:")
for w in f_m_w_list:
    print("".join(w))





