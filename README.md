<h1>Purpleprint Application Repository</h1>
<strong>구성원 : 이상학, 전현정</strong>

# List

1. [Connected Servers](#connected-servers)
2. [Features](#features)
3. [Stacks](#stacks)
4. [Package structure](#package-structure)
5. [Git Branch Strategy](#git-branch-strategy)
6. [CI/CD](#ci-cd)
7. [Program Structure](#program-structure)
8. [Issue](#issue)
<br>

## Connected Servers

1. <h4><a href="https://github.com/Purple-Print/log-server">log-server</a></h4>
2. <h4><a href="https://github.com/Purple-Print/PurplePrintMailBatchServer">mail-batch-server</a></h4>
3. <h4><a href="https://github.com/Purple-Print/purpleprintConfig">config-server</a></h4>
<br>

## Features

+ MySQL을 사용한 데이터베이스 구축
+ DDD 설계, JPA 사용
+ JWT 형식의 로그인 기능 구축
+ Restful API 개발
+ swagger를 통해 API를 문서화
+ event scheduling 기능 사용
+ 비동기 처리 기능 구현
+ CQRS 형식의 구조를 적용
+ git flow를 통해 형상관리 진행
+ 이메일 발송 배치 서버를 따로 분리
+ Jenkins를 통한 이미지 버전 관리 및 서버 자동화 배포
+ ElasticSearch를 사용한 log 수집용 서버를 따로 분리
+ S3 storage, RDS, EC2 AWS 클라우드 서비스 사용
+ Spring Cloud Config를 사용한 Config 서버 구축
<br>
<a href="https://github.com/Purple-Print/network-server/wiki/Features">자세한 내용 보러가기</a>
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

## Package Structure

+ CQRS 형식의 구조를 적용하여 시스템에서 명령을 처리하는 책임과 조회를 처리하는 책임을 분리하기 위한 확장성을 고려하며 개발
<br>
<a href="https://github.com/Purple-Print/network-server/wiki/%ED%8C%A8%ED%82%A4%EC%A7%80-%EA%B5%AC%EC%A1%B0">자세한 내용 보러가기</a></br>
<br>

## Git Branch Strategy

+ Git Flow 전략을 사용하였고 main, dev, feature 3가지 종류의 브랜치를 사용
<br>
<a href="https://github.com/Purple-Print/network-server/wiki/Git-Branch-Strategy">자세한 내용 보러가기</a>
<br>


## CI CD

+ Jenkins를 통해 CI/CD pipeline을 구축, elastic beanstalk를 사용하여 불필요한 자원 낭비 없는 서비스 배포
<br>
<a href="https://github.com/Purple-Print/network-server/wiki/CI--CD">자세한 내용 보러가기</a>
<br>

## Program Structure

+ XR - NETWORK - AI 3가지 분야에서 융합하여 개발을 진행
<br>
<a href="https://github.com/Purple-Print/network-server/wiki/PurplePrint-Network-%EC%9C%B5%ED%95%A9%EA%B5%AC%EC%A1%B0%EB%8F%84">자세한 내용 보러가기</a>
<br>

## Issue

Application-Server issue -> <a href="https://github.com/Purple-Print/network-server/issues">application-server</a></br>
Log-Server issue -> <a href="https://github.com/Purple-Print/log-server/issues">log-server</a></br>
Mail-Batch-Server issue -> <a href="https://github.com/Purple-Print/PurplePrintMailBatchServer/issues">mail-batch-server</a></br>
<br>

