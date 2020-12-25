Reyan
==========
Reyan-BE is an open source project providing advances search features for holy Quran.

Prerequisites
==========
You need to install docker in case you are using macOs or Windows. 

How to use
==========
1- run the dockers : 
```Shell
docker container kill reyan-api && docker container rm  reyan-api && docker-compose run -d -p 8080:8080 -v /var/lib/reyan-files:/mnt/reyan-files  reyan-api && docker container rename reyan-be_reyan-api_run_1 reyan-api
```

2- connect to elasticsearch docker and start elasticsearch service and kibana manually (to be fixed) : docker run exec -it <id> bash 

REST ENDPOINTS
==========
1- Surahs:

* Getting list of all surahs names ==> GET http://localhost:8080/api/allsurahs
* Getting list of Ayehs for a surah ==> GET http://localhost:8080/api/findAyehsBySurah/{surahName}
* Getting list of Ayehs for a surah ==> GET http://localhost:8080/api/findAyehsBySurah/{surahName}

2- Configs

* Adding new config ==> POST http://localhost:8080/api/config  
* Getting list of all configs ==> GET http://localhost:8080/api/config/allconfigs

3- Recites

* Playing recite of an Ayeh for a language: GET http://localhost:8080/api/recite/{languageId}/{surehId}/{ayeId} 
* Playing recite of a page with a given reciter sound: GET http://localhost:8080/api/recite/page/{reciterId}/{pageNumber}

 3- Elasticsearch
 
* @Deprecated Preparing index input file: GET http://localhost:8080/api/es/createIndexBulk 

