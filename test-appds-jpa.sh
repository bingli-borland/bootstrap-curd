#!/bin/sh

#-Dspring.profiles.active=appds -Dspring.jta.enabled=false
hostport=$1

echo -------------6---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test1" --data-raw 'id=1&name=admin&password=test11' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=true&jta=false'

echo -------------7---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test2" --data-raw 'id=1&name=admin&password=test22' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=true&jta=false'