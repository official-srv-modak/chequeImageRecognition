import os


if not os.path.exists("output.txt"):
    os.system(r'java -classpath bin ExtractImageData ExtractImageData')

filehandle = open("output.txt", 'r')

os.system(r'java -classpath bin ExtractImageData ExtractFromFile')
os.system("pause")
