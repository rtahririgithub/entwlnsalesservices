#!/bin/ksh
#################################################################################
#
# mod:  execPowerCenter.sh
#
# ver:  July 9, 2011 - Jeremy Leeder
# ver:  October 12, 2005 - Ron Turner
#
# inf:  Common Template for executing Informatica PowerCenter in batch mode
#
# arg:  1 - executable
#
# arg:  2 - command
#
# arg:  3 - service
#
# arg:  4 - node id
#
# arg:  5 - username
#
# arg:  6 - password (encrypted)
#
# arg:  7 - wait option
#
# arg:  8 - folder
#
# arg:  9 - workflow
#
# arg: 10 - G_POWERCENTER_HOME
#
# arg: 11 - PCNAME
#
# arg: 12 - PI_NAME
#
# arg: 13 - powercenter properties file path
#
# ret:  0 - success
#       non-zero - problem occurred
#
#################################################################################

############################  History ###########################################
# August 01, 2007 - Reza Tahriri
#
#Modified for Informatica version 8.1
#Removed server address and server port
#Added  Service and domain
##################################################################################
# default the return code
RC=0

# do some basic arg checks
#
if [[ $# -eq 13 && ${13} != "NONPARM" ]]; then
        PMCMD=${1}
        PMCMD_COMMAND=${2}
        SERVER_ADDR=${3}
        NODEID=${4}
        USERNAME=${5}
        PASSWORD=${6}
        WAIT_OPTION=${7}
        FOLDER=${8}
        WORKFLOW=${9}
        G_POWERCENTER_HOME=${10}
        PCNAME=${11}
        PI_NAME=${12}
        PMPARM=${13}
elif [[ $# -eq 12 || ${13} == "NONPARM" ]]; then
        PMCMD=${1}
        PMCMD_COMMAND=${2}
        SERVER_ADDR=${3}
        NODEID=${4}
        USERNAME=${5}
        PASSWORD=${6}
        WAIT_OPTION=${7}
        FOLDER=${8}
        WORKFLOW=${9}
        G_POWERCENTER_HOME=${10}
        PCNAME=${11}
        PI_NAME=${12}
else
    echo ""
    echo "usage: $0 [pmcmd] [pmcmd_command] [server] [nodeid] [username] [password] [wait_option] [folder] [workflow] [PowerCenter path] [pcname] [piname] [pmparm]"
    echo "              where   pmcmd = program to execute, e.g. pmcmd"
    echo "                              pmcmd_command = command for pmcmd, e.g. startworkflow"
    echo "                              server address= e.g. iedm3301da.ent.agt.ab.ca"
    echo "                              nodeid = node id, e.g. d1n1"
    echo "                              username = PowerCenter username, e.g. x089155"
    echo "                              password = encrypted password for username"
    echo "                              wait_option = wait for command to finish, -wait | -nowait"
    echo "              folder = repository folder, e.g. BP_TL"
    echo "                              workflow = workflow to execute, e.g. wf_m_ods_bill_cycle_cd_load"
    echo "                              PowerCenter path e.g. /apps/infra/powercenter/8_5/DV/INT1/"
    echo "              pcname = Integration Service name, e.g. PCBP81PT68PI1"
    echo "              pmparm = Powercenter properties file path"
    echo ""
    exit 1
fi
# OS check
typeset -u machine=`uname`

# Powercenter 8.5 config
unset PM_REPOME PM_HOME
export INFA_HOME=${G_POWERCENTER_HOME}${NODEID}
export INFA_DOMAINS_FILE=${INFA_HOME}/domains.infa

# the PATH is cleared by Bec.sh script so need to append /usr/bin at the end
export PATH=${INFA_HOME}/java/bin:${INFA_HOME}/server/bin:${PATH}:/usr/bin
case "$machine" in
      "AIX")
	export LIBPATH=${INFA_HOME}/server/bin ;;
      "SUNOS")
    	export LD_LIBRARY_PATH=${INFA_HOME}/server/bin ;;
      "LINUX")
    	export LD_LIBRARY_PATH=${INFA_HOME}/server/bin ;;
       "*")
	export LIBPATH=${INFA_HOME}/server/bin
esac

nodemeta=${INFA_HOME}/domains.infa
# Update for PC9
nodedomain=`grep '<domainName>' ${nodemeta} |  head -1 | sed s/"<domainName>"/"|"/ | awk "-F|" '{print $2}' | awk "-F<" '{print $1}'`
echo "domain="${nodedomain}

# invoke the job and handle the return code
#
export BPPW=$PASSWORD

if [ "$PMPARM" = "" ]; then
  echo $PMCMD $PMCMD_COMMAND -sv ${PCNAME} -d $nodedomain -u $USERNAME -pv BPPW -$WAIT_OPTION -f $FOLDER -rin ${PI_NAME} $WORKFLOW
  $PMCMD $PMCMD_COMMAND -sv ${PCNAME} -d $nodedomain -u $USERNAME -pv BPPW -$WAIT_OPTION -f $FOLDER -rin ${PI_NAME} $WORKFLOW
else
  chmod 644 $PMPARM
  echo $PMCMD $PMCMD_COMMAND -sv ${PCNAME} -d $nodedomain -u $USERNAME -pv BPPW -$WAIT_OPTION -f $FOLDER -paramfile $PMPARM -rin ${PI_NAME} $WORKFLOW
  $PMCMD $PMCMD_COMMAND -sv ${PCNAME} -d $nodedomain -u $USERNAME -pv BPPW -$WAIT_OPTION -f $FOLDER -paramfile $PMPARM -rin ${PI_NAME} $WORKFLOW
fi

RC=$?

if [ $RC -ne 0 ]; then
        echo "ERR: Error attempting to run job $0 [$RC]"
else
        echo "INF: Job $0 ran successfully [$RC]"
fi


# all done
#
exit $RC


