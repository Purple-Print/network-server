<h1>Purpleprint Application Repository</h1>
<strong>구성원 : 이상학, 전현정</strong>

# List

1. [Connected Servers](#connected-servers)
2. [Features](#features)
3. [Stacks](#stacks)
4. [Git Branch Strategy](#git-branch=strategy)
5. [CI/CD](#ci/cd)
6. [Structure](#structure)
7. [Issue](#issue)
8. [Wiki](#wiki)
<br>

## Connected Servers

1. <h4><a href="https://github.com/Purple-Print/log-server">log-server</a></h4>
2. <h4><a href="https://github.com/Purple-Print/PurplePrintMailBatchServer">mail-batch-server</a></h4>
3. <h4><a href="https://github.com/Purple-Print/purpleprintConfig">config-server</a></h4>
<br>

## Features

+ RDBMS 데이터베이스를 구축하여 파일 시스템을 통해 데이터를 저장할 때 나타나는 문제인 데이터 종속성, 중복성, 무결성 문제를 해결하였다.
+ DDD 설계, JPA 사용을 통해 도메인 중심의 개발을 진행하였다.
+ JWT 형식의 로그인 기능 구축하여 세션과 달리 확장성이 용이하고, 데이터 위변조를 막을 수 있다.
+ Bcrypt 암호화 기능을 사용하여 비밀번호를 안전하게 보관하였다.
+ Restful API를 통해 여러 시스템 사이에서 정보 교환을 안전하고 효율적으로 하였다.
+ swagger를 통해 API를 문서화하여 효율적인 협업을 진행하였다.
+ event scheduling 기능을 사용하여 정해진 시간에 특정 기능이 작동하도록 구현하였다.
+ 비동기 처리를 통해 다른 서버에서의 처리 시간이 걸리는 동안 다른 작업을 수행할 수 있도록 구현하였다.
+ CQRS 형식의 구조를 적용하여 시스템에서 명령을 처리하는 책임과 조회를 처리하는 책임을 분리하기 위한 확장성을 고려하였다.
+ git flow를 통해 형상관리를 하였다.
+ 이메일 발송 시 이메일 발송 배치 서버를 따로 분리하여 대용량 데이터 처리에 대한 application 서버의 부하를 줄일 수 있었다.
+ 최신의 소스를 업데이트 받아 자동으로 빌드해주는 자동화 툴인 Jenkins를 사용하여 이미지 버전 관리를 하였고 서버 자동화 배포를 하였다.(CI/CD pipeline 구축)
+ 실시간 이동좌표 log 수집으로 인한 db 부하를 고려하여 ElasticSearch를 사용한 log 수집용 서버를 따로 분리하였다.
+ S3 storage, RDS, EC2 AWS 클라우드 서비스 사용을 통해 개발 인프라를 효율적으로 구축 및 관리하였다.
+ Spring Cloud Config를 사용한 Config 서버 구축을 통해 서버를 재배포하지 않고 설정 파일의 변경사항을 반영할 수 있게 하였다.
<br>

## Stacks

<div align=center> 
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
<img src="https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=JPA&logoColor=white">
<br>

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
<img src="https://img.shields.io/badge/Elasticsearch-005571?style=for-the-badge&logo=Elasticsearch&logoColor=white">
<img src="https://img.shields.io/badge/Kibana-005571?style=for-the-badge&logo=Kibana&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">
<img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> 
<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">
<img src="https://img.shields.io/badge/Elastic Beanstalk-005571?style=for-the-badge&logo=Elastic Beanstalk&logoColor=white">
<br>
  
<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white">
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white">
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white">
<br>
  
<img src="https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=FastAPI&logoColor=white">
<img src="https://img.shields.io/badge/Jupyter-F37626?style=for-the-badge&logo=Jupyter&logoColor=white">
<img src="https://img.shields.io/badge/Unity-569A31?style=for-the-badge&logo=Unity&logoColor=white">
<img src="https://img.shields.io/badge/Photon-F37626?style=for-the-badge&logo=Photon&logoColor=white">
<img src="https://img.shields.io/badge/Scikit Learn-F7931E?style=for-the-badge&logo=scikit-learn&logoColor=white">
<img src="https://img.shields.io/badge/Pandas-150458?style=for-the-badge&logo=pandas&logoColor=white">
<br>
  
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<br>
</div>

## Git Branch Strategy

![branch 관리 전략](https://user-images.githubusercontent.com/65946607/204421760-af9c4d57-2d70-4dea-8f42-71d518dfdec2.jpg)
<br>
+ Git Flow 전략을 사용하였고 main, dev, feature 3가지 종류의 브랜치를 사용하였다.
1. main: 배포를 위한 main 브랜치
2. dev: 개발 통합 브랜치
3. feature: 기능 단위 개발 브랜치
<br>

## CI/CD
![cicd](https://user-images.githubusercontent.com/65946607/204679709-466bba40-9030-40b5-9476-f90945563c3b.png)
![elastic beanstalk](https://user-images.githubusercontent.com/65946607/204680712-c744b045-b9a3-4af5-834e-738d94f4cddf.png)
<br>
+ Jenkins를 통해 CI/CD pipeline을 구축하였고, elastic beanstalk를 사용하여 불필요한 자원 낭비 없는 서비스 배포를 하였다.
<br>

## Structure
### 전체 융합구조도

![전체 융합구조도](https://user-images.githubusercontent.com/65946607/204196096-114be9ba-a496-43ae-b3c9-ec4aab5b28b1.png)

### 캐릭터 생성 융합구조도

![캐릭터 생성 융합구조도](https://user-images.githubusercontent.com/65946607/204196072-c52422ce-db15-4a17-80f0-60be20515ba9.png)

### 행동분석 데이터 융합구조도

![행동분석 데이터 융합구조도](https://user-images.githubusercontent.com/65946607/204196102-001d60e0-e7a7-4797-b400-b2a2d0a5765e.png)
<br>

## Issue

Application-Server issue -> <a href="https://github.com/Purple-Print/network-server/issues">application-server</a></br>
Log-Server issue -> <a href="https://github.com/Purple-Print/log-server/issues">log-server</a></br>
Mail-Batch-Server issue -> <a href="https://github.com/Purple-Print/PurplePrintMailBatchServer/issues">mail-batch-server</a></br>
<br>

## Wiki

Application-Server Wiki -> <a href="https://github.com/Purple-Print/network-server/wiki">application-server</a></br>
Log-Server Wiki -> <a href="https://github.com/Purple-Print/log-server/wiki">log-server</a></br>
Mail-Batch-Server Wiki -> <a href="https://github.com/Purple-Print/PurplePrintMailBatchServer/wiki">mail-batch-server</a></br>
<br>
