Reyan
==========
Reyan-BE is an open source project providing advances search features for holy Quran.

Prerequisites
==========
You need to install docker in case you are using macOs or Windows. 

How to use
==========
server:

docker-compose run -p 8080:8080 -v /var/lib/reyan-files:/mnt/reyan-files  reyan-api

macos:

docker-compose run -p 8080:8080 -v /Users/Shared/reyan-files:/mnt/reyan-files  reyan-api  

REST ENDPOINTS
==========
Getting list of all surahs names ==> http://localhost:8080/api/allsurahs
