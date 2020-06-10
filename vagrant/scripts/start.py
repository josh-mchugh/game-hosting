#!/usr/bin/python

import subprocess

process = subprocess.Popen(["java", "-jar", "-Xms1G", "-Xmx1G", "server.jar", "--nogui"], cwd="/home/vagrant/server", stdout=subprocess.PIPE, stderr=subprocess.PIPE)

while True:
  if process.poll() is not None:
    break
  line = process.stdout.readline().strip()
  print line
  if "Done" in line:
    break
  

process.poll()