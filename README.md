# 📌프리온보딩 백엔드 인턴십 선발과제
***
## 요구사항 분석
* 프로젝트 개요
  + 본 서비스는 기업의 채용을 위한 웹 서비스 입니다. 
  + 회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.
* 요구사항
  + 채용공고를 등록합니다.
  + 채용공고를 수정합니다.
  + 채용공고를 삭제합니다.
  + 채용공고 목록을 가져옵니다.
  + 키워드를 이용해 채용공고를 검색합니다.
  + 채용공고의 상세 내용을 가져옵니다. 이 페이지에서는 해당 회사가 올린 다른 채용공고가 추가적으로 포함됩니다.
  + 사용자는 채용공고에 지원합니다.
* 필요 모델
  + 회사
  + 사용자
  + 채용공고
  + 지원내역
* 사용 기술
  + Java 11
  + Spring
  + Spring boot
  + MySQL
  + Spring Data JPA
***
### 📜 API명세
| 기능 | HTTP 메서드 | API PATH | Request | Response                                                                                                                                                                                                                                                                                                                                                | Description                                                                                                                   |
|---|---|---|---|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------|
|유저 생성|POST|/user|{<br/>"name":"김동현<br/>}| {<br/>"name":"김동현<br/>}                                                                                                                                                                                                                                                                                                                                 | 유저ID는 자동으로 생성됩니다.                                                                                                             |
|회사 생성|POST|/company|{<br/>"name":"원티드",<br/>"nation":"한국",<br/>"region":"서울"<br/>}| {<br/>"id":1,<br/>name":"원티드",<br/>"nation":"한국",<br/>"region":"서울"<br/>}                                                                                                                                                                                                                                                                               | 회사ID는 자동으로 생성됩니다..                                                                                                            
|채용공고 등록|POST|/employment|{<br/>"company_id": 1,<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "Java"<br/>}| {<br/>"company_id": 1,<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "Java"<br/>}                                                                                                                                                                                                               | company_id를 입력하여 해당 회사의 공고로 등록합니다. 채용공고ID는 자동으로 생성됩니다.                                                                        |
|채용공고 수정|PUT|/employment/{id}|{<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "python"<br/>}| {<br/>"position": "백엔드 개발자",<br/>"reward": 50000,<br/>"detail": "우리 회사에서 백엔드 개발자를 채용합니다.",<br/>"skill": "python"<br/>}                                                                                                                                                                                                                                  | 채용공고ID를 이용해 해당 공고의 내용을 수정할 수 있습니다.                                                                                            |
|채용공고 삭제|DELETE|/employment/{id}|-| {<br/>"message": "채용공고 1번 삭제 완료."<br/>}| 채용공고ID를 이용해 해당 공고를 삭제할 수 있습니다.                                                                                                |
|채용공고 목록 조회|GET|/employment|-| <br/>{<br/>"id": 1,<br/>"companyName": "kakao",<br/>"nation": "korea",<br/>"region": "jeju",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "react"<br/>},<br/>{<br/>"id": 2,<br/>"companyName": "LG",<br/>"nation": "korea",<br/>"region": "seoul",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "react"<br/>} | 현재 등록된 채용공고의 전체 목록을 가져옵니다. 해당 응답에는 채용공고를 등록한 회사의 정보와 채용 정보가 표시됩니다.                                                            |
|채용공고 검색|GET|/employment?search=keyward|-| {<br/>"id": 11,<br/>"companyName": "kakao",<br/>"nation": "korea",<br/>"region": "seoul",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "react"<br/>}| url의 keyward부분에 원하는 검색어를 입력하면 해당 키워드와 일치하는 채용공고의 전체 내역을 가져옵니다. ex) /employment?search=seoul 입력 시, region이 seoul인 모든 채용공고를 가져옴. |
|채용 상세 페이지|GET|/employment/1|-| {<br/>"id": 1,<br/>"companyName": "wanted",<br/>"nation": "korea",<br/>"region": "ansan",<br/>"position": "백엔드 시니어 개발자",<br/>"reward": 30000000,<br/>"skill": "python",<br/>"detail": "카카오에서 백엔드 시니어 개발자 팀장님을 채용합니다. 자격요건은...",<br/>"otherEmployment": [<br/>5,<br/>6<br/>]<br/>}| 원하는 채용공고의 ID를 입력하면 해당 공고의 상세 페이지를 가져옵니다. 추가적으로 채용공고를 올린 회사의 다른 공고들의 리스트도 제공됩니다.                                               |
|채용공고 지원|POST|/apply|{<br/>"userId" : 2,<br/>"employmentId" : 3<br/>}|{<br/>"userId" : 2,<br/>"employmentId" : 3<br/>}| 회원ID와 채용공고ID를 입력하여 공고에 지원합니다. 유저는 한 공고당 한 번 지원이 가능하며, 이미 지원한 공고일 시에 "이미 지원한 공고입니다." 라는 문구를 출력합니다.                             |


***
### 🔗 ERD
<img width="531" alt="ERD" src="https://github.com/gitKDH/wanted-pre-onboarding-backend/assets/119571075/7ae125d4-3f1e-4b92-bfbe-b249c2599f35">



