#!/bin/bash

echo -e "Shutdown namesrv ..."
mqshutdown namesrv

echo -e "Shutdown mq broker ..."
mqshutdown broker
