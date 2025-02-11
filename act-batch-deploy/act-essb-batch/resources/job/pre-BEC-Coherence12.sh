#!/bin/ksh
BEC_PATH=$1
set -x
preClasspath=/apps/common/lib/coherence-12.1.2-0-0.jar

shift 1
export CLASSPATH=${preClasspath}
cd ${BEC_PATH}
./fw-BEC.sh $@
