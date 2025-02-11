#!/bin/ksh
job=essb-grid-loader

mode=FORCE_FROM_STEP01
env=wk05

appSubcategory=act
bduShortname=essb
bduLongname=EntSalesSvcsBatch
SCHEDULER_INSTNC_ID=01n62
appHome=/work/users/$env$appSubcategory/$bduShortname

dataHome=/work/users/$env$appSubcategory/$bduShortname
becHome=/apps/common/scripts/$env

echo appHome=${appHome}

#-----------------------------
$becHome/fw-BEC.sh   $mode   ENV=$env                               \
  JOB=$job                                                           \
  LEVEL1_SHORTNAME=$appSubcategory   LEVEL2_SHORTNAME=$bduShortname  \
  APPID=$bduLongname  APP_HOME=$appHome  D=configAppCtxFile=appCtx.properties   \
  D=tangosol.coherence.override=/apps/common/env/$env/EntSalesSvcsBatch/ess-coherence-override.xml \
  D=tangosol.coherence.cacheconfig=/apps/common/env/%%G_ENV/EntSalesSvcsBatch/ess-coherence-cache-config.xml \
  D=tangosol.coherence.localhost=$hostname  \
  D=tangosol.coherence.distributed.localstorage=false \
  D=tangosol.coherence.mode=PROD      DATA_HOME=$dataHome       \
  SCHEDULER_INSTNC_ID=$SCHEDULER_INSTNC_ID   END_OF_PARMS
