#!/bin/sh

#-Dspring.profiles.active=jndids -Dspring.jta.enabled=true
hostport=$1

echo -------------8---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test1" --data-raw 'id=1&name=admin&password=test111' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=false&jta=true'

echo -------------9---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test2" --data-raw 'id=1&name=admin&password=test222' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=false&jta=true'

