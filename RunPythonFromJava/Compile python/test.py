import os
import re 
import sys

print("Not following command line argument mode")
img_name = sys.argv[1]
file_out_name = sys.argv[2]

cmd = "tesseract "+img_name+" "+file_out_name
os.system(cmd)

print("Reading from file")
filehandle = open(file_out_name+".txt", 'r')
while True:
    line = filehandle.readline()
    if not line:
        break
    print(line)
os.system("pause")