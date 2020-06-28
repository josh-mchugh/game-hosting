#!/usr/bin/python

from subprocess import PIPE
import psutil

def server_command(cmd):
  p = psutil.Popen(["screen", "-S", "minecraft", "-X", "stuff", "{}\015".format(cmd)], stdout=PIPE, stderr=PIPE)

def start():
  if not status():
    p = psutil.Popen(["screen", "-dmS", "minecraft", "java", "-jar", "-Xms3g", "-Xmx4g", "server.jar", "--nogui"], cwd="/home/vagrant/server", stdout=PIPE, stderr=PIPE)
    return "Server started"
  
  return "Server already started"

def stop():
  if status():
      server_command("stop")
      return "Server stoppped"
  
  return "Server not started"

def status():
  p = psutil.Popen(["screen", '-ls'], stdout=PIPE, stderr=PIPE)
  output = p.communicate()[0]
  if ".minecraft" in output:
      return True
  
  return False

def tell(msg):
  server_command("/tell @a " + msg)
  return "Message sent"