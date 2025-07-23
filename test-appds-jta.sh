#!/bin/sh

#-Dspring.profiles.active=appds -Dspring.jta.enabled=true
hostport=$1

echo -------------1---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test1" --data-raw 'id=1&name=admin&password=test1' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=test1&appds=true&jta=true'

echo -------------2---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test2" --data-raw 'id=1&name=admin&password=test2' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=test2&deptId=1&deptName=test2&appds=true&jta=true'

echo -------------3---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test3" --data-raw 'id=1&name=admin&password=test3' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=develop&appds=true&jta=true'

echo -------------4---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test4" --data-raw 'id=1&name=admin&password=test4' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=test4&deptId=1&deptName=develop&appds=true&jta=true'

echo -------------5---------------
curl -X POST "http://$hostport/jpademo/user/reset" --data-raw 'id=1&name=admin&password=default' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/test5" --data-raw 'id=1&name=admin&password=test5' -s -o /dev/null
curl -X POST "http://$hostport/jpademo/user/validation" --data-raw 'id=1&name=admin&password=default&deptId=1&deptName=test5&appds=true&jta=true'