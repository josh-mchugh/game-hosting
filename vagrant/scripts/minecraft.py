#!/usr/bin/python

import os
import sys
import subprocess
import select


def server_command(cmd):
  os.system('screen -S minecraft -X stuff "{}\015"'.format(cmd))

def tailLogs(ws, endLog, message):
  f = subprocess.Popen(['tail','-F', "-n +1", "/home/vagrant/server/logs/latest.log"], stdout=subprocess.PIPE, stderr=subprocess.PIPE)
  p = select.poll()
  p.register(f.stdout)

  while True:
    if p.poll(1):
      log = f.stdout.readline().strip()
      ws.send(log)
      if endLog in log:
        ws.send(message)
        break 

def start(ws):
  if not status():
    subprocess.Popen(["screen", "-dmS", 'minecraft', "java", "-jar", "-Xms3g", "-Xmx4g", "server.jar", "--nogui"], cwd="/home/vagrant/server", stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    tailLogs(ws, "Done", "Server Started")
  else:
    ws.send("Server already started.")

def stop(ws):
  if status():
      server_command('stop')
      tailLogs(ws, "(world): All chunks are saved", "Server stopped.")
  else:
      ws.send("Server not started")

def status():
  output = os.popen('screen -ls').read()
  if '.minecraft' in output:
      return True
  else:
      return False

def serverMsg(ws, msg):
  server_command("/tell @a " + msg)
  ws.send("Message sent")

def serverStatus(ws):
  ws.send("Server is running.") if status() else ws.send("Server is not running.")