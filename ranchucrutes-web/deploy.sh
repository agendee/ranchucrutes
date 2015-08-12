#!/bin/bash

pwd=$(pwd)

scp -r -i ~/aws-fmma-key.pem $pwd/* ubuntu@ec2-52-25-40-240.us-west-2.compute.amazonaws.com:/usr/share/nginx/html/marcmed.com.br
