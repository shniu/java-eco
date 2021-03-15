#!/bin/bash

echo -e "============= Startup Namesrv ============="
echo -e "Start namesrv1, the listen port is 9876"
nohup mqnamesrv -c namesrv-9876.properties > /tmp/namesrv-9876.log 2>&1 &

echo -e "Start namesrv2, the listen port is 9877"
nohup mqnamesrv -c namesrv-9877.properties > /tmp/namesrv-9877.log 2>&1 &

echo -e "Start namesrv3, the listen port is 9878"
nohup mqnamesrv -c namesrv-9878.properties > /tmp/namesrv-9878.log 2>&1 &

sleep 5s

echo -e "============= Startup Broker ============="

originRocketHome=$ROCKET_HOME

export ROCKET_HOME=/Volumes/disk_slm/workspace/osc-research/mine/java-eco/docs/rocketmq/2m-2s-async/broker-a-m
echo -e "RocketMQ home is: $ROCKET_HOME"
echo -e "Start broker-a master, the listen port is 10911"
nohup mqbroker -c broker-a.properties > /tmp/broker-a-m.log 2>&1 &

export ROCKET_HOME=/Volumes/disk_slm/workspace/osc-research/mine/java-eco/docs/rocketmq/2m-2s-async/broker-a-s
echo -e "RocketMQ home is: $ROCKET_HOME"
echo -e "Start broker-a slave, the listen port is 11911"
nohup mqbroker -c broker-a-s.properties > /tmp/broker-a-s.log 2>&1 &

export ROCKET_HOME=/Volumes/disk_slm/workspace/osc-research/mine/java-eco/docs/rocketmq/2m-2s-async/broker-b-m
echo -e "RocketMQ home is: $ROCKET_HOME"
echo -e "Start broker-a slave, the listen port is 12911"
nohup mqbroker -c broker-b.properties > /tmp/broker-b-m.log 2>&1 &

export ROCKET_HOME=/Volumes/disk_slm/workspace/osc-research/mine/java-eco/docs/rocketmq/2m-2s-async/broker-b-s
echo -e "RocketMQ home is: $ROCKET_HOME"
echo -e "Start broker-a slave, the listen port is 13911"
nohup mqbroker -c broker-b-s.properties > /tmp/broker-b-s.log 2>&1 &

export ROCKET_HOME="$originRocketHome"
echo -e "Restore origin RocketMQ home to $ROCKET_HOME"