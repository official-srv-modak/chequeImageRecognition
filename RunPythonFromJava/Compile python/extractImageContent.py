import os
import re 
import sys

if len(sys.argv) < 2:
    img_name = input("Enter the Image name\n")
    file_out_name = input("Enter the output file name\n")
    #cmd = r'icacls "C:\Users\souravmodak\Documents\demandDraft\Workspace" /grant souravmodak:(OI)(CI)F /T'
    #os.system(cmd)
else:
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



