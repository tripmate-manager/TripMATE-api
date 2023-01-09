```plain
 _____                __   __    __   _____  _____
|_   _|     _        |  \_/  |  /  \ |_   _||  ___|                   _
  | | _ __ (_) _ __  | |\ /| | / /\ \  | |  | |___  ___  ____  _ __  (_)
  | || '__|| || '_  \| || || |/ |__| \ | |  |  ___||___||__  || '_  \| |
  | || |   | || (_) || || || ||  __  | | |  | |___      | () || (_) || |
  |_||_|   |_|| .__/ |_||_||_||_|  |_| |_|  |_____|     |__._|| .__/ |_|
              |_|                                             |_|
```

## TripMATE-api

## Project Convention
### Environment
1. Java Version : 1.8.0+
2. Spring Boot Version : v2.7.1
3. Default Encoding : UTF-8
4. Default File System : Windows

### IDE
1. File Encoding
  * `File -> Settings -> Editor -> File Encodings -> Project Encoding -> UTF-8`
  * `File -> Settings -> Editor -> File Encodings -> Default encoding for properties files -> UTF-8`
2. Lombok Plugin
  * `File -> Settings -> Build, Excution, Deployment -> Compiler -> Annotation Processors -> Enable Annotation Processing check`
3. Tomcat VM Option
  * `-Dspring.profiles.active=local`
  * `-Djasypt.encryptor.password=안알랴줌(?)`

### Source
* [GitHub](https://github.com/tripmate-manager/TripMATE-api)

```bash
git clone https://github.com/tripmate-manager/TripMATE-api.git 
```

### git 사용자 정보 변경
* git bash 쉘을 이용하여, TripMATE-api 루트 디렉토리로 이동 후 아래 명령 실행
```bash
git config --local user.name 본인성명
git config --local user.email 메일주소(GitHub 계정에 등록된 주소)
```
