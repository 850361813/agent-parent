#!/usr/bin/env python
# -*- coding: utf-8 -*-
import os
import urllib
import urllib2
from multiprocessing import Process
from collections import defaultdict

import sys


def run_proc(name):
    print('Run child process %s (%s)...' % (name, os.getpid()))


if __name__ == '__main__':
    print len(sys.argv)
    if len(sys.argv) == 2:
        print sys.argv[1]
    else:
        print 0