import os


cmd = r'icacls "C:\Users\souravmodak\Documents\demandDraft\Workspace" /grant souravmodak:(OI)(CI)F /T'
os.system(cmd)
os.system("pause")