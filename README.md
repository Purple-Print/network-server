<h1>Application Repository</h1>
<p align='center'>- 이상학, 전현정</p>

# Navigation
1. [Connected Servers](#connected-servers)
2. [Features](#features)

## Connected Servers
<h4><a href="https://github.com/Purple-Print/log-server">log-server</a></h4>
<h4><a href="https://github.com/Purple-Print/PurplePrintMailBatchServer">mail-batch-server</a></h4>
<h4><a href="https://github.com/Purple-Print/purpleprintConfig">config-server</a></h4>

## Features
1. RDBMS 데이터베이스를 구축하여 파일 시스템을 통해 데이터를 저장할 때 나타나는 문제인 데이터 종속성, 중복성, 무결성 문제를 해결하였다.
2. DDD 설계, JPA 사용을 통해 도메인 중심의 개발을 하였다.
3. JWT 형식의 로그인 기능 구축하여 세션과 달리 확장성이 용이하고, 데이터 위변조를 막을 수 있다.
4. Bcrypt 암호화 기능을 사용하여 비밀번호를 안전하게 보관하였다.
5. Restful API를 통해 여러 시스템 사이에서 정보 교환을 안전하고 효율적으로 하였다.
6. swagger를 통해 API를 문서화하여 효율적인 협업을 진행하였다.
7. event scheduling 기능을 사용하여 정해진 시간에 특정 기능이 작동하도록 구현하였다.
8. 비동기 처리를 통해 다른 서버에서의 처리 시간이 걸리는 동안 다른 작업을 수행할 수 있도록 구현하였다.
9. CQRS 형식의 구조를 적용하여 시스템에서 명령을 처리하는 책임과 조회를 처리하는 책임을 분리하기 위한 확장성을 고려하였다.
10. git flow를 통해 형상관리를 하였다.
11. 이메일 발송 시 이메일 발송 배치 서버를 따로 분리하여 대용량 데이터 처리에 대한 application 서버의 부하를 줄일 수 있었다.
12. 최신의 소스를 업데이트 받아 자동으로 빌드해주는 자동화 툴인 Jenkins를 사용하여 이미지 버전 관리를 하였고 서버 자동화 배포를 하였다.(CI/CD pipeline 구축)
13. 실시간 이동좌표 log 수집으로 인한 db 부하를 고려하여 ElasticSearch를 사용한 log 수집용 서버를 따로 분리하였다.
14. S3 storage, RDS, EC2 AWS 클라우드 서비스 사용을 통해 개발 인프라를 효율적으로 구축 및 관리하였다.
15. Spring Cloud Config를 사용한 Config 서버 구축을 통해 서버를 재배포하지 않고 설정 파일의 변경사항을 반영할 수 있게 하였다.
![전체 융합구조도](https://user-images.githubusercontent.com/65946607/204196096-114be9ba-a496-43ae-b3c9-ec4aab5b28b1.png)
![캐릭터 생성 융합구조도](https://user-images.githubusercontent.com/65946607/204196072-c52422ce-db15-4a17-80f0-60be20515ba9.png)
![행동분석 데이터 융합구조도](https://user-images.githubusercontent.com/65946607/204196102-001d60e0-e7a7-4797-b400-b2a2d0a5765e.png)
